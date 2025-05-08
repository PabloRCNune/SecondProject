package com.example.PortalEmpleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PortalEmpleados.entity.Empleado;
import com.example.PortalEmpleados.repo.EmpleadoRepo;
import com.example.PortalEmpleados.services.EmpServices;

@Controller
public class EmpleadoController {
	@Autowired
	EmpServices empservices;

	@GetMapping({ "/", "/lista", "/home" })
	public String listEmpleados(Model model) {
		model.addAttribute("empleados", empservices.getAllEmpleados());
		
		return "index";
	}

	@GetMapping("/new/empleado")
	public String newEmpleado(Model model, @RequestParam(name = "id",required = false, defaultValue = "-1") long empleadoId) {
		Empleado empleado = empservices.getEmpleadoById(empleadoId);
		model.addAttribute("empleado", empleado);
		return "newEmpleado";
	}

	@GetMapping("/delete/{id}")
	public String removeEmpleado(@PathVariable long id) {
		empservices.deleteEmpleado(id);
		return "redirect:/lista";
	}

	@PostMapping("/save")
	public String saveEmpleado(@ModelAttribute Empleado empleado, @RequestParam long id) {
		empleado.setId(id);
		System.out.println("Empleado: " + empleado);
		empservices.saveOrUpdateEmpleado(empleado);
		return "redirect:/lista";

	}
	@GetMapping("/new/empleado/buttons")
	public String newEmpleadoButtons(Model model, @RequestParam(name = "id",required = false, defaultValue = "-1") long empleadoId) {
		Empleado empleado = empservices.getEmpleadoById(empleadoId);
		model.addAttribute("empleado", empleado);
		return "newEmpleadoButtons";
	}
	
	@GetMapping("/delete/buttons/{id}")
	public String removeEmpleadoButtons(@PathVariable long id) {
		empservices.deleteEmpleado(id);
		HttpHeader
		return "redirect:/lista";
	}
	
	@PostMapping("/save/buttons")
	public String saveEmpleadoButtons(@ModelAttribute Empleado empleado, @RequestParam long id) {
		empleado.setId(id);
		System.out.println("Empleado: " + empleado);
		empservices.saveOrUpdateEmpleado(empleado);
		return "redirect:/lista";
		
	}

}
