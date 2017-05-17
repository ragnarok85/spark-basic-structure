package app.triples;

public class Triple {

	int numSnt;
	String orgSnt;
	String sbj;
	String pred;
	String obj;
	String topic;

	
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
	public String getSbj() {
		return sbj;
	}
	public void setSbj(String sbj) {
		this.sbj = sbj;
	}
	public String getPred() {
		return pred;
	}
	public void setPred(String pred) {
		this.pred = pred;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
