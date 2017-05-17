package app.triples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import app.doc.Document;

public class TripleDAO {
	
//	public static List<Triple> triples = new ArrayList<Triple>();
	//document  = <http://tamps.cinvestav.mx/rdf/graph/programmer.txt.rdf>
	public static List<Sentence> getAllTriples(String document){
		List<Sentence> sentences = new ArrayList<Sentence>();
		String service = "http://127.0.0.1:3030/ComputerScience/sparql";
		String queryString = "SELECT ?snt ?s ?pred ?obj ?topic WHERE{"
				+ "<http://tamps.cinvestav.mx/rdf/graph/"+document+".txt.rdf> <http://tamps.cinvestav.mx/rdf/property/topic> ?topic."
				+ " ?s <http://tamps.cinvestav.mx/rdf/#inDoc> <http://tamps.cinvestav.mx/rdf/graph/"+document+".txt.rdf> ."
				+ "?obj <http://tamps.cinvestav.mx/rdf/#inDoc> <http://tamps.cinvestav.mx/rdf/graph/"+document+".txt.rdf> ."
				+ " ?s <http://tamps.cinvestav.mx/rdf/#inSentence> ?snt . "
				+ "?obj <http://tamps.cinvestav.mx/rdf/#inSentence> ?snt ."
				+ " ?s ?pred ?obj."
				+ " FILTER (?pred != <http://tamps.cinvestav.mx/rdf/#inDoc> && ?pred != <http://tamps.cinvestav.mx/#semanticSimilarTo>"
				+ " && ?pred != <http://tamps.cinvestav.mx/rdf/#inSentence>)"
				+ " }";
		Query query = QueryFactory.create(queryString);
		try(QueryExecution qexec =QueryExecutionFactory.sparqlService(service, query)){
			ResultSet results = qexec.execSelect();
			int counter = 0;
			for( ; results.hasNext() ;){
				QuerySolution soln = results.nextSolution();
				
				String orgSentence = soln.get("?snt").toString();
				String subject = prefixedEntity(soln.get("?s").toString());
				String predicate = prefixedPredName(soln.get("?pred").toString());
				String object = prefixedEntity(soln.get("?obj").toString());
				String topic = soln.get("?topic").toString();
				
				Sentence existsSentence = null;
				for(Sentence sentence : sentences){
					if(sentence.getOrgSnt().equals(orgSentence)){
						existsSentence = sentence;
						break;
					}
				}
				
				Triple triple = new Triple();
				triple.setSbj(subject);
				triple.setPred(predicate);
				triple.setObj(object);
				triple.setNumSnt(counter);
				triple.setTopic(topic);
				
				if(existsSentence == null){
					Sentence snt = new Sentence();
					snt.setOrgSnt(orgSentence);
					snt.setNumSnt(++counter);
					triple.setNumSnt(counter);
					snt.getTriples().add(triple);
					sentences.add(snt);
				}else{
					existsSentence.getTriples().add(triple);
				}
				
			}
		}
		Collections.sort(sentences);
		return sentences;
	}
	
	public static String prefixedPredName(String pred) {
		String prefixedName = "";
		pred = pred.replace("http://", "").replace("www.", "");
		String[] splitPred = pred.split("/");
		if (pred.contains("owl#sameAs"))
			prefixedName = "owl:sameAs";
		else if (pred.contains("22-rdf-syntax-ns#type"))
			prefixedName = "rdf:type";
		else {
			String[] splitSplitPred = splitPred[0].split("\\.");
			String firstPart = splitSplitPred[0];
			String lastPart = splitPred[splitPred.length - 1];

			prefixedName = firstPart + ":" + lastPart;
		}
		return prefixedName;
	}
	
	public static String prefixedEntity(String entity){
		String prefixedEnt  = "";
		
		entity = entity.replace("http://", "");
		String[] splitEntity = entity.split("/");
		
		if(entity.contains("wibitaxonomy")){
			prefixedEnt = "wibi:"  + splitEntity[splitEntity.length - 1];
		}else if(entity.contains("dbpedia")){
			prefixedEnt = "dbr:" + splitEntity[splitEntity.length - 1];
		}else if(entity.contains("tamps")){
			prefixedEnt = "tmpr:" + splitEntity[splitEntity.length - 1];
		}
		
		
		return prefixedEnt;
	}
	
	
	

}
