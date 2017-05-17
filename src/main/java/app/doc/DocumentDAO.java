package app.doc;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class DocumentDAO {
	
//	public static List<Document> documents = new ArrayList<Document>();
	
	public static List<Document> getAllDocuments(){
		List<Document> documents = new ArrayList<Document>();
		String service = "http://127.0.0.1:3030/ComputerScience/query";
		String queryString ="SELECT DISTINCT ?sDoc"
				+ " WHERE { "
				+ " ?s <http://tamps.cinvestav.mx/rdf/#inDoc> ?sDoc "
				+ " }";
		Query query = QueryFactory.create(queryString);
		try(QueryExecution qexec =QueryExecutionFactory.sparqlService(service, query)){
			ResultSet results = qexec.execSelect();
			int counter = 0;
			for( ; results.hasNext() ;){
				Document doc = new Document();
				QuerySolution soln = results.nextSolution();
				String result = soln.get("?sDoc").toString();
				result = result.replace("http://tamps.cinvestav.mx/rdf/graph/", "").replace(".txt.rdf", "");
				doc.setDocName(result);
				documents.add(doc);
			}
		}
		return documents;
	}
	
	public static void main(String[] args){
		
		DocumentDAO.getAllDocuments();
	}

}
