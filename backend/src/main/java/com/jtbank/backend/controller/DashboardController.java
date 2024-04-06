package com.jtbank.backend.controller;

import com.jtbank.backend.constant.TransactionMode;
import com.jtbank.backend.dto.DashboardDTO;
import com.jtbank.backend.mapper.TransactionMapper;
import com.jtbank.backend.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/dashboards")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    private final ITransactionService transactionService;

    @GetMapping
    public DashboardDTO dashboardDetails(@RequestAttribute long accountNumber) throws ExecutionException, InterruptedException {

        var numberOfDeposit = transactionService.countRecord1(TransactionMode.CREDIT, accountNumber);
        var numberOfWithdrawal = transactionService.countRecord1(TransactionMode.DEBIT, accountNumber);
        var numberOfTransfer = transactionService.countRecord1(TransactionMode.TRANSFER, accountNumber);

        var results = transactionService.getLast5Transaction(accountNumber);
        var transactions = results.thenApplyAsync(result -> result.stream()
                .map(TransactionMapper::dtoMapper).toList());

        CompletableFuture.allOf(numberOfDeposit, numberOfWithdrawal, numberOfTransfer, results, transactions).join();

        return new DashboardDTO(
                numberOfDeposit.get(),
                numberOfWithdrawal.get(),
                numberOfTransfer.get(),
                transactions.get()
        );
    }
}
