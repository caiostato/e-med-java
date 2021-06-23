package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	private static final String url = "jdbc:mysql://localhost:3306/emedjava";
	private static final String user = "root";
	private static final String password = "";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public static Connection conector() {
		
		java.sql.Connection conexao = null;
		
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(url,user,password);;
			return conexao;
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
