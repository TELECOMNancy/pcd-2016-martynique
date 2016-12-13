package controllers;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import utils.Answer;
import utils.MyFormButton;
import utils.Question;

public class FormController extends Controller {
    @FXML
    private Label questionLabel;

    @FXML
    private HBox buttonsContainer;

    private Question curQuestion;
    private HashMap<Flag, String> flags;
    private String request;

    @FXML
    private void initialize() {
    	/////////////////////////
    	//       PRESETS       //
    	/////////////////////////
    	Question q1 = new Question("Que voulez-vous faire ?");
    	Question q2 = new Question("Quel genre ?", Flag.TYPE);
    	Question q3 = new Question("Quel domaine ?", Flag.TYPE);
    	Question q4 = new Question("Combien de temps avez-vous ?", Flag.TEMPS);

    	Answer a1_1 = new Answer("Me divertir", q2);
    	Answer a1_2 = new Answer("M'instruire", q3);
    	q1.addAnswer(a1_1);
    	q1.addAnswer(a1_2);
    	
    	Answer a2_1 = new Answer("Jeux-vidéo", "jv", q4);
    	Answer a2_2 = new Answer("Sport", "sport", q4);
    	q2.addAnswer(a2_1);
    	q2.addAnswer(a2_2);
    	
    	Answer a3_1 = new Answer("Sciences", "sciences", q4);
    	Answer a3_2 = new Answer("Histoire", "histoire", q4);
    	q3.addAnswer(a3_1);
    	q3.addAnswer(a3_2);
    	
    	Answer a4_1 = new Answer("5 minutes", "temps < 6");
    	Answer a4_2 = new Answer("10 minutes", "temps >= 6 && temps < 11");
    	Answer a4_3 = new Answer("30 minutes", "temps >= 11");
    	q4.addAnswer(a4_1);
    	q4.addAnswer(a4_2);
    	q4.addAnswer(a4_3);
    	
    	this.curQuestion = q1;
    	/////////////////////////////
    	
    	update();
    	
    	flags = new HashMap<Flag, String>();
    	
    	/*
    	this.searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                search();
            }
        });
        */
    }
    
    private void generateRequest(){
    	String Request = "";
    	String WHERE = "";
    	for(Flag s : this.flags.keySet()){
    		if(!WHERE.equals(""))
        		WHERE += "&& ";
		
			if(s == Flag.TYPE)
				WHERE += "type == " + this.flags.get(s)+" ";
			if(s == Flag.TEMPS)
    			WHERE += this.flags.get(s)+" ";
    	}
    	
    	Request += "SELECT * IN VIDEO_SUGGESTION ";
    	Request += "WHERE " + WHERE;
    	
    	System.out.println(Request);
    }
    
    private void update(){
    	if(this.curQuestion != null){
	    	this.questionLabel.setText(this.curQuestion.getText());
	    	for(int i=0;i<this.curQuestion.getAnswers().size();i++){
	    		Answer curAnswer = this.curQuestion.getAnswers().get(i);
	    		MyFormButton curButton = new MyFormButton(curAnswer);
	    		curButton.setOnMousePressed(new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent event) {
	                	if(curQuestion.isMemorised())
	                		flags.put(curQuestion.getFlag(), curAnswer.getRealValue());
	                	
	                    curQuestion = curButton.getAnswer().getLeadingQuestion();
	                    buttonsContainer.getChildren().clear();
	                    update();
	                }
	            });
	    		this.buttonsContainer.getChildren().add(curButton);
	    	}
    	}
    	else{
    		this.questionLabel.setText("DONE");
    		System.out.println(this.flags.toString());
        	generateRequest();
    	}
    }
    
    public enum Flag{
    	TYPE,
    	TEMPS;
    }
}
