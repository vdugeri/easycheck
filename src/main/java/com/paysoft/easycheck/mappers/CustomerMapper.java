package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.CustomerDTO;
import com.paysoft.easycheck.models.Customer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer mapTo(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setID(customerDTO.getID());

        return customer;
    }


    public static CustomerDTO mapTo(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDTO(
            customer.getID(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getPassword()
        );
    }

    public static List<CustomerDTO> mapTo(List<Customer> customers) {
        if (customers.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return customers.stream()
            .map(user -> mapTo(user))
            .filter(user -> user != null)
            .collect(Collectors.toList());
    }
}
