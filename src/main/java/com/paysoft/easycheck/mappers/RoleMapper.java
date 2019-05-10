package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.RoleDTO;
import com.paysoft.easycheck.models.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDTO mapTo(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setID(role.getID());
        roleDTO.setName(role.getName());

        return roleDTO;
    }

    public static Role mapTo(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setID(roleDTO.getID());

        return role;
    }


    public static List<RoleDTO> mapTo(List<Role> roles) {
        if (roles.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return roles.stream()
            .map(role -> mapTo(role))
            .filter(roleDTO -> roleDTO != null)
            .collect(Collectors.toList());
    }
}
