package UD2_10;

public class Pelicula {
	private int id;
	private String titulo;
	private int anyo;
	private String descripcion;
	
	public Pelicula(int id, String titulo, int anyo, String descripcion) {
		this.id = id;
		this.titulo = titulo;
		this.anyo = anyo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", anyo=" + anyo + ", descripcion=" + descripcion + "]";
	}
	
	
	
}
