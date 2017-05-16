package app.triples;

import java.util.ArrayList;
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
		String queryString = "SELECT ?snt ?s ?pred ?obj WHERE{"
				+ " ?s <http://tamps.cinvestav.mx/rdf/#inDoc> <http://tamps.cinvestav.mx/rdf/graph/"+document+".txt.rdf> ."
				+ " ?s <http://tamps.cinvestav.mx/rdf/#inSentence> ?snt . "
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
				String subject = soln.get("?s").toString();
				String predicate = soln.get("?pred").toString();
				String object = soln.get("?obj").toString();
				
				Sentence existsSentence = null;
				for(Sentence sentence : sentences){
					if(sentence.getOrgSnt().equals(orgSentence)){
						existsSentence = sentence;
						break;
					}
				}
				
				if(existsSentence == null){
					Sentence snt = new Sentence();
					snt.setOrgSnt(orgSentence);
					snt.setNumSnt(++counter);
					Triple triple = new Triple();
					triple.setSbj(subject);
					triple.setPred(predicate);
					triple.setObj(object);
					triple.setNumSnt(++counter);
					snt.getTriples().add(triple);
					sentences.add(snt);
				}else{
					Triple triple = new Triple();
					triple.setSbj(subject);
					triple.setPred(predicate);
					triple.setObj(object);
					triple.setNumSnt(++counter);
					existsSentence.getTriples().add(triple);
				}
				
			}
		}
				
		return sentences;
	}
	
	

}
