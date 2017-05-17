package app.util;

public class Checkboxes {
	
	int correct;
	int incorrect;
	
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getIncorrect() {
		return incorrect;
	}
	public void setIncorrect(int incorrect) {
		this.incorrect = incorrect;
	}
	
	public void addCorrect(){
		correct++;
	}
	
	public void addIncorrect(){
		incorrect++;
	}
	
	

}
