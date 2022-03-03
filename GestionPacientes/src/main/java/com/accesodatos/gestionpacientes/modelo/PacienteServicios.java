package com.accesodatos.gestionpacientes.modelo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicios {

	@Autowired
	PacienteRepositorio repositorio;
	
	public Paciente insertar(Paciente p) {
		return repositorio.save(p);
	}
	
	public void borrar(long id) {
		repositorio.deleteById(id);
	}
	public Paciente editar(Paciente p) {
		Paciente modif = repositorio.getById(p.getId());
		
		modif.setNombre(p.getNombre());
		modif.setApellidos(p.getApellidos());
		modif.setCiudad(p.getCiudad());
		modif.setDireccion(p.getDireccion());
		modif.setEdad(p.getEdad());
		modif.setEmail(p.getEmail());
		modif.setFecha_alta(p.getFecha_alta());
		modif.setPoblacion(p.getPoblacion());
		modif.setTelefono(p.getTelefono());
		modif.setTerapeuta(p.getTerapeuta());
		
		return repositorio.save(modif);
	}
	
	public List<Paciente> buscarTodos(){
		return repositorio.findAll();
	}
	
	public Paciente buscarId(long id) {
		return repositorio.getById(id);
	}
	
}
