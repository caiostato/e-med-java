package controllers;

import Factory.FarmaceuticoFactory;
import Factory.MedicoFactory;
import classes.Admin;
import classes.Farmaceutico;
import classes.Medico;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AdminCreateAccController {
	
	@FXML
	TextField txtF_nome;
	@FXML
	TextField txtF_email;	
	@FXML
	TextField txtF_cr;	
	@FXML
	PasswordField passF_senha;	
	@FXML
	TextField txtF_cpf;
	
	@FXML
	RadioButton rButton_crm;	
	@FXML
	RadioButton rButton_crf;	
	
	@FXML
	Button buttonCreate;
	@FXML
	Button buttonVoltar;
	
	public void clickButtonCreate() {
		
		String nome = txtF_nome.getText();
		String email = txtF_email.getText();
		String cr = txtF_cr.getText(); 
		String senha = passF_senha.getText();
		String cpf = txtF_cpf.getText();
		
		boolean Bcrm = rButton_crm.isPressed();
		boolean Bcrf = rButton_crf.isPressed();
		Admin adm = new Admin();
		
		if(Bcrm = true) {
			MedicoFactory medFactory = new MedicoFactory();
			Medico medico = medFactory.criarFuncionario(cr, senha, nome, cpf, email);
			adm.addConta(medico);
		}
		else if(Bcrf = true) {
			FarmaceuticoFactory farmFactory = new FarmaceuticoFactory();
			Farmaceutico farmaceutico = farmFactory.criarFuncionario(cr, senha, nome, cpf, email);
			adm.addConta(farmaceutico);	
		}
		txtF_nome.clear();
		txtF_email.clear();
		txtF_cr.clear();
		passF_senha.clear();
		txtF_cpf.clear();		
	}
}
