package com.paysoft.easycheck.services;

import com.paysoft.easycheck.helpers.UserLogin;
import com.paysoft.easycheck.helpers.UserToken;
import com.paysoft.easycheck.mappers.CustomerMapper;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.repositories.CustomerRepository;
import com.paysoft.easycheck.utils.PasswordHash;
import com.paysoft.easycheck.utils.TokenGenerator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class AuthService {

    @Inject
    private CustomerRepository userRepository;

    @Inject
    private TokenGenerator tokenGenerator;

    public boolean logIn(UserLogin userLogin) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", userLogin.getEmail());

        List<Customer> customerList = userRepository.findWithNamedQuery(Customer.FIND_BY_EMAIL, params, 1);

        if (customerList.isEmpty()) {
            return false;
        }

        Customer customer = customerList.get(0);

        return new PasswordHash().compare(userLogin.getPassword().toCharArray(), customer.getPassword());
    }

    public UserToken generateToken(UserLogin userLogin) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", userLogin.getEmail());

        List<Customer> customerList = userRepository.findWithNamedQuery(Customer.FIND_BY_EMAIL, params, 1);

        Customer customer = customerList.get(0);

        String token = tokenGenerator.generateToken(customer);

        return new UserToken().setToken(token).setUser(CustomerMapper.mapTo(customer));
    }
}
