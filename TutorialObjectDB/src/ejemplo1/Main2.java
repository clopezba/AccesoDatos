package ejemplo1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main2 {

	public static void main(String[] args) throws ParseException {
        
		AccesoBdatos abd = new AccesoBdatos();
		abd.conectar();
//		abd.imprimirDepartamento(90);
//		abd.imprimirDepartamento(40);
//		abd.imprimirDepartamento(10);
		
//      System.out.println(abd.insertarDepartamento(new DepartamentoEntity(60,"Recursos Humanos", "Chimillas")));
//      System.out.println(abd.insertarDepartamento(new DepartamentoEntity(60,"Recursos Humanos", "Chimillas")));
//      abd.imprimirDepartamento(60);
		
//		System.out.println(abd.modificarDepartamento(new DepartamentoEntity(88,"RRHH", "Huerrios")));
//		System.out.println(abd.modificarDepartamento(new DepartamentoEntity(60,"RRHH", "Esquedas")));
//		abd.imprimirDepartamento(60);
		
//		System.out.println(abd.borrarDepartamento(88)); // false no existe
//		System.out.println(abd.borrarDepartamento(60)); // true
//		System.out.println(abd.borrarDepartamento(10)); // false pues tiene empleados
		
//		abd.imprimirDepartamento(10);
	
//		abd.demoJPQL();
		
//		System.out.println(abd.incrementarSalario(-500));
		
//		System.out.println(abd.incrementarSalarioOficio("Empleado", 100));
		
//		System.out.println(abd.incrementarSalarioDepartamento(20, 200));
		
//		System.out.println(abd.borrarEmpleado(2066));
		
		System.out.println(abd.borrarDepartamentoNum(30));
		
		abd.desconectar();
	

	}

}

