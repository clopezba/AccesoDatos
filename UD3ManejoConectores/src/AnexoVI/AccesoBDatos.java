package AnexoVI;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AccesoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "empleados";
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
		metadata = conecta.getMetaData();
		
	}
	
	public void desconectar() throws SQLException {
		if(conecta != null)
			conecta.close();
	}
	public boolean existeBD(String catalogo, String tabla) {
		boolean existe = false;
		try {
			ResultSet tab = metadata.getTables(catalogo, null, tabla, null);
			while(tab.next()) {
				if(tab.getString(3).equals(tabla) || tab.getString(1).equals(catalogo))
					existe = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	
	public void columnasMetadata(String catalogo, String tabla) {
		try {
			if(existeBD(catalogo, tabla)){
				ResultSet res = metadata.getColumns(catalogo, null, tabla, null);
				
				String mensaje = "";
				while(res.next()) {
					mensaje += "Nombre : " + res.getString(4) + " - Tipo: " + res.getString(6) 
						+  " - Tamaño: " + res.getInt(7) + " - ¿Admite nulos? -> " + res.getString(18) + "\n"; 
				}
				mensaje += "---------------------------------------------------\n Clave Primaria: ";
				
				ResultSet resul = metadata.getPrimaryKeys(catalogo, null, tabla);
		
				while(resul.next()) {
					mensaje += resul.getString(4) + " ";
				}
				
				JOptionPane.showMessageDialog(null, mensaje, "Columnas de la tabla " + tabla + " de la BD " 
						+ catalogo, JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "No existe la BD " + catalogo + " o la tabla " 
						+ tabla, "Columnas de la tabla " + tabla + " de la BD " + catalogo, JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rsMeta() {
		 try {
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM empleados");
			ResultSet res = pre.executeQuery();
			for (int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
				System.out.println(res.getMetaData().getColumnName(i) + " - " + res.getMetaData().getColumnTypeName(i)
						+ " - " + res.getMetaData().getColumnDisplaySize(i));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
}
