package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class a2_AccesoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "sample";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	public Connection conecta;
	
	public void conectar() {
		try {
			Class.forName(driver);
			conecta= DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validar(String user, String pass) {
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM usuario WHERE username=? AND password=?");
			pre.setString(1, user);
			pre.setString(2, pass);
			
			ResultSet res = pre.executeQuery();
			if(res.next()) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public String nombreCompleto(String user) {
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT nombre FROM usuario WHERE username=?");
			pre.setString(1, user);
			ResultSet res = pre.executeQuery();
			
			if(res.next()) {
				return res.getString(1);
			}
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void desconectar() {
		try {
			if (conecta!=null)
				conecta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
