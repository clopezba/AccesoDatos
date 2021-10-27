package AnexoIII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
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
			int porcentaje = s.nextInt();
			
			CallableStatement call = conecta.prepareCall("CALL incrementarPrecioCategoria(?,?,?)");
			call.setInt(1, cat);
			call.setInt(2, porcentaje);
			call.registerOutParameter(3, Types.INTEGER);
			call.executeQuery();
			
			if(call.getInt(3)==0) {
				System.out.println("No se actualizó ningún producto de la categoría -> " + cat);
			}
			else if(call.getInt(3)==-1){
				System.out.println("Ocurrió un error");
			}
			else {
				System.out.println("Se incrementó el precio un " + porcentaje + 
						"% para " + call.getInt(3) + " productos de la categoría -> " + cat);
			}
			call.close();
			s.close();
			conecta.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

	}

}
