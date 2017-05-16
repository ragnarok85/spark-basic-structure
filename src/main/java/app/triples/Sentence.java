package app.triples;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	
	int numSnt;
	String orgSnt;
	List<Triple> triples;
	int sizeTriples;
	
	public Sentence(){
		this.triples = new ArrayList<Triple>();
	}
	
	public int getNumSnt() {
		return numSnt;
	}
	public void setNumSnt(int numSnt) {
		this.numSnt = numSnt;
	}
	public String getOrgSnt() {
		return orgSnt;
	}
	public void setOrgSnt(String orgSnt) {
		this.orgSnt = orgSnt;
	}
	public List<Triple> getTriples() {
		return triples;
	}
	public void setTriples(List<Triple> triples) {
		this.triples.addAll(triples);
	}

	public int getSizeTriples() {
		return triples.size() + 1;
	}
	

}
