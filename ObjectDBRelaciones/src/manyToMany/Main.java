package manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		/* Crear EntityManagerFactory */ 
		EntityManagerFactory emf = Persistence .createEntityManagerFactory ("db/manytomany.odb");

	    /* Create EntityManager */
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction transaction = em.getTransaction();

	    transaction.begin();

	    Empleado cristina = new Empleado(0, "Cristina López");
//	    prasad.setNombre("prasad kharkar");

	    Empleado harish = new Empleado();
	    harish.setNombre("Harish taware");

	    Proyecto ceg = new Proyecto(0, "CEG");
//	    ceg.setNombre("CEG");

	    Proyecto gtt = new Proyecto(0, "GTT");
//	    gtt.setNombre("GTT");

	    List<Proyecto> proyectos = new ArrayList<Proyecto>();
	    proyectos.add(ceg);
	    proyectos.add(gtt);

	    List<Empleado> empleados = new ArrayList<Empleado>();
	    empleados.add(cristina);
	    empleados.add(harish);

	    ceg.setEmpleados(empleados);
	    gtt.setEmpleados(empleados);

	    cristina.setProyectos(proyectos);
	    harish.setProyectos(proyectos);

	    em.persist(cristina);
	    //em.remove(cristina);
	    transaction.commit();


	}

}
