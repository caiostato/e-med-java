package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caios
 */
public class MainPageController {
    
    @FXML
    Button buttonMedico;
    @FXML
    Button buttonFarmaceutico;
    @FXML
    Button buttonAdmin;
    @FXML
    Button buttonSair;
    @FXML
    BorderPane borderpane;
    
    public void clickButtonMedico() throws Exception{
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MedicoLoginScreen.fxml"));
        Stage window = (Stage)this.buttonMedico.getScene().getWindow();
        window.setScene(new Scene(root, 1366, 720));
    }
    
    public void clickButtonFarmaceutico() throws Exception{
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/FarmaceuticoLoginScreen.fxml"));
        Stage window = (Stage)this.buttonFarmaceutico.getScene().getWindow();
        window.setScene(new Scene(root, 1366, 720));
    }
    
    public void clickButtonAdmin() throws Exception{
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/AdminLoginScreen.fxml"));
        Stage window = (Stage)this.buttonAdmin.getScene().getWindow();
        window.setScene(new Scene(root, 1366, 720));

    }
    
    public void clickButtonSair(){
        System.exit(0);
    }
    
    
}
