package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.CardDTO;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.services.CardService;
import com.paysoft.easycheck.services.CustomerService;
import com.paysoft.easycheck.utils.PaginatedResource;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("cards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
public class CardController {

    @Inject
    CardService cardService;

    @Inject
    CustomerService customerService;

    @GET
    public Response index(@DefaultValue("50")@QueryParam("limit") int limit, @DefaultValue("0")@QueryParam("offset") int offset) {
        PaginatedResource<CardDTO> cards = cardService.getAll(limit, offset);

        return Response.ok().entity(cards).build();
    }

    @POST
    public Response store(CardDTO cardDTO) {
        Optional<Customer> customer = customerService.findOne(cardDTO.getCustomerID());

        if (!customer.isPresent()) {
            throw new EntityNotFoundException("Customer with id " + cardDTO.getCustomerID() + " is not found");
        }

        CardDTO card = cardService.save(cardDTO, customer.get());

        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @GET
    @Path("{user_id}")
    public Response getUserCards(@PathParam("user_id") Long userID) {
        Optional<Customer> user = customerService.findOne(userID);

        if (!user.isPresent()) {
            throw new EntityNotFoundException("Customer with id " + userID + " is not found");
        }

        List<CardDTO> cards = cardService.getUserCards(user.get().getID());

        return Response.ok().entity(cards).build();
    }


    @DELETE
    @Path("{card_id}")
    public Response destroy(@PathParam("card_id") Long cardID) {
        cardService.remove(cardID);

        return Response.ok().build();
    }
}
