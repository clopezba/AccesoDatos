package CallableStatement;

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
		Connection conecta;
		
		try {
			Class.forName(driver);
			conecta= DriverManager.getConnection(url, username, password);
			
			Scanner s = new Scanner(System.in);
			System.out.println("Introduce n�mero de categor�a: ");
			int cat = s.nextInt();
			System.out.println("Introduce porcentaje: ");
			int porcentaje = s.nextInt();
			s.close();
			
			
			CallableStatement call =conecta.prepareCall("CALL incrementarPrecioCategoria(?,?,?)");
			call.setInt(1, cat);
			call.setInt(2, porcentaje);
			call.registerOutParameter(3, Types.INTEGER);
			
			call.executeQuery();
			
			if(call.getInt(3)==-1) {
				System.out.println("Se ha producido un error");
			}
			else if(call.getInt(3)==0) {
				System.out.println("No se actualiz� nung�n producto de la categor�a -> " + cat);
			}
			else {
				System.out.println("Se increment� el precio un " + porcentaje + "% para " + call.getInt(3) + " productos de la categor�a " + cat);
			}
	
			
			
			if (conecta!=null)
				conecta.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
