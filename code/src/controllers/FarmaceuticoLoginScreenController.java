package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Admin;
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

public class FarmaceuticoLoginScreenController {

	@FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;  
    @FXML
    Button buttonSair;
    @FXML
    Button buttonLogin;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void clickButtonEntrar() throws IOException{
    	String username = textField.getText();
    	String pass = passwordField.getText();
    	
        Farmaceutico farmaceutico = new Farmaceutico(username,pass);
        
        Boolean result = farmaceutico.login();
        
        if(result == true) {
		    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/FarmaceuticoLoggedScreen.fxml"));
		    Stage window = (Stage)this.buttonLogin.getScene().getWindow();
		    window.setScene(new Scene(root, 1366, 720));
        }
        else {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("ERRO");
        	alert.setHeaderText("Erro ao tentar entrar");
        	alert.setContentText("Seu usuario ou sua senha estao incorretas, tente novamente");
        	alert.showAndWait();
        	
        	textField.clear();
        	passwordField.clear();
        	
        }
    }
    
    public void clickButtonSair() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MainPage.fxml"));
	    Stage window = (Stage)this.buttonSair.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }
    
}

