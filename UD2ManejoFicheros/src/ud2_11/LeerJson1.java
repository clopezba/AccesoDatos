package ud2_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import UD2_10.Pelicula;

public class LeerJson1 {

	public static void main(String[] args) {
		
		try {
			Gson gson = new Gson();
			String fichero = new String(Files.readAllBytes(Paths.get("Ficheros\\Peliculas.json")));
			List<Pelicula> peliculas = Arrays.asList(gson.fromJson(fichero, Pelicula[].class));
			for (Pelicula p : peliculas) {
				System.out.println(p);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
