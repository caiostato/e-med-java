package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import classes.Admin;
import classes.Farmaceutico;
import classes.Medico;
import conexao.ConexaoBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelTables.ModelTableMedico;
import modelTables.modelTableFarmaceutico;

public class AdminDeleteAccController implements Initializable{
	
	@FXML
	TextField txtF_id;
	
	@FXML
	RadioButton rButton_crm;
	@FXML
	RadioButton rButton_crf;
	
	@FXML
	Button buttonExcluir;
	@FXML
	Button buttonAtivar;
	@FXML
	Button buttonVoltar;
	
	@FXML
	TableView<ModelTableMedico> table_medico;
	@FXML
	TableColumn<ModelTableMedico, String> col_crm;
	@FXML
	TableColumn<ModelTableMedico, String> col_nome;
	@FXML
	TableView<modelTableFarmaceutico> table_farm;
	@FXML
	TableColumn<modelTableFarmaceutico, String> col_crf;	
	@FXML
	TableColumn<modelTableFarmaceutico, String> col_nome1;
	
	ObservableList<ModelTableMedico> oblist = FXCollections.observableArrayList();
	
	ObservableList<modelTableFarmaceutico> oblist1 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Connection con = ConexaoBD.conector();
			ResultSet rs = con.createStatement().executeQuery("select * from medico");
			
			while(rs.next()) {
				oblist.add(new ModelTableMedico(rs.getString("crm"),rs.getString("medico_nome")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		try {	
			Connection con1 = ConexaoBD.conector();
			ResultSet rs1 = con1.createStatement().executeQuery("select * from farmaceutico");
			
			while(rs1.next()) {
				oblist1.add(new modelTableFarmaceutico(rs1.getString("crf"),rs1.getString("farmaceutico_nome")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		col_crm.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		col_crf.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nome1.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		table_medico.setItems(oblist);
		table_farm.setItems(oblist1);
		
	}
	
	public void clickButtonExcluir() {
		String id = txtF_id.getText();
		
		boolean Bcrm = rButton_crm.isPressed();
		boolean Bcrf = rButton_crf.isPressed();
		
		Admin adm = new Admin();
		
		if(Bcrm = true) {
			adm.excConta(id,1);
		}
		else if(Bcrf = true) {
			adm.excConta(id,2);	
		}
		
		txtF_id.clear();
		clickButtonAtivar();
	}
	public void clickButtonAtivar() {
		table_medico.getItems().clear();
		table_farm.getItems().clear();
		try {
			Connection con = ConexaoBD.conector();
			ResultSet rs = con.createStatement().executeQuery("select * from medico");
			
			while(rs.next()) {
				oblist.add(new ModelTableMedico(rs.getString("crm"),rs.getString("medico_nome")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		try {	
			Connection con1 = ConexaoBD.conector();
			ResultSet rs1 = con1.createStatement().executeQuery("select * from farmaceutico");
			
			while(rs1.next()) {
				oblist1.add(new modelTableFarmaceutico(rs1.getString("crf"),rs1.getString("farmaceutico_nome")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		col_crm.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		col_crf.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nome1.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		table_medico.setItems(oblist);
		table_farm.setItems(oblist1);
		
	}
	
    public void clickButtonVoltar() throws IOException{
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/AdminLoggedScreen.fxml"));
	    Stage window = (Stage)this.buttonVoltar.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
    }


}
