package com.accesodatos.gestionpacientes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.accesodatos.gestionpacientes.modelo.Paciente;
import com.accesodatos.gestionpacientes.modelo.PacienteServicios;

@Controller
public class PacienteControlador {

	@Autowired 
	PacienteServicios pacienteServicios;
	
	@GetMapping({"/", "/index.html"})
	public String index(Model model) {
		model.addAttribute("pacientes", pacienteServicios.buscarTodos());
		return "index";
	}
	
	@GetMapping("/crear.html")
	public String crearPaciente(Model model) {
		model.addAttribute("pacienteForm", new Paciente());
		return "crear";
	}
	@PostMapping("/crear/enviar.html")
	public String crearPacienteEnviar(@ModelAttribute("pacienteForm") Paciente nuevoPaciente) {
		pacienteServicios.insertar(nuevoPaciente);
		return "redirect:/index.html";
	}
	
	//++++++++[[ TRAER DATOS PACIENTE ]]+++++++++++++++
	@GetMapping("/modificar.html")
	public String modificarPaciente(Model model) {
		model.addAttribute("pacienteForm", new Paciente());
		return "modificar";
	}
	@PostMapping("/modificar/enviar.html")
	public String modificarPacienteEnviar(@ModelAttribute("pacienteForm") Paciente nuevoPaciente) {
		pacienteServicios.insertar(nuevoPaciente);
		return "redirect:/index.html";
	}
}
