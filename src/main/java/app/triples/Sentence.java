package app.triples;

import java.util.ArrayList;
import java.util.List;


public class Sentence implements Comparable<Sentence>{
	
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
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Sentence))
			return false;
		Sentence other = (Sentence) obj;
		if(this.numSnt == other.numSnt && this.orgSnt.equals(other.orgSnt) && this.triples == other.triples)
			return true;
		return false;
	}
	
	public int compareTo(Sentence o) {
		int returnValue = 0;
		if(this.numSnt > o.numSnt){
			returnValue = 1;
		}else if(this.numSnt < o.numSnt){
			returnValue = -1;
		}else if(this.numSnt == o.numSnt){
			returnValue = 0;
		}
		return returnValue;
	}

}
