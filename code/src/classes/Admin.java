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
public class Admin extends Funcionario{

	private String username;
	private String password;
	
    //construtor
	public Admin() {
		
	}
    public Admin(String username, String password) {
    	this.setUsername(username);
    	this.setPassword(password);
    }
	
	public Admin(String username, String password,String nome, String cpf, String email) {
        super(nome,cpf,email);
    }
    
    //functions
    public void addConta(Medico func){ 
		
		try {
			Connection conn = ConexaoBD.conector();
			Statement st = conn.createStatement();
			st.executeQuery("call insert_adm(1,"+func.getCrm()+",'"+func.getPassword()+"','"+func.getEmail()+"','"+func.getEmail()+"'),'"+func.getCpf()+"');");
			
			ResultSet rs =st.getResultSet();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void addConta(Farmaceutico func){ 
		
		try {
			Connection conn = ConexaoBD.conector();
			Statement st = conn.createStatement();
			st.executeQuery("call insert_adm(2,"+func.getCrf()+",'"+func.getPassword()+"','"+func.getEmail()+"','"+func.getEmail()+"'),'"+func.getCpf()+"');");
			
			ResultSet rs =st.getResultSet();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void excConta(String id,int type){
        // exclui conta
    	
		if(type == 1) {

			
			try {
				Connection conn = ConexaoBD.conector();
				Statement st = conn.createStatement();
				st.executeQuery("delete from medico where crm = "+id+";");
				ResultSet rs =st.getResultSet();
				
				conn.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}else if(type == 2) {
			
			try {
				Connection conn = ConexaoBD.conector();
				Statement st = conn.createStatement();
				st.executeQuery("delete from farmaceutico where crf = "+id+";");
				
				ResultSet rs =st.getResultSet();
				
				conn.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
    	
    	
    	
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
