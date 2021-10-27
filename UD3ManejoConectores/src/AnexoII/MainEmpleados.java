package AnexoII;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainEmpleados {

	public static void main(String[] args) {
		AccesoBDatos abd = new AccesoBDatos();
		try {
			//b. Métodos conectar y desconectar
			abd.conectar();
			
			//c. Buscar empleado por código
			System.out.println(abd.busquedaPorCodigo(100));
			System.out.println(abd.busquedaPorCodigo(7788));
			
			//d. Empleados por oficio
			System.out.println(abd.busquedaPorOficio("Barrendero"));
			System.out.println(abd.busquedaPorOficio("CLERK"));
			
			//e. Empleados por antigüedad
			System.out.println("Empleados contratados antes del 22 de febrero de 1981");
			System.out.println("=====================================================");
			java.util.Date fecha = null;
			java.sql.Date sqlFecha = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			try {
				fecha = sdf.parse("2020/03/18");
				sqlFecha = new java.sql.Date(fecha.getTime());
			} catch (ParseException e) {
				System.out.println("Error al convertir fecha");
			}
			System.out.println(abd.busquedaPorAntiguedad(sqlFecha));
			
			
			//f. Insertar usuario
			Empleado e1 = new Empleado(5, "LOPEZ", "ALUMNO", 1, sqlFecha, 1000.0, 0, 10);
			System.out.println(abd.insertarConBean(e1));
			System.out.println(abd.insertarConBean(e1));
			
			//i. Actualizar salario
			System.out.println("Subir un 20% el salario a los empleados del departamento 30");
			System.out.println(abd.actualizarSalario(30, 0.2));
			System.out.println("Subir un 15% el salario a los empleados del departamento 44");
			System.out.println(abd.actualizarSalario(44, 0.15));
			
			//j. Borrar empleado
			System.out.println(abd.borrarEmpleado(5));
			System.out.println(abd.borrarEmpleado(99));
			System.out.println(abd.borrarEmpleado(7839));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

	}

}
