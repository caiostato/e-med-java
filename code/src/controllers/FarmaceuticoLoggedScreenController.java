package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Farmaceutico;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FarmaceuticoLoggedScreenController {

    @FXML
    Button buttonSair;
    @FXML
    Button buttonCadastrar;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void clickButtonCadastrar() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/FarmaceuticoCadastrarScreen.fxml"));
	    Stage window = (Stage)this.buttonCadastrar.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }
    
    public void clickButtonSair() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MainPage.fxml"));
	    Stage window = (Stage)this.buttonSair.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }
    
}

