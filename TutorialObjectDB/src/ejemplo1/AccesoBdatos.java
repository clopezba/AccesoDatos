package ejemplo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//
// Alberto Carrera Martín - Abril 2020
//

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleados.odb");
		em = emf.createEntityManager();
	}
	public void desconectar() {
		em.close();
		emf.close();
	}
	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		return em.find(DepartamentoEntity.class, numDepartamento);
	}// de método buscarDepartamento
	//
	@SuppressWarnings("deprecation")
	public void imprimirDepartamento (int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d==null)
			System.out.println("No existe el Departamento " + numDepartamento);
		else {
			Set <EmpleadoEntity> empleados =d.getEmpleados();
			String datos="Datos del departamento " + numDepartamento + ": ";
			datos+= "Nombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad()+ "\n";
			if (empleados.isEmpty())
				datos+="No tiene empleados en este momento";
			else {			
				datos+="Lista de empleados"+ "\n";
				datos+="*******************";
			}
			for (EmpleadoEntity empleado :empleados) {
				datos+= "\nNúmero de empleado: " + empleado.getEmpnoId()+ "\n";
				datos+= "Nombre: " + empleado.getNombre()+ "\n";
				datos+= "Oficio: " + empleado.getOficio()+ "\n";
				if (empleado.getDirId()==null)
					datos+= "Jefe: No tiene"+ "\n";
				else
					datos+= "Jefe: "+ empleado.getDirId().getNombre()+ "\n";
				datos+= "Año de alta: " + (empleado.getAlta().getYear()+1900)+ "\n";	
				datos+= "Salario: "+ empleado.getSalario()+ "\n";
				if (empleado.getComision() ==null)
					datos+= "Comisión: No tiene"+ "\n";
				else
					datos+= "Comisión: "+ empleado.getComision()+ "\n";
			}
			
			System.out.println(datos);
		}
	} // de método imprimirDepartamento
	
	public boolean insertarDepartamento (DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId())!=null)
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento
	
	public boolean modificarDepartamento (DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(d.getDptoId());
		if (departamentoBuscado==null)
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist (departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	// Si tiene empleados lo borraría igual, dejando en estos el número de Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo borrar
	
	public boolean borrarDepartamento  (int numDepartamento) {
		DepartamentoEntity departamentoBuscado=buscarDepartamento(numDepartamento);
		if (departamentoBuscado==null || !departamentoBuscado.getEmpleados().isEmpty() )
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento
	
	private void imprimirResultado (List<Object[]> l) {
		//String texto = "";
		for (Object[] obj : l) {
			for (Object ob : obj) {
				System.out.print(ob + " - ");
			}
			System.out.println("");
		}
	}
	
	public void demoJPQL() {
		
		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
        System.out.println("Total Departamentos: " + q1.getSingleResult());
        //
        TypedQuery<Long> tq1 = em.createQuery(
        	      "SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
        System.out.println("Total Departamentos: " + tq1.getSingleResult());
        //
        TypedQuery<DepartamentoEntity>tq2 =
	            em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
	        List<DepartamentoEntity> l2 = tq2.getResultList();
	        for (DepartamentoEntity r2 : l2) {
	            System.out.println("Nombre :  " + r2.getNombre()+ ", Localidad: "+ r2.getLocalidad());
	        }
	    
	        //
        TypedQuery<Object[]>tq3 =
	            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d", Object[].class);
	        List<Object[]> l3 = tq3.getResultList();
	        for (Object[] r3 : l3) {
	            System.out.println(
	            "Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
	    }    
	    
	    //
	      TypedQuery<Object[]>tq4 =
		            em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity d"
		            		+ " WHERE d.dptoId != :n", Object[].class);
	        		tq4.setParameter("n", 10);
		        List<Object[]> l4 = tq4.getResultList();
		        for (Object[] r4 : l4) {
		            System.out.println(
		            "Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		    }     
	     
		//Nombre y fecha de alta de todos los empleados
	    TypedQuery<Object[]> con1 = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e", Object[].class);
	    List<Object[]> lista1 = con1.getResultList();
	    for (Object[] obj : lista1) {
	    	System.out.println(obj[0] + " - " + obj[1]);
		}
	     
	    //Aquellos en que Carrera esté en el nombre
	    TypedQuery<Object[]> con2 = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e "
	    		+ "where UPPER(e.nombre) LIKE '%CARRERA%'", Object[].class);
	    List<Object[]> lista2 = con2.getResultList();
	    for (Object[] obj : lista2) {
	    	System.out.println(obj[0] + " - " + obj[1]);
		}
		
		
		//Departamento I+D cuyo oficio es Empleado
		TypedQuery<Object[]> con3 = em.createQuery("SELECT e.nombre, e.oficio, e.departamento.nombre "
				+ "FROM EmpleadoEntity e where e.departamento.nombre = 'I+D' AND e.oficio = 'Empleado'", Object[].class);
		List<Object[]> lista3 = con3.getResultList();
		imprimirResultado(lista3);
		
		//Empleados contratados a partir del 2003
		TypedQuery<Object[]> con4 = em.createQuery("SELECT e.nombre, e.alta FROM EmpleadoEntity e "
				+ "WHERE year(e.alta) >= 2003", Object[].class);
		List<Object[]> lista4 = con4.getResultList();
		imprimirResultado(lista4);
		
		//Empleados por orden alfabético de departamento
		TypedQuery<Object[]> con5 = em.createQuery("SELECT e.departamento.dptoId, e.nombre "
				+ "FROM EmpleadoEntity e ORDER BY e.departamento.nombre", Object[].class);
		List<Object[]> lista5 = con5.getResultList();
		imprimirResultado(lista5);
		
		//Nombre, nº empleados, total y max salario de departamentos con empleados
		TypedQuery<Object[]> con6 = em.createQuery("SELECT e.departamento.nombre, count(e.nombre), "
				+ "sum(salario), max(salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING count(e.nombre) > 0", Object[].class);
		List<Object[]> lista6 = con6.getResultList();
		imprimirResultado(lista6);
		
		//Igual que antes pero con departamentos de más de 5 empleados
		TypedQuery<Object[]> con7 = em.createQuery("SELECT e.departamento.nombre, count(e.nombre), "
				+ "sum(salario), max(salario) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING count(e.nombre) >= 5", Object[].class);
		List<Object[]> lista7 = con7.getResultList();
		imprimirResultado(lista7);
		
		//Cada empleado con su jefe
		TypedQuery<Object[]> con8 = em.createQuery("SELECT e.nombre, e.dirId.nombre, e.departamento.dptoId FROM EmpleadoEntity e", Object[].class);
		List<Object[]> lista8 = con8.getResultList();
		for (Object[] obj : lista8) {
			System.out.println(obj[0] + " - su jefe es - " + obj[1] + " - departamento - " + obj[2]);
		}
		
		//Nombre y total empleados de departamentos con empleados
		TypedQuery<Object[]> con9 = em.createQuery("SELECT e.departamento.nombre, count(e.nombre) FROM EmpleadoEntity e GROUP BY e.departamento.nombre HAVING count(e.nombre) > 0", Object[].class);
		List<Object[]> lista9 = con9.getResultList();
		imprimirResultado(lista9);
		
		//Nombre y total de empleados de TODOS los departamentos
		TypedQuery<DepartamentoEntity> con10 = em.createQuery("SELECT d FROM DepartamentoEntity d", DepartamentoEntity.class);
		List<DepartamentoEntity> lista10 = con10.getResultList();
		for (DepartamentoEntity dep : lista10) {
			System.out.println(dep.getNombre() + " - " + dep.getEmpleados().size());
		}
		
		//Ordenar desc por departamento y asc por salario
		TypedQuery<EmpleadoEntity> con11 = em.createQuery("SELECT e FROM EmpleadoEntity e ORDER BY e.departamento.dptoId DESC, e.salario ASC", EmpleadoEntity.class);
		List<EmpleadoEntity> lista11 = con11.getResultList();
		for (EmpleadoEntity emp : lista11) {
			System.out.println(emp.getDepartamento().getDptoId() + " - " + emp.getNombre() + " - " + emp.getSalario());
		}
 		
		//Empleados sin jefe
		TypedQuery<Object[]> con12 = em.createQuery("SELECT e.empnoId, e.nombre "
				+ "FROM EmpleadoEntity e WHERE e.dirId is null", Object[].class);
		List<Object[]> lista12 = con12.getResultList();
		imprimirResultado(lista12);
		
		//Departamento al que pertenece el empleado 1039
		TypedQuery<Object[]> con13 = em.createQuery("SELECT d.dptoId, d.nombre FROM DepartamentoEntity d WHERE d.empleados.empnoId = 1039", Object[].class);
		List<Object[]> lista13 = con13.getResultList();
		imprimirResultado(lista13);
		
	}// de demoJPQL
	
	public int incrementarSalario(int cantidad) {
		int resultado = 0;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("UPDATE EmpleadoEntity SET salario = salario + :num");
			q.setParameter("num", cantidad);
			resultado = q.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en la actualización " + e.getMessage());
			em.getTransaction().rollback();
		}
		return resultado;
	}
	
	public int incrementarSalarioOficio (String oficio, int cantidad) {
		int resultado = 0;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario + :num "
					+ "WHERE e.oficio = :oficio");
			q.setParameter("num", cantidad);
			q.setParameter("oficio", oficio);
			resultado = q.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error en la actualización " + e.getMessage());
			em.getTransaction().rollback();
		}
		return resultado;
	}
	
	public int incrementarSalarioDepartamento (int numDepartamento, int cantidad) {
		int resultado = 0;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("UPDATE EmpleadoEntity e SET e.salario = e.salario + :num WHERE e.departamento.getDptoId() = :dep");
			q.setParameter("num", cantidad);
			q.setParameter("dep", numDepartamento);
			resultado = q.executeUpdate();
			em.getTransaction().commit();
			return resultado;
		} catch (Exception e) {
			System.out.println("Error en la actualización ");
			e.printStackTrace();
			em.getTransaction().rollback();
			return resultado;
		}
		
	}
	
	public int borrarEmpleado (int numEmpleado) {
		int resultado = 0;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("DELETE FROM EmpleadoEntity WHERE empnoId = :num");
			q.setParameter("num", numEmpleado);
			resultado = q.executeUpdate();
			
			Query qu = em.createQuery("UPDATE EmpleadoEntity e SET e.dirId = null WHERE e.empnoId = :num");
			qu.setParameter("num", numEmpleado);
			qu.executeUpdate();
			em.getTransaction().commit();
			return resultado;
		
		} catch (Exception e) {
			System.out.println("Error en el borrado");
			e.printStackTrace();
			em.getTransaction().rollback();
			return resultado;
		}
	}
	
	public int borrarDepartamentoNum(int numDepartamento) {
		int resultado = 0;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery("DELETE FROM DepartamentoEntity d WHERE d.dptoId = :num");
			q.setParameter("num", numDepartamento);
			resultado = q.executeUpdate();
			em.getTransaction().commit();
			return resultado;
		}catch (Exception e) {
			System.out.println("Error en el borrado");
			e.printStackTrace();
			em.getTransaction().rollback();
			return resultado;
		}
	}
//--------------------------------------------------------------------------------------------------------------
	
	
} // de la clase
