package com.accesodatos.gestionpacientes.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesodatos.gestionpacientes.modelo.Paciente;
import com.accesodatos.gestionpacientes.modelo.PacienteServicios;

@Controller
public class PacienteControlador {

	@Autowired
	PacienteServicios pacienteServicios;

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index";
	}
	
	@GetMapping("/lista")
	public String lista(Model model, @RequestParam(name="q", required=false) String query) {
		List<Paciente> resultado = (query == null) ? pacienteServicios.buscarTodos() : pacienteServicios.filtrar(query);
		model.addAttribute("pacientes", resultado);
		return "lista";
	}

	@GetMapping("/crear")
	public String crearPaciente(Model model) {
		model.addAttribute("pacienteForm", new Paciente());
		return "crear";
	}

	@PostMapping("/crear/enviar")
	public String crearPacienteEnviar(@ModelAttribute("pacienteForm") Paciente nuevoPaciente) {
		pacienteServicios.insertar(nuevoPaciente);
		return "redirect:/lista";
	}

	@GetMapping("/modificar")
	public String modificarPaciente(@RequestParam Long id, Model model) {

		Paciente paciente = pacienteServicios.buscarId(id);

		if (paciente != null) {
			model.addAttribute("pacienteForm", paciente);
			return "modificar";
		} else {
			return "redirect:/crear";
		}

	}

	@PostMapping("/modificar/enviar")
	public String modificarPacienteEnviar(@ModelAttribute("pacienteForm") Paciente modifPaciente) {
		pacienteServicios.editar(modifPaciente);
		return "redirect:/lista";
	}

	@GetMapping("/eliminar")
	public String eliminarPaciente(@RequestParam Long id) {
		pacienteServicios.borrar(id);
		return "redirect:/lista";
	}

}
