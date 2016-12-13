package utils;
import java.util.ArrayList;

public class Question {
	private ArrayList<Answer> answers;
	private String question;
	private String flag;
	private boolean memorise;
	
	private Question(String question, ArrayList<Answer> answers, String flag, boolean memorise){
		this.answers = answers;
		this.flag = flag;
		this.question = question;
		this.memorise = memorise;
	}
	
	public Question(String question, ArrayList<Answer> answers, String flag){
		this(question, answers, flag, true);
	}
	
	public Question(String question, ArrayList<Answer> answers){
		this(question, answers, null, false);
	}
	
	public Question(String question, String flag){
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
	
	public String getFlag(){
		return this.flag;
	}
	
	public boolean isMemorised(){
		return this.memorise;
	}
	
	public ArrayList<Answer> getAnswers(){
		return this.answers;
	}
}
