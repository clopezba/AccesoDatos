package UD2_10;

import java.util.ArrayList;
import java.util.List;

public class ListaPeliculas {
	private List<Pelicula> lista = new ArrayList<Pelicula>();

	public ListaPeliculas() {}

	public void add(Pelicula p) {
		lista.add(p);
	}
	public List<Pelicula> getListaPeliculas() {
		return lista;
	}
	
	
	
}
