package com.jtbank.backend.service;

import com.jtbank.backend.constant.TransactionMode;
import com.jtbank.backend.model.Transaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);

    CompletableFuture<List<Transaction>> getDebitedTransactions(long accountNumber, int pageSize, int pageNumber);

    List<Transaction> getCreditedTransactions(long accountNumber, int pageSize, int pageNumber);

    List<Transaction> getTransferredTransactions(long accountNumber, int pageSize, int pageNumber);

    CompletableFuture<List<Transaction>> getLast5Transaction(long accountNumber);

    long countRecord(TransactionMode mode, long accountNumber);

    CompletableFuture<Long> countRecord1(TransactionMode mode, long accountNumber);
}
