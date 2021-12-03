package Ejercicios;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primeroNBA.Jugadores;
import primeroNBA.SessionFactoryUtil;
import primeroNBA.Estadisticas;

public class EjEstadisticas {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el número del jugador: ");
		int numJug = s.nextInt();
		
		Jugadores jug = (Jugadores) session.get(Jugadores.class, numJug);
		if(jug == null) {
			System.out.println("Número de jugador no válido"); 
		}
		else {
			Estadisticas est = new Estadisticas();
			Query cons = session.createQuery("from Estadisticas e where e.jugadores = :jug");
			cons.setInteger("jug", numJug);
			
			List<Estadisticas> lista = cons.list();
			System.out.println("DATOS DEL JUGADOR: " + numJug);
			System.out.println("Nombre: " + jug.getNombre());
			System.out.println("Equipo: " + jug.getEquipos().getNombre());
			System.out.println("Temporada  Ptos\t Asis\t Temp\t Reb\t");
			System.out.println("========================================");
			Iterator<Estadisticas> it = lista.iterator();
			while(it.hasNext()) {
				est = it.next();
				System.out.println(est.getId().getTemporada() + "\t" + est.getPuntosPorPartido() + "\t" 
						+ est.getAsistenciasPorPartido() + "\t" + est.getTaponesPorPartido() + "\t" + est.getRebotesPorPartido());
			}
			System.out.println("========================================");
			System.out.println("Num de registros: " + lista.size());
			System.out.println("========================================");
		}
		s.close();
		session.close();
		sesion.close();
	}
}
