package AnexoVI;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcceoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "sample";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	private DatabaseMetaData metadata;
	public Connection conecta;
	
	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	public void desconectar() throws SQLException {
		if(conecta != null)
			conecta.close();
	}
	public void columnasMetadata(String catalogo, String tabla) {
		try {
			metadata = conecta.getMetaData();
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
