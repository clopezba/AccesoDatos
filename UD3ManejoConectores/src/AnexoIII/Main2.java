package AnexoIII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main2 {
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
			
			Scanner s = new Scanner(System.in);
			System.out.println("Introduce número de categoría: ");
			int cat = s.nextInt();
			
			CallableStatement call = conecta.prepareCall("CALL productosCategoriaBajoMinimos(?)");
			call.setInt(1, cat);
			
			ResultSet res = call.executeQuery();
			if(res.next()) {
				System.out.println("Nombre Producto - Precio - Existencias - Mínimo");
				System.out.println("-------------------------------------------------");
				System.out.println(res.getString(1) + " | " + res.getDouble(2) + " | " 
						+ res.getInt(3) + " | " + res.getInt(4));
				while(res.next()) {
					System.out.println(res.getString(1) + " | " + res.getDouble(2) + " | " 
							+ res.getInt(3) + " | " + res.getInt(4));
				}
			}
			else
				System.out.println("La categoría " + cat + " no existe o no tiene productos bajo mínimos");
			
			s.close();
			call.close();
			res.close();
			conecta.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		 

	}

}
