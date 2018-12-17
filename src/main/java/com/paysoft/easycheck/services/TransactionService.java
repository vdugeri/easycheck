package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.CustomerDTO;
import com.paysoft.easycheck.dtos.MerchantDTO;
import com.paysoft.easycheck.dtos.TransactionDTO;
import com.paysoft.easycheck.mappers.CustomerMapper;
import com.paysoft.easycheck.mappers.MerchantMapper;
import com.paysoft.easycheck.mappers.TransactionMapper;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.models.Transaction;
import com.paysoft.easycheck.repositories.TransactionRepository;
import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.utils.PaginationMetadata;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
@LocalBean
public class TransactionService {

    @Inject
    TransactionRepository transactionRepository;

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param limit
     * @param offset
     *
     * @return {@link PaginatedResource} of {@link TransactionDTO}
     */
    public PaginatedResource<TransactionDTO> getAll(int limit, int offset) {
        List<Transaction> transactions = transactionRepository.findWithLimitAndOffset(limit, offset);
        if (transactions.isEmpty()) {
            return new PaginatedResource<>();
        }

        int total = transactionRepository.count();

        int pages = (int) Math.ceil(total / limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setPages(pages);
        metadata.setPerPage(limit);
        metadata.setTotal(total);

        PaginatedResource<TransactionDTO> paginatedTransactions = new PaginatedResource<>();
        paginatedTransactions.setMeta(metadata);
        paginatedTransactions.setData(TransactionMapper.mapTo(transactions));


        return paginatedTransactions;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param limit
     * @param offset
     * @param customer {@link Customer}
     *
     * @return {@link PaginatedResource} of {@link TransactionDTO} for a {@link CustomerDTO}
     */
    public PaginatedResource<TransactionDTO> forCustomer(int limit, int offset, CustomerDTO customer) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerID", customer.getID());
        int total = transactionRepository.findWithNamedQuery(Transaction.FIND_BY_CUSTOMER, params, 0).size();

        List<Transaction> transactions = transactionRepository
            .findWithLimitOffsetNamedQuery(Transaction.FIND_BY_CUSTOMER, params, limit, offset);

        int pages = (int) Math.ceil(total/limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setTotal(total);
        metadata.setPages(pages);
        metadata.setPerPage(limit);

        PaginatedResource<TransactionDTO> paginatedResource = new PaginatedResource<>();
        paginatedResource.setMeta(metadata);
        paginatedResource.setData(TransactionMapper.mapTo(transactions));

        return paginatedResource;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param limit
     * @param offset
     * @param merchant {@link Merchant}
     *
     * @return @return {@link PaginatedResource} of {@link TransactionDTO} for a {@link MerchantDTO}
     */
    public PaginatedResource<TransactionDTO> forMerchant(int limit, int offset, MerchantDTO merchant) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerID", merchant.getID());
        int total = transactionRepository.findWithNamedQuery(Transaction.FIND_BY_MERCHANT, params, 0).size();

        List<Transaction> transactions = transactionRepository
            .findWithLimitOffsetNamedQuery(Transaction.FIND_BY_MERCHANT, params, limit, offset);

        int pages = (int) Math.ceil(total/limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setTotal(total);
        metadata.setPages(pages);
        metadata.setPerPage(limit);

        PaginatedResource<TransactionDTO> paginatedResource = new PaginatedResource<>();
        paginatedResource.setMeta(metadata);
        paginatedResource.setData(TransactionMapper.mapTo(transactions));

        return paginatedResource;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param transactionDTO {@link TransactionDTO}
     * @param merchantDTO {@link MerchantDTO}
     * @param customerDTO {@link CustomerDTO}
     *
     * @return {@link TransactionDTO}
     */
    public TransactionDTO save(TransactionDTO transactionDTO, MerchantDTO merchantDTO, CustomerDTO customerDTO) {
        Merchant merchant = MerchantMapper.mapTo(merchantDTO, new ArrayList<>());
        Customer customer = CustomerMapper.mapTo(customerDTO);

        return TransactionMapper.mapTo(transactionRepository.create(TransactionMapper.mapTo(transactionDTO, merchant, customer)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID
     *
     * @return {@link Transaction}
     */
    public Optional<TransactionDTO> findOne(Long ID) {
        return Optional.ofNullable(TransactionMapper.mapTo(transactionRepository.find(ID)));
    }
}
