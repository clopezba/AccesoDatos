package com.accesodatos.gestionpacientes.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Paciente {

	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellidos;
	private Integer edad;
	private Long telefono;
	private String email;
	private String direccion;
	private String poblacion;
	private String ciudad;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_alta;
	private String terapeuta;
	
		
	public Paciente() {
	}

	public Paciente(Long id, String nombre, String apellidos, Long telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
	}

	public Paciente(Long id, String nombre, String apellidos, Integer edad, Long telefono, String email, String direccion,
			String poblacion, String ciudad, Date fecha_alta, String terapeuta) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.ciudad = ciudad;
		this.fecha_alta = fecha_alta;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
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

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(String terapeuta) {
		this.terapeuta = terapeuta;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
				+ ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", población="
				+ poblacion + ", ciudad=" + ciudad + ", fecha_alta=" + fecha_alta + ", terapeuta=" + terapeuta + "]";
	}
	
	
}
