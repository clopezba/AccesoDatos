package com.accesodatos.gestionpacientes.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Paciente {

	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String apellidos;
	private int edad;
	private long telefono;
	private String email;
	private String direccion;
	private String poblacion;
	private String ciudad;
	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date fecha_alta;
	private String terapeuta;
	
		
	public Paciente() {
	}

	public Paciente(String nombre, String apellidos, long telefono, String email) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
	}

	public Paciente(String nombre, String apellidos, int edad, long telefono, String email, String direccion,
			String poblacion, String ciudad, String terapeuta) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
		this.terapeuta = terapeuta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblación() {
		return poblacion;
	}

	public void setPoblación(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(String terapeuta) {
		this.terapeuta = terapeuta;
	}

	public long getId() {
		return id;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", población="
				+ poblacion + ", ciudad=" + ciudad + ", fecha_alta=" + fecha_alta + ", terapeuta=" + terapeuta + "]";
	}
	
	
}
