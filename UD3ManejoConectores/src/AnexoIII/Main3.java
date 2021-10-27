package AnexoIII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main3 {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "pedidos";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	
	public static void main(String[] args) {
		try {
			Class.forName(driver);
			Connection conecta = DriverManager.getConnection(url, username, password);
			
			Scanner s  = new Scanner(System.in);
			System.out.println("Introduce número de categoría: ");
			int cat = s.nextInt();
			System.out.println("Introduce porcentaje: ");
			int procentaje = s.nextInt();
			
			CallableStatement call = conecta.prepareCall("CALL incrementarPrecioCategoria(?,?,?)");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

	}

}
