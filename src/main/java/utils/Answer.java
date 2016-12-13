package utils;

public class Answer {
	private Question leadingQuestion;
	private String answer;
	
	public Answer(String answer){
		this(answer, null);
	}
	
	public Answer(String answer, Question leadingQuestion){
		this.answer = answer;
		this.leadingQuestion = leadingQuestion;
	}

	public String getText() {
		return this.answer;
	}
	
	public Question getLeadingQuestion(){
		return this.leadingQuestion;
	}
}
