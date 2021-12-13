package prueba;

import primero.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//

public class AccesoBdatos {
	private SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	private Session session = sesion.openSession();

	public void conectar() {
		sesion = SessionFactoryUtil.getSessionFactory();
		session = sesion.openSession();
	}

	public void desconectar() {
		session.close();
		sesion.close();
		System.exit(0);
	}

	public boolean pregunta1(String nombre, String telefono) {
		boolean resultado = false;
		Transaction tx = null;
		tx = session.beginTransaction();

		Tienda tienda = new Tienda();

		try {
			if (nombre == null || nombre.equals("")) {
				tx.rollback();
			} else {
				tienda.setNombre(nombre);
				tienda.setTlf(telefono);
				session.save(tienda);
				tx.commit();
				resultado = true;
			}
		} catch (Exception ex) {
			System.out.println("Error en la inserción");
			ex.printStackTrace();
		}
		return resultado;
	}

	public boolean pregunta2(int codigo, String telefono) {
		boolean resultado = false;

		Transaction tx = null;
		tx = session.beginTransaction();

		Tienda tienda = (Tienda) session.get(Tienda.class, codigo);

		try {
			if (tienda == null) {
				tx.rollback();
			} else {
				tienda.setTlf(telefono);
				session.update(tienda);
				tx.commit();
				resultado = true;
			}
		} catch (Exception ex) {
			System.out.println("Error en la actualización");
			ex.printStackTrace();
		}
		return resultado;
	}

	public long pregunta3() {
		Long contador = (Long) session.createQuery("select count(nombre) from Familia").uniqueResult();
		return contador;
	}

	public void pregunta4(String codigoP, int codigoT) {
		Integer stock = (Integer) session
				.createQuery("select unidades from Stock where producto.cod = :prod and tienda.cod = :tien")
				.setString("prod", codigoP).setInteger("tien", codigoT).uniqueResult();

		if (stock == null)
			stock = 0;
		
		System.out.println("Total unidades del producto " + codigoP + " en la tienda " + codigoT + " --> " + stock);
	}
	
	public void pregunta5() {
		Long cuenta = (Long) session.createQuery("select count(distinct producto.cod) from Stock").uniqueResult();
		System.out.println("Productos distintos: " + cuenta + "\n");
		
		Query q = session.createQuery("select producto.nombreCorto, count(tienda.cod), "
				+ "sum(unidades) from Stock group by producto.cod order by producto.nombreCorto");
		Iterator<Object[]> it = q.iterate();
		
		while(it.hasNext()) {
			Object[] fila = (Object[]) it.next();
			String nombreC = (String) fila[0];
			Long tiendas = (Long) fila[1];
			Long unidades = (Long) fila[2];
			
			System.out.println("\tProducto: " + nombreC +" , disponible en "+ tiendas +" tienda(s), total Unidades "+ unidades);
		}
	}
	
	public void pregunta6() {
		 
		System.out.println("Producto (nombre corto) -  \tPrecio -   Familia");
		System.out.println("========================================================");
		Query q = session.createQuery("select p.nombreCorto, p.pvp, p.familia.nombre from Producto p left join p.stocks s "
				+ "where s.producto.cod = null");
		Iterator<Object[]> it = q.iterate();

		while(it.hasNext()) {
			Object[] fila = (Object[]) it.next();
			System.out.println(fila[0] + " - " + fila[1] + " - " + fila[2]);
		}
		
	}

} // de la clase AccesoBdatos
