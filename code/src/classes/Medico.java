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
public class Medico extends Funcionario{

	private int crm;
	private String password;

    //construtor
    public Medico(String crm, String password,String nome, String cpf, String email) {
        super(nome,cpf,email);
    }
    
    //functions
    public void solicitaMedicamento(String idMed, Integer quant){
        //executa solicitamento    
    }
    
	@Override
    boolean login(String login,String password){
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

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};
    
}
