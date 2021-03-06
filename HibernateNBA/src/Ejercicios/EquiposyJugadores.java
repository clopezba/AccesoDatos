package Ejercicios;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primeroNBA.Equipos;
import primeroNBA.SessionFactoryUtil;

public class EquiposyJugadores {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();

		Equipos eq = new Equipos();
		Query qu = session.createQuery("from Equipos");
		List<Equipos> lisEq = qu.list();
		Iterator<Equipos> it = lisEq.iterator();
		
		System.out.println("N?mero de Equipos: " + lisEq.size());
		System.out.println("==========================================");
		
		while (it.hasNext()) {
			eq = it.next();
			System.out.println("Equipo: " + eq.getNombre());
			String hql = "select j.nombre, j.codigo, avg(e.puntosPorPartido) from Equipos as es join es.jugadoreses as j join "
					+ "j.estadisticases as e where es.nombre='" + eq.getNombre() + "' group by j.nombre";
			Query cons = session.createQuery(hql);
			List<Object[]> lista = cons.list();
			for (int i = 0; i < lista.size(); i++) {
				Object[] par = (Object[]) lista.get(i);
				System.out.printf("%d, %s: %.2f%n", par[1], par[0], par[2]);
			}
			System.out.println("==========================================");
		}
		session.close();
		sesion.close();
	}

}
