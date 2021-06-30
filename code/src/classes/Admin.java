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
public class Admin extends Funcionario{

	private String username;
	private String password;
	
    //construtor
    public Admin(String username, String password) {
    	this.setUsername(username);
    	this.setPassword(password);
    }
	
	public Admin(String username, String password,String nome, String cpf, String email) {
        super(nome,cpf,email);
    }
    
    //functions
    void addConta(Funcionario func, Integer type){
        //adiciona conta
    }
    
    void excConta(Funcionario func, Integer type){
        // exclui conta
    }
    
    @Override
	public boolean login(){
		String login = this.username;
		String password = this.password;
		
        boolean result = false;
        String sql = "";
        
        try {
        
        Connection conn = ConexaoBD.conector();

        sql = "SELECT * FROM adm where adm_login=? AND adm_senha =?";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, password);

        ResultSet rs;
        rs = ps.executeQuery();

        if (rs.next()) {
            String user = rs.getString("adm_login");
            String pass = rs.getString("adm_senha");
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
    
	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	};
    
}
