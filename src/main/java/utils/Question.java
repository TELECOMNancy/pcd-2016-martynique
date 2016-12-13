package utils;
import java.util.ArrayList;

public class Question {
	private ArrayList<Answer> answers;
	private String question;
	
	public Question(String question, ArrayList<Answer> answers){
		this.answers = answers;
		this.question = question;
	}
	
	public Question(String question){
		this(question, new ArrayList<Answer>());
	}
	
	public void addAnswer(Answer answer){
		this.answers.add(answer);
	}
	
	public String getText(){
		return this.question;
	}
	
	public ArrayList<Answer> getAnswers(){
		return this.answers;
	}
}
