package controllers;

import java.util.HashMap;
import java.util.List;

import db.SuggestionDB;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import utils.Answer;
import utils.SuggestionAnswerButton;
import utils.Question;

public class SuggestionController extends Controller {
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
    	Question q2 = new Question("Quel genre ?", Flag.TAG);
    	Question q3 = new Question("Quel domaine ?", Flag.TAG);
    	Question q4 = new Question("Combien de temps avez-vous ?", Flag.LENGTH);

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
    	
    	Answer a4_1 = new Answer("5 minutes", "Suggestions.length < 6");
    	Answer a4_2 = new Answer("10 minutes", "Suggestions.length >= 6 AND Suggestions.length < 11");
    	Answer a4_3 = new Answer("30 minutes", "Suggestions.length >= 11");
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
    
    private void update(){
    	if(this.curQuestion != null){
	    	this.questionLabel.setText(this.curQuestion.getText());
	    	for(int i=0;i<this.curQuestion.getAnswers().size();i++){
	    		Answer curAnswer = this.curQuestion.getAnswers().get(i);
	    		SuggestionAnswerButton curButton = new SuggestionAnswerButton(curAnswer);
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
    		
    		SuggestionDB suggestionDB = new SuggestionDB();
    		List<String> queryReturn = suggestionDB.runSuggestionQuery(flags);
    		System.out.println(queryReturn.toString());
    		
    		String selected = queryReturn.get(0);
    		
    		System.out.println(selected);
    		
    		appController.playWebVideo(selected);
    	}
    }
    
    public enum Flag{
    	TAG,
    	LENGTH;
    }
}
