package CRUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {

	public static void main(String[] args) {
		AccesoBDatos abd = new AccesoBDatos();
		abd.conectar();
		
		//System.out.println(abd.busquedaPorCodigo(100));
		//System.out.println(abd.busquedaPorCodigo(7788));
		
		//System.out.println(abd.busquedaPorOficio("Profesor"));
		//System.out.println(abd.busquedaPorOficio("CLERK"));
		
		java.util.Date fecha = null;
		java.sql.Date sqlFecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			fecha = sdf.parse("2020/03/18");
			sqlFecha = new java.sql.Date(fecha.getTime());
		} catch (ParseException e) {
			System.out.println("Error al convertir fecha");
		}
	//	Empleado e1 = new Empleado(22, "LOPEZ", "ALUMNO", 1, sqlFecha, 1000.0, 0, 10);
	//	System.out.println(abd.insertarConBean(e1));
	//	System.out.println(abd.insertarConBean(e1));
		
	//	System.out.println("Subir un 20% el salario de los empleados del departamento 30");
	//	System.out.println(abd.actualizarSalario(30, 0.2));
		
	//	System.out.println("Subir un 15% el salario de los empleados del departamento 44");
	//	System.out.println(abd.actualizarSalario(44, 0.15));
		
	//	System.out.println(abd.borrarEmpleado(22));
	//	System.out.println(abd.borrarEmpleado(99));
	//	System.out.println(abd.borrarEmpleado(7698));
		
	//	System.out.println(abd.actualizarSalarioconTransacciones(10, 0));
		abd.desconectar();

	}

}
