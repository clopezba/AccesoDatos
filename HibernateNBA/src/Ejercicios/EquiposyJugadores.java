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
		
		String hql = "select j.nombre, j.codigo, avg(e.puntosPorPartido) from Jugadores as j left join "
				+ "j.estadisticases as e";
		Query cons = session.createQuery(hql);
		Iterator it = cons.iterate();
		
		while(it.hasNext()) {
			Object[] par = (Object[]) it.next();
			Jugadores jug = (Jugadores) par[0];
			Estadisticas est = (Estadisticas) par[1];
			
			System.out.println("Jugador: "+ jug.getCodigo() + ", "+ jug.getNombre());
			System.out.println("Puntos: " + est.getPuntosPorPartido());
		}
		session.close();
		sesion.close();
	}

}
