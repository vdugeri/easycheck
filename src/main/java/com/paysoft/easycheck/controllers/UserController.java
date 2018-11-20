package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.UserDTO;
import com.paysoft.easycheck.mappers.UserMapper;
import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.services.UserService;
import com.paysoft.easycheck.dtos.NotFound;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Stateless
@Path("/users/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    @GET
    public Response index() {
        List<User> users = userService.findAll();

        return Response.ok().entity(UserMapper.mapTo(users)).build();
    }

    @POST
    public Response store(UserDTO user) {
        User createdUser = userService.createUser(user);

        return Response
                .status(Response.Status.CREATED)
                .entity(UserMapper.mapTo(createdUser))
                .build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long ID) {
        Optional<User> user = userService.findOne(ID);

        if (user.isPresent()) {
            return Response.ok().entity(UserMapper.mapTo(user.get())).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new NotFound("User not found"))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long ID, UserDTO user) {
        Optional<User> userOptional = userService.findOne(ID);

        if (userOptional.isPresent()) {
            User updatedUser = userService.update(user);

            return Response.ok().entity(UserMapper.mapTo(updatedUser)).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new NotFound("User not found"))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") Long ID) {
        userService.delete(ID);

        return Response.ok().build();
    }
}
