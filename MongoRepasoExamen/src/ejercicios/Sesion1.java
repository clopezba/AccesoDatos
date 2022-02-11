package ejercicios;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class Sesion1 {

	static MongoCollection<Document> coleccion;

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase baseDatos = mongo.getDatabase("test");
		coleccion = baseDatos.getCollection("ciudades");

//		1. Número de ciudades
		Long numCiudades = coleccion.countDocuments();
		System.out.println("Número de ciudades: " + numCiudades);
		
//		2. Datos de la ciudad de Elx
		Iterator<Document> elx = coleccion.find(new Document("name", "Elx")).iterator();
		System.out.println(elx.next());
		
//		3. Poblacion de la ciudad de Vergel
		Iterator<Document> vergel = coleccion.find(new Document("name", "Vergel")).iterator();
		System.out.println("Población de Vergel: " + vergel.next().getInteger("population"));
		
//		4. Cantidad de ciudades en España
		Long ciuEsp = coleccion.countDocuments(new Document("country", "ES"));
		System.out.println("Total ciudades España: " + ciuEsp);
		
//		5. Ciudades españolas de más de 1.000.000 hab
		Iterator<Document> ciuMillon = coleccion.find(new Document("country", "ES")
				.append("population", new Document("$gt", 1000000))).iterator();
		System.out.println("Ciudades con más de 1.000.000 de habitantes:");
		while (ciuMillon.hasNext()) {
			System.out.println(ciuMillon.next().toJson());
		}
		
//		6. Cantidad ciudades Andorra y España
		List<String> paises = new ArrayList<String>();
		paises.add("ES");
		paises.add("AD");
		Long ciuEspAnd = coleccion.countDocuments(new Document("country", new Document("$in", paises)));
		System.out.println("Total ciudades España y Andorra: " + ciuEspAnd);
				
		
//		7. Nombre y poblacion 10 ciudades más pobladas
		Iterator<Document> diezPob = coleccion.find()
				.projection(Projections.include("name", "population"))
				.projection(Projections.exclude("_id", "country", "location", "timezone", "tags"))
				.sort(new Document("population", -1))
				.limit(10).iterator();
		System.out.println("10 ciudades más pobladas: ");
		while (diezPob.hasNext()) {
			System.out.println(diezPob.next().toJson());
		}
		
//		8. Nombre distintas zonas horarias España
		Iterator<String> difZonas = coleccion.distinct("timezone", new Document("country", "ES"), String.class).iterator();
		while (difZonas.hasNext()) {
			System.out.println(difZonas.next());
		}
		
//		9. Ciudades esp con zona horario diferente de Europe/Madrid
		Iterator<Document> noTimeMadrid = coleccion.find(new Document("country", "ES")
				.append("timezone", new Document("$ne", "Europe/Madrid"))).iterator();
		while (noTimeMadrid.hasNext()) {
			System.out.println(noTimeMadrid.next().toJson());
		}
		
//		10. Ciudades esp que empiecen por Ben
		Iterator<Document> ciuBen = coleccion.find(new Document("country", "ES")
				.append("name", new Document("$regex", "^Ben.*"))).iterator();
		while (ciuBen.hasNext()) {
			System.out.println(ciuBen.next().getString("name"));
		}
		
//		11. Ciudades de TZ Atlantic/Canary o Africa/Ceuta con más de 500.000 hab
		List<String> timezone = new ArrayList<String>();
		timezone.add("Atlantic/Canary");
		timezone.add("Africa/Ceuta");
		Iterator<Document> TZ = coleccion.find(new Document("timezone", new Document("$in", timezone))
				.append("population", new Document("$gt", 500000))).iterator();
		while (TZ.hasNext()) {
			System.out.println(TZ.next().toJson());
		}
		
//		12. Nombre y población de 3 ciudades europeas mayor población
		Iterator<Document> ciuEur = coleccion.find(new Document("timezone", new Document("$regex", ".*Europe.*")))
				.projection(Projections.exclude("_id", "location", "timezone", "tags", "country"))
				.sort(new Document("population", -1))
				.limit(3).iterator();
		while (ciuEur.hasNext()) {
			System.out.println(ciuEur.next().toJson());
		}
		
//		13. Cantidad ciudades esp coordenadas entre -0.1 y 0.1
		List<Document> listaCoor = new ArrayList<Document>();
		listaCoor.add(new Document("location.longitude", new Document("$gt", -0.1)));
		listaCoor.add(new Document("location.longitude", new Document("$lt", 0.1)));
		Long cood = coleccion.countDocuments(new Document("country", "ES")
				.append("$and", listaCoor));
		System.out.println("Ciudades con coordenadas de longitud entre -0.1 y 0.1: " + cood);
				
		mongo.close();
	
	}
}
