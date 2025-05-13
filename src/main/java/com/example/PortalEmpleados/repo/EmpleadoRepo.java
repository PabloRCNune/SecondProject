package com.example.PortalEmpleados.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PortalEmpleados.entity.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Long>{

	
	public Empleado findEmpleadosLimitB
	
}
