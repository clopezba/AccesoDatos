package ud2_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LeerJson2 {

	public static void main(String[] args) {
		try {
			String fichero = new String(Files.readAllBytes(Paths.get("Ficheros/Peliculas.json")));
			JsonArray gsonArr = JsonParser.parseString(fichero).getAsJsonArray();
			
			for (JsonElement obj : gsonArr) {
				JsonObject gsonObj = obj.getAsJsonObject();
				System.out.print(gsonObj.get("id").getAsInt() + " - " + 
						gsonObj.get("titulo").getAsString() + " - " + gsonObj.get("anyo").getAsInt()
						+ " - " + gsonObj.get("descripcion").getAsString());
				System.out.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
