package com.jtbank.backend.mapper;

import com.jtbank.backend.dto.TransactionDTO;
import com.jtbank.backend.model.Transaction;

public class TransactionMapper {
    private TransactionMapper() {}

    public static TransactionDTO dtoMapper(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTimestamp(),
                transaction.getMode(),
                transaction.getAmount()
        );
    }
}
