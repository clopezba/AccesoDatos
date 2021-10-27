package AnexoIII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Main1 {
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
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce número de pedido: ");
			int pedido = sc.nextInt();
			
			CallableStatement call = conecta.prepareCall("SELECT importePedido(?)");
			call.setInt(1, pedido);
			
			ResultSet res = call.executeQuery();
			if(res.next()) {
				if(res.getDouble(1)!= -1)
					System.out.println("El total del pedido " + pedido + " es " + res.getDouble(1));
				else
					System.out.println("El número de pedido " + pedido + " no existe" );
			}
			
			sc.close();
			call.close();
			res.close();
			conecta.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
