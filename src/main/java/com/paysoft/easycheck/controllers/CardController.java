package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.CardDTO;
import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.services.CardService;
import com.paysoft.easycheck.services.UserService;
import com.paysoft.easycheck.utils.PaginatedResource;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
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
    UserService userService;

    @GET
    public Response index(@DefaultValue("50")@QueryParam("limit") int limit, @DefaultValue("0")@QueryParam("offset") int offset) {
        PaginatedResource<CardDTO> cards = cardService.getAll(limit, offset);

        return Response.ok().entity(cards).build();
    }

    @POST
    public Response store(CardDTO cardDTO) {
        Optional<User> user = userService.findOne(cardDTO.getUserId());

        if (!user.isPresent()) {
            throw new EntityNotFoundException("User with id " + cardDTO.getUserId() + " is not found");
        }

        CardDTO card = cardService.save(cardDTO, user.get());

        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @GET
    @Path("{user_id}")
    public Response getUserCards(@PathParam("user_id") Long userID) {
        Optional<User> user = userService.findOne(userID);

        if (!user.isPresent()) {
            throw new EntityNotFoundException("User with id " + userID + " is not found");
        }

        List<CardDTO> cards = cardService.getUserCards(user.get().getID());

        return Response.ok().entity(cards).build();
    }
}
