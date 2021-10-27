package UD2_10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CrearXML {

	public static void main(String[] args) {
		ListaPeliculas pelis = new ListaPeliculas();
		
		pelis.add(new Pelicula(1, "El Señor de los Anillos: la Comunidad del Anillo", 
				2001, "Ambientada en la Tierra Media..."));
		pelis.add(new Pelicula(2, "El Señor de los Anillos: las dos torres", 2002, 
				"La trama de la película..."));
		pelis.add(new Pelicula(3, "El Señor de los Anillos: el retorno del Rey", 2003,
				"Trata sobre la última parte del viaje..."));
		
		
		
		try {
			XStream xs = new XStream(new DomDriver("UTF-8"));
			xs.alias("pelicula", Pelicula.class);
			xs.alias("peliculas", ListaPeliculas.class);
			xs.addImplicitCollection(ListaPeliculas.class, "lista");
			xs.toXML(pelis, new FileOutputStream("Ficheros\\peliculas.xml"));
			System.out.println("Creado fichero XML...");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
