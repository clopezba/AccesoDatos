package ejercicios;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

public class Sesion1_CRUD {

	static MongoCollection<Document> coleccion;

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase baseDatos = mongo.getDatabase("test");
		coleccion = baseDatos.getCollection("ciudades");

//		14. Modif poblacion Huesca a 1.000.000
//		UpdateResult resultado = coleccion.updateOne(new Document("name", "Huesca"),
//				new Document("$set", new Document("population", 1000000)));
//		System.out.println(resultado);
		
//		15. Incementa poblacion Elx en 666 personas
//		UpdateResult resultado = coleccion.updateOne(new Document("name", "Elx"),
//				new Document("$inc", new Document("population", 666)));
//		System.out.println(resultado);
		
//		16. Reduce poblacion Andorra en 5 personas
//		UpdateResult res = coleccion.updateMany(new Document("country", "AD"), 
//				new Document("$inc", new Document("population", -5)));
//		System.out.println(res);
		

//		17. Gibraltar español pais y zona horaria
		
//		18. ...
		
		
		
		mongo.close();
	}
}
