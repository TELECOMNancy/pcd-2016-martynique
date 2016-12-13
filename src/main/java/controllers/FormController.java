package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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

    @FXML
    private void initialize() {
    	/////////////////////////
    	//       PRESETS       //
    	/////////////////////////
    	Question q1 = new Question("Que voulez-vous faire ?");
    	Question q2 = new Question("Quel genre ?");
    	Question q3 = new Question("Quel domaine ?");
    	Question q4 = new Question("Combien de temps avez-vous ?");

    	Answer a1_1 = new Answer("Me divertir", q2);
    	Answer a1_2 = new Answer("M'instruire", q3);
    	q1.addAnswer(a1_1);
    	q1.addAnswer(a1_2);
    	
    	Answer a2_1 = new Answer("Jeux-vidéo", q4);
    	Answer a2_2 = new Answer("Sport", q4);
    	q2.addAnswer(a2_1);
    	q2.addAnswer(a2_2);
    	
    	Answer a3_1 = new Answer("Sciences", q4);
    	Answer a3_2 = new Answer("Histoire", q4);
    	q3.addAnswer(a3_1);
    	q3.addAnswer(a3_2);
    	
    	Answer a4_1 = new Answer("5 minutes");
    	Answer a4_2 = new Answer("10 minutes");
    	Answer a4_3 = new Answer("30 minutes");
    	q4.addAnswer(a4_1);
    	q4.addAnswer(a4_2);
    	q4.addAnswer(a4_3);
    	
    	this.curQuestion = q1;
    	/////////////////////////////
    	
    	this.questionLabel.setText(this.curQuestion.getQuestion());
    	for(int i=0;i<this.curQuestion.getAnswers().size();i++)
    		this.buttonsContainer.getChildren().add(new MyFormButton(this.curQuestion.getAnswers().get(i).getAnswer()));
    	
    	/*
    	this.searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                search();
            }
        });
        */
    }
}
