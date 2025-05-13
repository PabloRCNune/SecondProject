package com.example.PortalEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.example.PortalEmpleados.entity.Empleado;
import com.example.PortalEmpleados.repo.EmpleadoRepo;
import com.example.PortalEmpleados.representation.EmployeeModelAssembler;

@Service
public class EmpServices {
	
	@Autowired
	private EmpleadoRepo empleadoRepo;
	@Autowired
	PagedResourcesAssembler<Empleado> assembler;
	@Autowired
	private EmployeeModelAssembler employeeModelAssembler;
	
	public List<Empleado> getAllEmpleados() {
		return empleadoRepo.findAll();
	}
	
	public Empleado getEmpleadoById(long id) {
		return empleadoRepo.findById(id).orElse(new Empleado());
	}
	
	public void saveOrUpdateEmpleado(Empleado empleado) {
		empleadoRepo.save(empleado);
	}
	
	public void deleteEmpleado(long id) {
		empleadoRepo.deleteById(id);
	}
	
	public Page<Empleado> getEmployeesByPage(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return empleadoRepo.findAll(pageable);
	}
	
	public PagedModel<EntityModel<Empleado>> getEmployeesByPage2(int page, int size){
		Pageable pageable=PageRequest.of(page, size);
		Page<Empleado> employees  = empleadoRepo.findAll(pageable);
		return assembler.toModel(employees, employeeModelAssembler);
	}
}
