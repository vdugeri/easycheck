package com.paysoft.easycheck.controllers;

import com.paysoft.easycheck.dtos.AdminDTO;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.models.Role;
import com.paysoft.easycheck.repositories.MerchantRepository;
import com.paysoft.easycheck.repositories.RoleRepository;
import com.paysoft.easycheck.services.AdminService;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@LocalBean
@Path("admins")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    @Inject
    AdminService adminService;

    @Inject
    MerchantRepository merchantRepository;

    @Inject
    RoleRepository roleRepository;

    @GET
    public Response index() {
        List<AdminDTO> admins = adminService.allAdmins();

        return Response.ok().entity(admins).build();
    }


    @POST
    public Response create(AdminDTO adminDTO) {
        Merchant merchant = merchantRepository.find(adminDTO.getMerchantID());

        List<Role> roles = adminDTO
            .getRoleIDs()
            .stream()
            .map(roleID -> roleRepository.find(roleID))
            .filter(role -> role != null)
            .collect(Collectors.toList());

        if (merchant != null) {
            AdminDTO admin = adminService.save(adminDTO, merchant, roles);

            return Response.status(Response.Status.CREATED).entity(admin).build();
        }

        throw new EntityNotFoundException("Invalid merchant id");
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long ID) {
        Optional<AdminDTO> admin = adminService.find(ID);

        if (admin.isPresent()) {
            return Response.ok().entity(admin.get()).build();
        }

        throw new EntityNotFoundException("Admin not found");
    }


    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long ID, AdminDTO adminDTO) {
        Merchant merchant = merchantRepository.find(adminDTO.getMerchantID());

        List<Role> roles = adminDTO
            .getRoleIDs()
            .stream()
            .map(roleID -> roleRepository.find(roleID))
            .filter(role -> role != null)
            .collect(Collectors.toList());

        if (merchant != null) {
            AdminDTO admin = adminService.update(adminDTO, merchant, roles);

            return Response.ok().entity(admin).build();
        }


        throw new EntityNotFoundException("Invalid merchant id");
    }


    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") Long ID) {
        adminService.delete(ID);

        return Response.noContent().build();
    }
}
