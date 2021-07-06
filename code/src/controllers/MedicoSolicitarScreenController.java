package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelTables.modelTableMedicamento;

public class MedicoSolicitarScreenController implements Initializable{
	
	@FXML
	TableView<modelTableMedicamento> table;	
	@FXML
	TableColumn<modelTableMedicamento, String> col_id;	
	@FXML
	TableColumn<modelTableMedicamento, String> col_nome;	
	@FXML
	TableColumn<modelTableMedicamento, String> col_quantidade;
	@FXML
	TableColumn<modelTableMedicamento, String> col_cas;
	
	ObservableList<modelTableMedicamento> oblist = FXCollections.observableArrayList();

	@FXML
	Button buttonVoltar;	
	@FXML
	Button buttonSolicitar;	
	@FXML
	Button buttonAtivar;
	
	@FXML
	TextField txtField_med;	
	@FXML
	TextField txtField_quantidade;	
	@FXML
	TextField txtField_crm;
	
    public void initialize(URL url, ResourceBundle rb) {
    	try {
    		Connection conn = ConexaoBD.conector();
    		System.out.print("conn: "+conn);
    		ResultSet rs = conn.createStatement().executeQuery("select * from medicamento");
    		
    		while(rs.next()) {
    			oblist.add(new modelTableMedicamento(rs.getString("medicamento_id"),rs.getString("medicamento_nome"),rs.getString("medicamento_quantidade"),rs.getString("medicamento_cas")));
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	col_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    	col_cas.setCellValueFactory(new PropertyValueFactory<>("cas"));
    	
    	table.setItems(oblist);
    }
    
	public void clickButtonSolicitar() {
		
		String med = txtField_med.getText();
		String quantidade = txtField_quantidade.getText();
		
		Medico medico = new Medico();
		medico.solicitaMedicamento(med, Integer.parseInt(quantidade));
		
		txtField_med.clear();
		txtField_quantidade.clear();
		
		//to refresh
		clickButtonAtivar();	
	}
	
	public void clickButtonVoltar() throws IOException {
	    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("/fxml/MedicoLoggedScreen.fxml"));
	    Stage window = (Stage)this.buttonVoltar.getScene().getWindow();
	    window.setScene(new Scene(root, 1366, 720));
		
	}
    
	//refresh table function
	public void clickButtonAtivar() {
		table.getItems().clear();
		try {
			Connection con = ConexaoBD.conector();
			ResultSet rs = con.createStatement().executeQuery("select * from medicamento");
			
			while(rs.next()) {
				oblist.add(new modelTableMedicamento(rs.getString("medicamento_id"),rs.getString("medicamento_nome"),rs.getString("medicamento_quantidade"),rs.getString("medicamento_cas")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		col_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		col_cas.setCellValueFactory(new PropertyValueFactory<>("cas"));
		
		table.setItems(oblist);
    	
    }
	
	
	
	

}
