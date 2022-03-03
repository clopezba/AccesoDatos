package com.accesodatos.gestionpacientes.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
	
}
