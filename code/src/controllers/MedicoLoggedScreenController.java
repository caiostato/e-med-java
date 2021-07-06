package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Medico;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caios
 */
public class MedicoLoggedScreenController implements Initializable{
 
    @FXML
    Button buttonSair;
    @FXML
    Button buttonSolicitar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void clickButtonSolicitar() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MedicoSolicitarScreen.fxml"));
	    Stage window = (Stage)this.buttonSolicitar.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }
    
    public void clickButtonSair() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MainPage.fxml"));
	    Stage window = (Stage)this.buttonSair.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }
    
}
