package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.services.HelloService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldController {

    @Inject
    HelloService helloService;

    @GET
    public Response sayHello() {
        return Response.ok().entity(helloService.sayHello()).build();
    }
}
