package com.jtbank.backend.service;

import com.jtbank.backend.model.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface IAccountService {
    /**
     * <b>Create a account without address</b>
     * */
    Account createAccount(Account account);

    /**
     * <b>Create a account with address</b>
     * */
    Account saveAccount(Account account);

    Account updateAccount(long accountNumber, Account account);

    Account uploadProfilePicture(long accountNumber, MultipartFile file) throws FileNotFoundException, Exception;

    void depositBalance(long accountNumber, double balance);

    void withdrawalBalance(long accountNumber, double balance);

    void transfer(long sender, long receiver, double balance);

    Account deleteAccount(long accountNumber);

    byte[] getProfilePicture(long accountNumber) throws Exception;

    Account getAccount(long accountNumber);

    Account getAccountBySlNo(int slNo);

    Account getAccountByEmail(String email);

    Account getAccountByEmailAndPassword(String email, String password);

    Account getAccountByAddressId(String addressId);

    List<Account> getAccounts();

}
