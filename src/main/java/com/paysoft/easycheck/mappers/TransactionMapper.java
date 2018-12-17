package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.TransactionDTO;
import com.paysoft.easycheck.models.Customer;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.models.Transaction;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static Transaction mapTo(TransactionDTO dto, Merchant merchant, Customer customer) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setCreatedAt(LocalDateTime.now().toString());
        transaction.setUpdatedAt(LocalDateTime.now().toString());
        transaction.setMerchant(merchant);
        transaction.setCustomer(customer);
        transaction.setID(dto.getID());

        return transaction;
    }

    public static TransactionDTO mapTo(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(transaction.getAmount());
        dto.setCreatedAt(transaction.getCreatedAt()); //TODO: Fix date processing
        dto.setUpdatedAt(transaction.getUpdatedAt()); //TODO: Fix date processing
        dto.setCustomerID(transaction.getCustomer().getID());
        dto.setMerchantID(transaction.getMerchant().getID());
        dto.setID(transaction.getID());

        return dto;
    }


    public static List<TransactionDTO> mapTo(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return transactions.stream()
            .map(transaction -> mapTo(transaction))
            .filter(transactionDTO -> transactionDTO != null)
            .collect(Collectors.toList());
    }
}
