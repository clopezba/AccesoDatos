package com.accesodatos.gestionpacientes.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

	List<Paciente> findByNombreContainsIgnoreCaseOrApellidosContainsIgnoreCaseOrEmailContainsIgnoreCase(String nombre, String apellidos, String email);
}
