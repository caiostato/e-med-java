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

import conexao.ConexaoBD;

/**
 *
 * @author caios
 */
public class Farmaceutico extends Funcionario{
	
	private String crf;
	private String password;

    //construtor
	public Farmaceutico(String crf, String password) {
		this.setCrf(crf);
		this.setPassword(password);
	}
    public Farmaceutico(String crf, String password,String nome, String cpf, String email) {
        super(nome,cpf,email);
    }
    
    //functions
	@Override
	public boolean login(){
		String login = this.crf;
		String password = this.password;
		
        boolean result = false;
        String sql = "";
        
        try {
        
        Connection conn = ConexaoBD.conector();

        sql = "SELECT * FROM farmaceutico where crf=? AND farmaceutico_senha =?";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, password);

        ResultSet rs;
        rs = ps.executeQuery();

        if (rs.next()) {
            String user = rs.getString("crf");
            String pass = rs.getString("farmaceutico_senha");
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
    
    
    public void cadastraMedicamento(Medicamento med){
        //executa cadastramento
        
    }

	public String getCrf() {
		return crf;
	}

	private void setCrf(String crf) {
		this.crf = crf;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}
    
}
