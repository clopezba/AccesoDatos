import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import primero.Departamentos;
import primero.Empleados;
import primero.SessionFactoryUtil;

public class PruebasExamen {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		// ---------INSERTAR EMPLEADO CON SAVE-------
//		try {
//			Departamentos dep = (Departamentos) session.load(Departamentos.class, (byte) 20);
//			Empleados emp = new Empleados((short)1234, dep, "Martinez", "Vendedor", (short)7566, Date.valueOf("2021-12-10"), (float)2100.2, (float)0);
//			session.save(emp);
//			try {
//				tx.commit();
//				System.out.println("Empleado añadido correctamente");
//			}catch (Exception e) {
//				System.out.println("Error en la inserción");
//			}
//			
//		}catch (ObjectNotFoundException e){
//			System.out.println("No existe el departamento introducido");
//		}
//		catch (Exception ex) {
//			System.out.println("Error desconocido");
//			ex.printStackTrace();
//		}

		// ----------ACTUALIZAR EMPLEADO CON UPDATE--------
//		Empleados emp = (Empleados) session.get(Empleados.class, (short)1334);
//		if(emp == null) {
//			System.out.println("No existe un empleado con ese id");
//		}
//		else {
//			float comision = emp.getComision();
//			comision += 1200;
//			emp.setComision(comision);
//			session.update(emp);
//			try {
//				tx.commit();
//				System.out.println("Empleado actualizado");
//			}catch (Exception e) {
//				System.out.println("Error al actualizar el empleado");
//				e.printStackTrace();
//			}
//		}

		// -------BORRAR EMPLEADO CON DELETE---------

//		Empleados emp = (Empleados) session.get(Empleados.class, (short) 1234);
//		if (emp == null) {
//			System.out.println("No existe el empleado introducido");
//		} else {
//			session.delete(emp);
//			try {
//				tx.commit();
//			} catch (Exception e) {
//				System.out.println("Error al eliminar el empleado");
//				e.printStackTrace();
//			}
//		}

		// ------- ELIMINAR EMPLEADO QUE NO ES DIRECTOR ------
//		short numEmp = 1234;
//		Empleados emp = (Empleados) session.get(Empleados.class, numEmp);
//		Query q = session.createQuery("from Empleados where dir = :director")
//				.setShort("director", numEmp);
//		List<Empleados> lista = q.list();
//		
//		if (!lista.isEmpty()) {
//			System.out.println("No se puede borrar");
//		} else {
//			
//			session.delete(emp);
//			try {			
//				tx.commit();
//				System.out.println("Empleado borrado correctamente");
//				}catch (Exception e) {
//					System.out.println("Error al borrar");
//					e.printStackTrace();
//				}
//			}

		// ----------UNIQUERESULT-------
//		Empleados emp = (Empleados) session.createQuery("from Empleados where apellido = :apel")
//				.setString("apel", "GIL").uniqueResult();

		// ----------UPDATE EN CONSULTA-----
//		Query q = session.createQuery("update Empleados set comision = 2000 where departamentos.deptNo = :num");
//		q.setParameter("num", (byte) 10);
//		q.executeUpdate();
//		tx.commit();

		// ---------DELETE EN CONSULTA-----
//		int q = session.createQuery("delete Empleados where oficio = :oficio")
//			.setString("oficio", "ANALISTA").executeUpdate();
//		tx.commit();
//		System.out.println(q);

		// ----------LISTAS--------
//		Query q = session.createQuery("from Empleados");
//		List<Empleados> lista = q.list();
//		Iterator<Empleados> it = lista.iterator();
//		while(it.hasNext()) {
//			Empleados emp = it.next();
//			System.out.println(emp.getApellido());
//		}

		// -----------ITERATE-------
//		Query q = session.createQuery("from Empleados");
//		Iterator<Empleados> it = q.iterate();
//		while(it.hasNext()) {
//			Empleados emp = it.next();
//			System.out.println(emp.getApellido());
//		}

		// ----------LISTA PARÁMETROS-------
//		List<String> listaOficio = new ArrayList<String>();
//		listaOficio.add("DIRECTOR");
//		listaOficio.add("PRESIDENTE");
//		Query q = session.createQuery("from Empleados where oficio not in (:listaOfi)");
//		q.setParameterList("listaOfi", listaOficio);
//		Iterator<Empleados> it = q.iterate();
//		while(it.hasNext()) {
//			Empleados emp = it.next();
//			System.out.println(emp.getApellido());
//		}

		// -----------CONSULTAS NO ASOCIADAS-------
//		Query q = session.createQuery("from Departamentos d, Empleados e where d.deptNo = e.departamentos.deptNo");
//		Iterator<Object[]> it = q.iterate();
//		while(it.hasNext()) {
//			Object[] par = it.next();
//			Departamentos dep = (Departamentos) par[0];
//			Empleados emp = (Empleados) par[1];
//			
//			System.out.println(dep.getDeptNo() + "--" + emp.getApellido());
//		}

//		Query q = session.createQuery("select avg(salario) from Empleados where departamentos.deptNo = ?")
//				.setShort(0, (short) 10);
//		Double media = (Double) q.uniqueResult();
//		System.out.println(media);

//		Query q = session.createQuery("select avg(comision), count(apellido) from Empleados where comision != null");
//		Object[] lista = (Object[]) q.uniqueResult();
//		Double media = (Double) lista[0];
//		Long contador = (Long) lista[1];
//		System.out.println(media + "--" + contador);

//		Query q = session.createQuery("from Departamentos d left join d.empleadoses");
//		List<Object[]> lista = q.list();
//
//		for (Object[] objects : lista) {
//			Departamentos dep = (Departamentos) objects[0];
//			Empleados emp = (Empleados) objects[1];
//			if (emp == null) {
//				System.out.println(dep.getDeptNo() + " -- SIN EMPLEADOS ");
//			} else {
//				System.out.println(dep.getDeptNo() + " -- " + emp.getApellido());
//			}
//		}
		
		//------CONSULTA SQL-----
//		Departamentos q = (Departamentos) session
//				.createSQLQuery("SELECT dept_no, dnombre, loc FROM departamentos WHERE dept_no = :num")
//				.addEntity(Departamentos.class).setShort("num", (short) 10).uniqueResult();
//		System.out.printf("%d\t%s\t%s%n", q.getDeptNo(), q.getDnombre(), q.getLoc());
		
//		int q = session.createSQLQuery("INSERT INTO departamentos VALUES (:dept, :nom, :loc)")
//				.setShort("dept", (short) 50)
//				.setString("nom", "DESARROLLO")
//				.setParameter("loc", "CUENCA")
//				.executeUpdate();
//		tx.commit();
//		System.out.println("Filas añadidas: " + q);
		
		

		session.close();
		sesion.close();

	}

}
