package UD2_10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeerXML {

	public static void main(String[] args) {
		XStream xs = new XStream(new DomDriver("UTF-8"));
		xs.alias("pelicula", Pelicula.class);
		xs.alias("peliculas", ListaPeliculas.class);
		xs.addImplicitCollection(ListaPeliculas.class, "lista");
		try {
			
			ListaPeliculas pelis = (ListaPeliculas) xs.fromXML(new FileInputStream("Ficheros\\peliculas.xml"));
			
			for (Pelicula p : pelis.getListaPeliculas()) {
				System.out.println(p);
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}

}
