package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class a1_AccesoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
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
	public void consultarPorLocalidad(String localidad) {
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM socio WHERE localidad LIKE ?", 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pre.setString(1, "%" + localidad + "%");
			ResultSet res = pre.executeQuery();
			imprimirDatos(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void imprimirDatos(ResultSet rs) {
		try {
			if(rs.next()) {
				System.out.println("Id: " + rs.getInt(1) + " | Nombre: " + rs.getString(2) + " | Estatura: " 
					+ rs.getInt(3) + " cm. | Edad:" + rs.getInt(4) + " a?os | Localidad: " + rs.getString(5));
			}
			while(rs.next()) {
				System.out.println("Id: " + rs.getInt(1) + " | Nombre: " + rs.getString(2) + " | Estatura: " 
						+ rs.getInt(3) + " cm. | Edad:" + rs.getInt(4) + " a?os | Localidad: " + rs.getString(5));
			}
			rs.last();
			System.out.println("__________________________________________________________________");
			System.out.println("Total de socios: " + rs.getRow());
		} catch (SQLException e) {
			e.printStackTrace();
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
