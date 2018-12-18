package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.CustomerDTO;
import com.paysoft.easycheck.dtos.MerchantDTO;
import com.paysoft.easycheck.dtos.TransactionDTO;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.services.CustomerService;
import com.paysoft.easycheck.services.MerchantService;
import com.paysoft.easycheck.services.TransactionService;
import com.paysoft.easycheck.utils.PaginatedResource;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
public class TransactionController {

    @Inject
    TransactionService service;

    @Inject
    MerchantService merchantService;

    @Inject
    CustomerService customerService;

    @GET
    public Response index(
        @DefaultValue("50") @QueryParam("limit") int limit,
        @DefaultValue("0") @QueryParam("offset") int offset,
        @QueryParam("customer_id") Long customerID,
        @QueryParam("merchant_id") Long merchantID
    ) {
        PaginatedResource<TransactionDTO> transactions;
        if (merchantID != null) {
            Optional<MerchantDTO> merchant = merchantService.find(merchantID);

            if (!merchant.isPresent()) {
                throw new EntityNotFoundException("Merchant with id " + merchantID + " not found.");
            }

            transactions = service.forMerchant(limit, offset,merchant.get());

            return Response.ok().entity(transactions).build();
        }

        if (customerID != null) {
            Optional<CustomerDTO> customer = customerService.findOne(customerID);

            if (!customer.isPresent()) {
                 throw new EntityNotFoundException("Customer with id " + customerID + " not found.");
            }

            transactions = service.forCustomer(limit, offset, customer.get());

            return Response.ok().entity(transactions).build();
        }

        transactions = service.getAll(limit, offset);

        return Response.ok().entity(transactions).build();
    }

    @POST
    public Response save(TransactionDTO transactionDTO) {
        Optional<CustomerDTO> customer = customerService.findOne(transactionDTO.getCustomerID());
        if (!customer.isPresent()) {
            throw new EntityNotFoundException("Customer with id " + transactionDTO.getCustomerID() + " not found.");
        }

        Optional<MerchantDTO> merchant = merchantService.find(transactionDTO.getMerchantID());
        if (!merchant.isPresent()) {
            throw new EntityNotFoundException("Merchant with id " + transactionDTO.getMerchantID() + " not found.");
        }

        TransactionDTO transaction = service.save(transactionDTO, merchant.get(), customer.get());

        return Response.status(Response.Status.CREATED).entity(transaction).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long ID) {
        Optional<TransactionDTO> transaction = service.findOne(ID);

        if (!transaction.isPresent()) {
            throw new EntityNotFoundException("Transaction not found");
        }

        return Response.ok().entity(transaction.get()).build();
    }
}
