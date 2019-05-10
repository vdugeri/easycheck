package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.RoleDTO;
import com.paysoft.easycheck.services.RoleService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
public class RoleController {

    @Inject
    RoleService roleService;

    @GET
    public Response index() {
        List<RoleDTO> roles = roleService.getAll();

        return Response.ok().entity(roles).build();
    }

    @POST
    public Response store(RoleDTO roleDTO) {
        RoleDTO role = roleService.create(roleDTO);

        return Response.status(Response.Status.CREATED).entity(role).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long ID) {
        Optional<RoleDTO> role = roleService.findOne(ID);

        if (!role.isPresent()) {
            throw new EntityNotFoundException("Role not found");
        }

        return Response.ok().entity(role.get()).build();
    }

    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") Long ID, RoleDTO roleDTO) {
        Optional<RoleDTO> role = roleService.edit(ID, roleDTO);

        if (!role.isPresent()) {
            throw new EntityNotFoundException("Role not found");
        }

        return Response.ok().entity(role.get()).build();
    }


    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") Long ID) {
        roleService.delete(ID);

        return Response.ok().build();
    }
}
