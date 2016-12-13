package utils;

public class Answer {
	private Question leadingQuestion;
	private String answer;
	private String realValue;
	
	public Answer(String answer){
		this(answer, answer, null);
	}
	
	public Answer(String answer, String realValue){
		this(answer, realValue, null);
	}

	public Answer(String answer, Question leadingQuestion){
		this(answer, answer, leadingQuestion);
	}
	
	public Answer(String answer, String realValue, Question leadingQuestion){
		this.answer = answer;
		this.realValue = realValue;
		this.leadingQuestion = leadingQuestion;
	}

	public String getText() {
		return this.answer;
	}

	public String getRealValue() {
		return this.realValue;
	}
	
	public Question getLeadingQuestion(){
		return this.leadingQuestion;
	}
}
