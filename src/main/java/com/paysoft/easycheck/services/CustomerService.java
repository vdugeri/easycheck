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
     * @param customer new customer
     *
     * @return the created {@link Customer}
     */
    public CustomerDTO createCustomer(CustomerDTO customer) {
        customer.setPassword(new PasswordHash().hash(customer.getPassword().toCharArray()));
        return CustomerMapper.mapTo(customerRepository.create(CustomerMapper.mapTo(customer)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID Long
     *
     * @return Optional of Customer
     */
    public Optional<CustomerDTO> findOne(Long ID) {
        return Optional.ofNullable(CustomerMapper.mapTo(customerRepository.find(ID)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user Customer
     * @return found Customer
     */
    public CustomerDTO update(CustomerDTO user) {
        return CustomerMapper.mapTo(customerRepository.edit(CustomerMapper.mapTo(user)));
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
