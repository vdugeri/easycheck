package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.dtos.UserDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static Customer mapTo(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setEmail(userDTO.getEmail());
        customer.setPassword(userDTO.getPassword());
        customer.setFirstName(userDTO.getFirstName());
        customer.setLastName(userDTO.getLastName());

        return customer;
    }


    public static UserDTO mapTo(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new UserDTO(
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getPassword()
        );
    }

    public static List<UserDTO> mapTo(List<Customer> customers) {
        if (customers.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return customers.stream()
            .map(user -> mapTo(user))
            .filter(user -> user != null)
            .collect(Collectors.toList());
    }
}
