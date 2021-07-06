/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.ConexaoBD;

/**
 *
 * @author caios
 */
public class Medico extends Funcionario{

	private String crm;
	private String password;

    //construtor
	public Medico() {
	}
	public Medico(String crm, String password) {
		this.setCrm(crm);
		this.setPassword(password);
	}
	
    public Medico(String crm, String password,String nome, String cpf, String email){
        super(nome,cpf,email);
    }
    
    //functions
    public void solicitaMedicamento(String idMed, Integer quant){
		try {
			Connection conn = ConexaoBD.conector();
			Statement st = conn.createStatement();
			st.executeQuery("call solicitar('"+idMed+"',"+quant+");");
			
			ResultSet rs =st.getResultSet();
			
			conn.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
    }
    
	@Override
	public boolean login(){
		String login = this.crm;
		String password = this.password;
		
        boolean result = false;
        String sql = "";
        
        try {
        
        Connection conn = ConexaoBD.conector();

        sql = "SELECT * FROM medico where crm=? AND medico_senha =?";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, password);

        ResultSet rs;
        rs = ps.executeQuery();

        if (rs.next()) {
            String user = rs.getString("crm");
            String pass = rs.getString("medico_senha");
            result = true;
        } else {
            ps.close();
            return result;
        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
         
        return result;
	}

	public String getCrm() {
		return crm;
	}

	private void setCrm(String crm) {
		this.crm = crm;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	};
    
}
