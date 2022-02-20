package com.accesodatos.gestionpacientes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.accesodatos.gestionpacientes.modelo.PacienteServicios;

@Controller
public class PacienteControlador {

	@Autowired 
	PacienteServicios pacienteServicios;
	
	@GetMapping({"/", "/index"})
	public String index(Model model) {
		model.addAttribute("pacientes", pacienteServicios.buscarTodos());
		return "index";
	}
}
