package Ejercicios;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primeroNBA.Equipos;
import primeroNBA.Estadisticas;
import primeroNBA.Jugadores;
import primeroNBA.SessionFactoryUtil;

public class EquiposyJugadores {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Equipos eq = new Equipos();
		Query qu = session.createQuery("from Equipos");
		List<Equipos> lisEq = qu.list();
		Iterator<Equipos> it = lisEq.iterator();
		while(it.hasNext()) {
			eq = it.next();
			System.out.println("Equipo: " + eq.getNombre());
			String hql = "select j.nombre, j.codigo, avg(e.puntosPorPartido) from Jugadores as j join "
					+ "j.estadisticases as e group by j.nombre" ;
			Query cons = session.createQuery(hql);
			List<Object[]> lista = cons.list();
			System.out.println(lista.size());
			for (int i = 0; i < lista.size(); i++) {
				Object[] par = (Object[]) lista.get(i);
				
				
				System.out.println("Jugador: "+ par[1] + ", "+ par[0]);
				System.out.println("Puntos: " + par[2]);

			}
		}
		
		
		session.close();
		sesion.close();
	}

}
