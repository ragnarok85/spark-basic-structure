package app.util;

public class Checkboxes {
	
	int correct;
	int incorrect;
	
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = Integer.parseInt(correct);
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
