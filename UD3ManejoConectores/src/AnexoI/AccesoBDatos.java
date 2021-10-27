package AnexoI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "sample";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	public Connection conecta;
	
	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	//SIN SENTENCIAS PREPARADAS
	public boolean validar(String usuario, String contrasena) {
		boolean validar= false;
		try {
			Statement stm = conecta.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM usuario WHERE username='" 
					+ usuario + "' AND password='" + contrasena + "'");
			if (rs.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validar;
	}
	public String nombreCompleto(String usuario) {
		String nombre = null;
		try {
			Statement stmn = conecta.createStatement();
			ResultSet resul = stmn.executeQuery("SELECT nombre FROM usuario WHERE username='" + usuario + "'");
			if(resul.next()) {
				nombre = resul.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	
	//CON SENTENCIAS PREPARADAS
	public boolean validarP(String user, String pass) {
		boolean validar = false;
		try {
			PreparedStatement prep = conecta.prepareStatement("SELECT * FROM usuario WHERE username=? AND password=?");
			prep.setString(1, user);
			prep.setString(2, pass);
			ResultSet res = prep.executeQuery();
		
			if(res.next()) 
				validar = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validar;
	}
	public String dameNombre(String user) {
		String nombre = null;
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT nombre FROM usuario WHERE username=?");
			pre.setString(1, user);
			ResultSet result = pre.executeQuery();
			
			if (result.next())
				nombre = result.getString(1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	
	public void desconectar() {
		try {
			if(conecta!=null)
				conecta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
