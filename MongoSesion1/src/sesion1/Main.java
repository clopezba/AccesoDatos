package sesion1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

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
		//listarPaises();
		agregacion();
		
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
		
		for (Document document : buscar) {
			System.out.println(document.getString("name"));
		}
		
//		Iterator<Document> it = buscar.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next().getString("name"));
//		}
	}
	
	private static void listarPaises() {
		TreeSet<String> lista = new TreeSet<String>(); //Elimina repetidos y sale ordenada
		FindIterable<Document> buscar = coleccion.find()
				.projection(Projections.exclude("_id", "name", "timezone", "location", "tags", "poblacion"))
				.sort(new Document("country", 1)); //No sería necesario ordenarlo porque ya lo hace el TreeSet
	
		
		Iterator<Document> it = buscar.iterator();
		while(it.hasNext()) {
			lista.add(it.next().getString("country"));
		}
		for (String pais : lista) {
			System.out.println(pais);
		}
	}
	
	private static void agregacion() {
		Document match = new Document("$match", new Document("country", "ES"));
		Document project = new Document("$project", 
				new Document("_id", 0)
					.append("nombre", "$name")
					.append("poblacion", "$population"));
		Document sort = new Document("$sort", new Document("poblacion", -1));
		Document limit = new Document("$limit", 3);
		
		List<Document> listaAgregacion = new ArrayList<Document>();
		listaAgregacion.add(match);
		listaAgregacion.add(project);
		listaAgregacion.add(sort);
		listaAgregacion.add(limit);
		
		MongoCursor<Document> cursor = coleccion.aggregate(listaAgregacion).iterator();
		
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
