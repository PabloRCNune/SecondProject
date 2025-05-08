package com.example.PortalEmpleados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PortalEmpleados.entity.Empleado;
import com.example.PortalEmpleados.repo.EmpleadoRepo;

@Service
public class EmpServices {
	
	@Autowired
	private EmpleadoRepo empleadoRepo;
	
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
}
