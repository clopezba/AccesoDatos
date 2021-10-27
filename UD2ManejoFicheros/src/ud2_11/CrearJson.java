package ud2_11;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import UD2_10.Pelicula;
public class CrearJson {

	public static void main(String[] args) {
		
		try {
			List<Pelicula> peliculas = Arrays.asList(
					new Pelicula(1, "El Señor de los Anillos: la Comunidad del Anillo", 2001,
							"Ambientada en la Tierra Media..."),
					new Pelicula(2, "El Señor de los Anillos: las dos torres", 2002, "..."),
					new Pelicula(3, "EL Señor de los Anillos: el retorno del Rey", 2003, "...")
					);
			
			FileWriter fw = new FileWriter("Ficheros\\Peliculas.json");
			new Gson().toJson(peliculas, fw);
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				

	}

}
