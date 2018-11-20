package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.UserDTO;
import com.paysoft.easycheck.models.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapTo(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return user;
    }


    public static UserDTO mapTo(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTO(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword()
        );
    }

    public static List<UserDTO> mapTo(List<User> users) {
        if (users.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return users.stream()
            .map(user -> mapTo(user))
            .filter(user -> user != null)
            .collect(Collectors.toList());
    }
}
