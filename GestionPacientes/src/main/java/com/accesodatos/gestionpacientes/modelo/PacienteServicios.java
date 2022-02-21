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
		return repositorio.save(p);
	}
	
	public List<Paciente> buscarTodos(){
		return repositorio.findAll();
	}
	
	
}
