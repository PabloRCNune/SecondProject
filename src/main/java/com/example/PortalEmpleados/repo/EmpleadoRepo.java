package com.example.PortalEmpleados.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.PortalEmpleados.entity.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Long>{

	@Query(value = "SELECT * FROM empleado order by id limit ?1 offset ?2", nativeQuery = true)
	List<Empleado> findEmpleadosLimitOffset(int limit, int offset);
	
}
