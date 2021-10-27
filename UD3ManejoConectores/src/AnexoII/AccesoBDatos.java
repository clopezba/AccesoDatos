package AnexoII;

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
	
	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	public void desconectar() {
		try {
			if(conecta != null)
			conecta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Empleado busquedaPorCodigo(int numero) {
		Empleado emp = null;
		try {
			String sql = "SELECT * FROM emp WHERE EMPNO=?";
			PreparedStatement prep = conecta.prepareStatement(sql);
			prep.setInt(1, numero);
			
			ResultSet resultado = prep.executeQuery();
			if(resultado.next()) {
				emp = new Empleado();
				emp.setEmpno(resultado.getInt(1));
				emp.setNombre(resultado.getString(2));
				emp.setTrabajo(resultado.getString(3));
				emp.setJefe(resultado.getInt(4));
				emp.setAntiguedad(resultado.getDate(5));
				emp.setSalario(resultado.getDouble(6));
				emp.setComision(resultado.getDouble(7));
				emp.setDept(resultado.getInt(8));
			}
			return emp;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return emp;
		}
	}
	
	public ArrayList<Empleado> busquedaPorOficio (String oficio){
		ArrayList<Empleado> listaEmp = null;
		String sql = "SELECT * FROM emp WHERE job=?";
		try {
			listaEmp = new ArrayList<Empleado>();
			
			PreparedStatement pre = conecta.prepareStatement(sql);
			pre.setString(1, oficio);
			ResultSet res = pre.executeQuery();
			while(res.next()) {
				Empleado emp = new Empleado(res.getInt(1), res.getString(2), res.getString(3),
						res.getInt(4), res.getDate(5), res.getDouble(6), res.getDouble(7), res.getInt(8));
				listaEmp.add(emp);
			}
			return listaEmp;
		} catch (SQLException e) {
			e.printStackTrace();
			return listaEmp;
		}
	}
	
	public ArrayList<Empleado> busquedaPorAntiguedad(java.sql.Date fecha){
		ArrayList<Empleado> lista = null;
		String sql = "SELECT * FROM emp WHERE hiredate <=?";
		
		try {
			lista = new ArrayList<Empleado>();
			PreparedStatement prep = conecta.prepareStatement(sql);
			prep.setDate(1, fecha);
			ResultSet res = prep.executeQuery();
			
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
		int resultado=0;
		String sql = "INSERT INTO emp VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pre = conecta.prepareStatement(sql);
			pre.setInt(1, emp.getEmpno());
			pre.setString(2, emp.getNombre());
			pre.setString(3, emp.getTrabajo());
			pre.setInt(4, emp.getJefe());
			pre.setDate(5, emp.getAntiguedad());
			pre.setDouble(6, emp.getSalario());
			pre.setDouble(7, emp.getComision());
			pre.setInt(8, emp.getDept());
			resultado = pre.executeUpdate();			
			
			return resultado;
			
		} catch (SQLException e) {
			resultado = e.getErrorCode();
			return resultado;
		}
	}
	
	public int actualizarSalario(int departamento, double porcentaje) {
		int resultado = 0;
		String sql = "UPDATE emp SET sal=sal+sal*? WHERE deptno=?";
		try {
			PreparedStatement pre = conecta.prepareStatement(sql);
			pre.setDouble(1, porcentaje);
			pre.setInt(2, departamento);
						
			resultado = pre.executeUpdate();
			return resultado;
		} catch (SQLException e) {
			resultado = e.getErrorCode();
			return resultado;
		}		
	}
	
	public int borrarEmpleado(int numero) {
		int resultado = 0;
		String sql = "DELETE FROM emp WHERE empno=?";
		try {
			PreparedStatement pre = conecta.prepareStatement(sql);
			pre.setInt(1, numero);
			resultado = pre.executeUpdate();
			return resultado;
			
		} catch (SQLException e) {
			resultado = e.getErrorCode();
			return resultado;
		}
		
	}

}
