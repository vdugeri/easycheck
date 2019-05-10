package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.dtos.CustomerDTO;
import com.paysoft.easycheck.mappers.CustomerMapper;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.services.CustomerService;
import com.paysoft.easycheck.dtos.NotFound;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Stateless
@Path("/customers/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GET
    public Response index(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
        if (limit == null) {
            limit = 50;
        }

        if (offset == null) {
            offset = 0;
        }

        PaginatedResource<CustomerDTO> users = customerService.findAll(limit, offset);

        return Response.ok().entity(users).build();
    }

    @POST
    public Response store(CustomerDTO user) {
        CustomerDTO createdCustomer = customerService.createCustomer(user);

        return Response
                .status(Response.Status.CREATED)
                .entity(CustomerMapper.mapTo(createdCustomer))
                .build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long ID) {
        Optional<CustomerDTO> user = customerService.findOne(ID);

        if (user.isPresent()) {
            return Response.ok().entity(CustomerMapper.mapTo(user.get())).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new NotFound("Customer not found"))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long ID, CustomerDTO user) {
        Optional<CustomerDTO> userOptional = customerService.findOne(ID);

        if (userOptional.isPresent()) {
            CustomerDTO updatedCustomer = customerService.update(user);

            return Response.ok().entity(CustomerMapper.mapTo(updatedCustomer)).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new NotFound("Customer not found"))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") Long ID) {
        customerService.delete(ID);

        return Response.ok().build();
    }
}
