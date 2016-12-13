package utils;

import javafx.scene.control.Button;

public class MyFormButton extends Button {
	private String answer;
	
	public MyFormButton(String answer){
		this.answer = answer;
		this.setText(this.answer);
	}
}
