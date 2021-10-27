package Ejercicios;

import java.io.Serializable;

public class Profesor implements Serializable{
	private String nombre;
	private double antiguedad;
	public Profesor(String nombre, double antiguedad) {
		this.nombre = nombre;
		this.antiguedad = antiguedad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setAntiguedad(double antiguedad) {
		this.antiguedad = antiguedad;
	}
	@Override
	public String toString() {
		return "Profesor [nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}
	
	
}
