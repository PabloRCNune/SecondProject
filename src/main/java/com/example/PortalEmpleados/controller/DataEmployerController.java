package com.example.PortalEmpleados.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PortalEmpleados.entity.Empleado;
import com.example.PortalEmpleados.repo.EmpleadoRepo;
import com.example.PortalEmpleados.services.EmpServices;

@RestController
@RequestMapping("/api")
public class DataEmployerController {
	@Autowired
	private EmpServices empServices;
	
	@GetMapping("tabla2")
	public ResponseEntity<Page<Empleado>> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		Page<Empleado> personas = empServices.getEmployeesByPage(page, size);
        return ResponseEntity.ok(personas);
	}
	
	@GetMapping("/empleados")
	public ResponseEntity<PagedModel<EntityModel<Empleado>>> getEmployees2(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(empServices.getEmployeesByPage2(page, size));
	}
	
	@GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        return ResponseEntity.ok(empServices.getEmpleadoById(id));
    }
	@GetMapping("tabla3")
	public ResponseEntity<List<Empleado>> getEmployees3(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset){
        return ResponseEntity.ok(empServices.getEmpleadosLimitOffset(limit, offset));
	}
	@GetMapping("tabla4")
	public Map<String, Object> getEmployees4(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam int draw){
		return empServices.getEmpleadosLimitOffset2(limit, offset, draw);
	}
}
