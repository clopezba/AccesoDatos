package sesion1;

import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
	static MongoCollection<Document> coleccion;
	
	public static void main(String[] args) {
		//Crear cliente
		MongoClient mongo = new MongoClient("localhost", 27017);
		//Conectar con base de datos
		MongoDatabase bd = mongo.getDatabase("test");
		//Seleccionar colección
		 coleccion = bd.getCollection("ciudades");
		
		Ciudad cuenca = new Ciudad();
		cuenca.setCountry("ES");
		cuenca.setName("Cuenca");
		cuenca.setLatitude(0);
		cuenca.setLongitude(0);
		cuenca.setPopulation(53000);
		cuenca.setTimezone("Europe/Madrid");
		
		//System.out.println(insertaCiudad(cuenca));
		//listarCiudades();
		//listarCiudadesPais("ES");
		
		
		//FALTA AGRUPAR
		listarPaises(); 
		
		//Cerrar conexión
		mongo.close();

	}
	
	private static boolean insertaCiudad(Ciudad ciudad) {
		Document coordenadas = new Document("latitude", ciudad.getLatitude())
				.append("longitude", ciudad.getLongitude());
		
		Document document = new Document("name", ciudad.getName())
				.append("country", ciudad.getCountry())
				.append("timezone", ciudad.getTimezone())
				.append("location", coordenadas)
				.append("population", ciudad.getPopulation());
		
		try {
			coleccion.insertOne(document);
			System.out.println("Documento insertado");
			return true;
		}catch(Exception e) {
			System.out.println("No se ha podido insertar");
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static void listarCiudades() {
		FindIterable<Document> iter = coleccion.find();
		Iterator<Document> it = iter.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getString("name"));
		}
	}
	
	private static void listarCiudadesPais(String pais) {
		FindIterable<Document> buscar = coleccion.find(new Document("country",pais))
				.sort(new Document("name", 1));
		Iterator<Document> it = buscar.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getString("name"));
		}
	}
	
	
	//FALTA AGRUPAR!!
	private static void listarPaises() {
		FindIterable<Document> buscar = coleccion.find().sort(new Document("country", 1));
	
		Iterator<Document> it = buscar.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getString("country"));
		}
	}
}
