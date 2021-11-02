package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoBDatos {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "demodb";
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
	
	public Empleado busquedaPorCodigo(int numero) {
		Empleado emp = null;
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM emp WHERE empno=?");
			pre.setInt(1, numero);
			
			ResultSet res = pre.executeQuery();
			if(res.next()) {
				emp = new Empleado(res.getInt(1), res.getString(2), res.getString(3), 
						res.getInt(4), res.getDate(5), res.getDouble(6), res.getDouble(7), res.getInt(8));
				return emp;
			}
			else
				return emp;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return emp;
		}
		
	}
	
	public ArrayList<Empleado> busquedaPorOficio (String oficio){
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		
		try {
			PreparedStatement pre = conecta.prepareStatement("SELECT * FROM emp WHERE job=?");
			pre.setString(1, oficio);
			
			ResultSet res = pre.executeQuery();
			while(res.next()) {
				Empleado emp = new Empleado(res.getInt(1), res.getString(2), res.getString(3), 
						res.getInt(4), res.getDate(5), res.getDouble(6), res.getDouble(7), res.getInt(8));
				lista.add(emp);
			}
			return lista;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return lista;
		}
		
	}
	
	public int insertarConBean(Empleado emp) {
		try {
			PreparedStatement pre = conecta.prepareStatement("INSERT INTO emp VALUES (?,?,?,?,?,?,?,?)");
			pre.setInt(1, emp.getEmpno());
			pre.setString(2, emp.getNombre());
			pre.setString(3, emp.getTrabajo());
			pre.setInt(4, emp.getJefe());
			pre.setDate(5, emp.getAntiguedad());
			pre.setDouble(6, emp.getSalario());
			pre.setDouble(7, emp.getComision());
			pre.setInt(8, emp.getDeptno());
			
			return pre.executeUpdate();
			
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
		
	}
	
	public int actualizarSalario(int departamento, double procentaje) {
		try {
			PreparedStatement pre = conecta.prepareStatement("UPDATE emp SET sal=sal+sal*? WHERE deptno=?");
			pre.setDouble(1, procentaje);
			pre.setInt(2, departamento);
			
			return pre.executeUpdate();
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
		
	}
	
	public int borrarEmpleado (int numero) {
		try {
			PreparedStatement pre = conecta.prepareStatement("DELETE FROM emp WHERE empno=?");
			pre.setInt(1, numero);
			
			return pre.executeUpdate();
			
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
		
	}
	
	public int actualizarSalarioconTransacciones(int departamento, double porcentaje) {
		int resultado = 0;
		try {
			conecta.setAutoCommit(false);
			PreparedStatement pre = conecta.prepareStatement("UPDATE emp SET sal=sal+sal*? WHERE deptno=?");
			pre.setDouble(1, porcentaje);
			pre.setInt(2, departamento);
			
			resultado = pre.executeUpdate();
			if(resultado > 0) {
				conecta.commit();
			}
			else {
				conecta.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
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
