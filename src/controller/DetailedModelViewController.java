/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Bio;

/**
 * FXML Controller class
 *
 * @author JUJU
 */
public class DetailedModelViewController  {

    @FXML
    private Button backButton;
    @FXML
    private Label labelName;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelSchoolyear;
    @FXML
    private Label labelMajor;
    @FXML
    private Label labelMinor;
    @FXML
    private ImageView image;
    
    

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (previousScene != null) {
           stage.setScene(previousScene);
       }
    }
    
    Bio selectedUser;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);
    }
    
     public void initData(Bio model) {
        selectedUser = model;
        labelName.setText(model.getName());
        labelAge.setText(String.valueOf(model.getAge()));
        labelSchoolyear.setText(model.getSchoolyear());
        labelMajor.setText(model.getMajor());
        labelMinor.setText(model.getMinor());
        
        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + model.getId() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelName != null : "fx:id=\"labelName\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelAge != null : "fx:id=\"labelAge\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelSchoolyear != null : "fx:id=\"labelSchoolyear\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelMajor != null : "fx:id=\"labelMajor\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelMinor != null : "fx:id=\"labelMinor\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        
        backButton.setDisable(true);
    }
}
   
    

