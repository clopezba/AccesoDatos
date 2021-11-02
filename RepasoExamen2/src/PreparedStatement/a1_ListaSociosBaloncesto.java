package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class a1_ListaSociosBaloncesto {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	
	
	public static void main(String[] args) {
		Connection conecta;
		try {
			Class.forName(driver);
			conecta= DriverManager.getConnection(url, username, password);
			
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM socio", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet res = pre.executeQuery();
			
			System.out.println("LISTA DE SOCIOS");
			System.out.println("===============");
			while(res.next()) {
				System.out.println("Id: " + res.getInt(1) + " | Nombre: " + res.getString(2) + " | Estatura: " 
			+ res.getInt(3) + " cm. | Edad: " + res.getInt(4) + " años | Localidad: " + res.getString(5));
			}
			res.last();
			int total = res.getRow();
			System.out.println("______________________________________________________________________");
			System.out.println("Total de socios: " + total);
			
			
			conecta.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
