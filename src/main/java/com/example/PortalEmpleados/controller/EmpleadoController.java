package com.example.PortalEmpleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.PortalEmpleados.entity.Empleado;
import com.example.PortalEmpleados.repo.EmpleadoRepo;

@Controller
public class EmpleadoController {
	@Autowired
	EmpleadoRepo empleadoRepo;

	@GetMapping({ "/", "/lista", "/home" })
	public String listEmpleados(Model model) {
		model.addAttribute("empleados", empleadoRepo.findAll());
		return "index";
	}

	@GetMapping("/new/empleado")
	public String newEmpleado(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		return "newEmpleado";
	}

	@GetMapping("/delete/{id}")
	public String removeEmpleado(@PathVariable long id) {
		empleadoRepo.deleteById(id);
		return "redirect:/lista";
	}

	@PostMapping("/save")
	public String saveEmpleado(@ModelAttribute Empleado empleado) {
		empleadoRepo.save(empleado);
		return "redirect:/lista";

	}

}
