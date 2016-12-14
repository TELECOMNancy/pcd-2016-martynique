package utils;

import javafx.scene.control.Button;

public class SuggestionAnswerButton extends Button {
	private Answer answer;
	
	public SuggestionAnswerButton(Answer answer){
		this.answer = answer;
		this.setText(this.answer.getText());
		this.getStyleClass().add("suggestionButton");
	}
	
	public Answer getAnswer(){
		return this.answer;
	}
}
