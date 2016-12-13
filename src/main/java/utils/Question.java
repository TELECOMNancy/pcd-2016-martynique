package utils;
import java.util.ArrayList;

import controllers.FormController.Flag;

public class Question {
	private ArrayList<Answer> answers;
	private String question;
	private Flag flag;
	private boolean memorise;
	
	private Question(String question, ArrayList<Answer> answers, Flag flag, boolean memorise){
		this.answers = answers;
		this.flag = flag;
		this.question = question;
		this.memorise = memorise;
	}
	
	public Question(String question, ArrayList<Answer> answers, Flag flag){
		this(question, answers, flag, true);
	}
	
	public Question(String question, ArrayList<Answer> answers){
		this(question, answers, null, false);
	}
	
	public Question(String question, Flag flag){
		this(question, new ArrayList<Answer>(), flag);
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
	
	public Flag getFlag(){
		return this.flag;
	}
	
	public boolean isMemorised(){
		return this.memorise;
	}
	
	public ArrayList<Answer> getAnswers(){
		return this.answers;
	}
}
