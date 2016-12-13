package utils;

import javafx.scene.control.Button;

public class MyFormButton extends Button {
	private Answer answer;
	
	public MyFormButton(Answer answer){
		this.answer = answer;
		this.setText(this.answer.getText());
	}
	
	public Answer getAnswer(){
		return this.answer;
	}
}
