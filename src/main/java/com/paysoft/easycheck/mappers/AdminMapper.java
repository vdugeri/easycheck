package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.AdminDTO;
import com.paysoft.easycheck.models.Admin;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.models.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdminMapper {

    public static Admin mapTo(AdminDTO adminDTO, Merchant merchant, List<Role> roles) {
        if (adminDTO == null) {
            return  null;
        }

        Admin admin = new Admin();
        admin.setEmailAddress(adminDTO.getEmailAddress());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setID(adminDTO.getID());
        admin.setMerchant(merchant);
        admin.setPassword(adminDTO.getPassword());
        admin.setRoles(roles);

        return admin;
    }


    public static AdminDTO mapTo(Admin admin) {
        if (admin == null) {
            return null;
        }

        List<Long> roleIDs = admin.getRoles().stream().map(role -> role.getID()).collect(Collectors.toList());

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setEmailAddress(admin.getEmailAddress());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setID(admin.getID());
        adminDTO.setMerchantID(admin.getMerchant().getID());
        adminDTO.setRoleIDs(roleIDs);
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }


    public static List<AdminDTO> mapTo(List<Admin> admins) {
        if (admins.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return admins
            .stream()
            .map(admin -> mapTo(admin))
            .filter(adminDTO ->  adminDTO != null)
            .collect(Collectors.toList());
    }
}
