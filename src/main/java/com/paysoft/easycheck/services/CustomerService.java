package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.CustomerDTO;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.mappers.CustomerMapper;
import com.paysoft.easycheck.repositories.CustomerRepository;
import com.paysoft.easycheck.utils.PaginationMetadata;
import com.paysoft.easycheck.utils.PasswordHash;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Verem Dugeri <verem.dugeri@gmail.com>
 */
@Stateless
@LocalBean
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return List of users
     */
    public PaginatedResource<CustomerDTO> findAll(Integer limit, Integer offset) {
        int total = customerRepository.count();
        List<Customer> customers = customerRepository.findWithLimitAndOffset(limit, offset);

        int pages = (int) Math.ceil(total / limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setPages(pages);
        metadata.setPerPage(limit);
        metadata.setTotal(total);

        PaginatedResource<CustomerDTO> paginatedUsers = new PaginatedResource<>();
        paginatedUsers.setMeta(metadata);
        paginatedUsers.setData(CustomerMapper.mapTo(customers));


        return paginatedUsers;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user new user
     *
     * @return the created user
     */
    public Customer createUser(CustomerDTO user) {
        user.setPassword(new PasswordHash().hash(user.getPassword().toCharArray()));
        return customerRepository.create(CustomerMapper.mapTo(user));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID Long
     *
     * @return Optional of Customer
     */
    public Optional<Customer> findOne(Long ID) {
        return Optional.ofNullable(customerRepository.find(ID));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user Customer
     * @return found Customer
     */
    public Customer update(CustomerDTO user) {
        return customerRepository.edit(CustomerMapper.mapTo(user));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID Long
     */
    public void delete(Long ID) {
        customerRepository.delete(ID);
    }
}
