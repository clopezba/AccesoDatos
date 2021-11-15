import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.SessionFactoryUtil;

public class Metodos {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		

	}

}
