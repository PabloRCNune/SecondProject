package com.example.PortalEmpleados.representation;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.PortalEmpleados.controller.EmpleadoController;
import com.example.PortalEmpleados.entity.Empleado;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Empleado,EntityModel<Empleado>> {
	
	@Override
	public EntityModel<Empleado> toModel(Empleado empleado) {
        return EntityModel.of(empleado,linkTo(methodOn(EmpleadoController.class).removeEmpleadoButtons(empleado.getId())).withRel("delete"));
    }
}
