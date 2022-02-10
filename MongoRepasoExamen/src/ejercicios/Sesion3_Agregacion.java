package ejercicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Sesion3_Agregacion {

	static MongoCollection<Document> col;
	
	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("test");
		col = db.getCollection("ciudades");
		
		//ejercicio26();
		ejercicio28();
		
		mongo.close();
	}
	
	private static void ejercicio26() {
		Document match = new Document("$match", new Document("country", "ES"));
		Document project = new Document("$project", new Document("_id", 0)
				.append("nombre", "$name")
				.append("poblacion", "$population"));
		Document sort = new Document("$sort", new Document("poblacion", -1));
		Document limit = new Document("$limit", 3);
		
		List<Document> lista = new ArrayList<Document>();
		lista.add(match);
		lista.add(project);
		lista.add(sort);
		lista.add(limit);
		
		Iterator<Document> cursor = col.aggregate(lista).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());			
		}
	}
	
	private static void ejercicio27() {
		Document group = new Document("$group", 
				new Document("_id", "$country")
				.append("totalPob", new Document("$sum", "$population"))
				.append("totalCiu", new Document("$sum", 1)));
		Document project = new Document("$project", 
				new Document("_id", 0)
				.append("poblacion", "$totalPob")
				.append("ciudades", "$totalCiu")
				.append("pais", "$_id"));
		Document sort = new Document("$sort", new Document("poblacion", -1));
		
		List<Document> lista = new ArrayList<Document>();
		lista.add(group);
		lista.add(project);
		lista.add(sort);
		
		Iterator<Document> iterador = col.aggregate(lista).iterator();
		while (iterador.hasNext()) {
			System.out.println(iterador.next().toJson());			
		}
	}
	
	private static void ejercicio28() {
		Document group = new Document("$group",
				new Document("_id", "$country")
				.append("totalPob", new Document("$sum", "$population"))
				.append("totalCiu", new Document("$sum", 1))
				.append("media", new Document("$avg", "$population")));
		Document project = new Document("$project", 
				new Document("_id", 0)
				.append("pais", "$_id")
				.append("ratio", "$media"));
		Document sort = new Document("$sort", new Document("ratio", -1));
		
		List<Document> lista = new ArrayList<Document>();
		lista.add(group);
		lista.add(project);
		lista.add(sort);
		
		Iterator<Document> it = col.aggregate(lista).iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toJson());			
		}
	}

}
