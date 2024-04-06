package com.jtbank.backend.controller;

import com.jtbank.backend.dto.*;
import com.jtbank.backend.mapper.AccountMapper;
import com.jtbank.backend.model.Credential;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.service.IJWTService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;
    private final IJWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO dto) {
        var account = AccountMapper.modelMapper(dto);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountAddressDTO dto) {
        var account = AccountMapper.modelMapper(dto);
        var result = accountService.saveAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TokenDTO accountByEmailAndPassword(@RequestBody Credential credential) {
        var auth = new UsernamePasswordAuthenticationToken(credential.getAccountEmail(),
                credential.getAccountPassword());
        authenticationManager.authenticate(auth);

        var account = accountService.getAccountByEmail(credential.getAccountEmail());
        var token = jwtService.generateToken(String.valueOf(account.getAccountNumber()));
        return new TokenDTO(token);
    }

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO uploadPicture(@RequestAttribute long accountNumber,
                                          @RequestParam("file")MultipartFile file) throws Exception {
        if(file.isEmpty())
            throw new RuntimeException("Image not found");

        var result = accountService.uploadProfilePicture(accountNumber, file);
        return AccountMapper.dtoMapper(result);
    }


    @PutMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO updateAccount(@PathVariable long accountNumber, @RequestBody UpdateDTO dto) {
        var account = AccountMapper.modelMapper(dto);
        var result = accountService.updateAccount(accountNumber, account);
        return AccountMapper.dtoMapper(result);
    }

    @PatchMapping("/deposit/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestAttribute long accountNumber, @PathVariable double balance) {
        accountService.depositBalance(accountNumber, balance);
    }

    @PatchMapping("/withdrawal/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@RequestAttribute long accountNumber, @PathVariable double balance) {
        accountService.withdrawalBalance(accountNumber, balance);
    }

    @PatchMapping("/transfer/{receiver}/balance/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestAttribute("accountNumber") long sender, @PathVariable long receiver, @PathVariable double balance) {
        accountService.transfer(sender, receiver, balance);
    }

    @DeleteMapping("/number/{accountNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO deleteByAccountNumber(@PathVariable long accountNumber) {
        var result = accountService.deleteAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping
    public List<AccountResponseDTO> accounts() {
        var results = accountService.getAccounts();
        return results.stream().map(AccountMapper::dtoMapper).toList();
    }

    @GetMapping(value = "/{accountNumber}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable long accountNumber) throws Exception {
        return accountService.getProfilePicture(accountNumber);
    }

    @GetMapping("/{slNo}")
    public AccountResponseDTO accountBySlNO(@PathVariable int slNo) {
        var result = accountService.getAccountBySlNo(slNo);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/number/{accountNumber}")
    public AccountResponseDTO accountByNumber(@PathVariable long accountNumber) {
        var result = accountService.getAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/current")
    public AccountResponseDTO currentAccountByNumber(@RequestAttribute long accountNumber) {
        var result = accountService.getAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/email/{email}")
    public AccountResponseDTO accountByEmail(@PathVariable String email) {
        var result = accountService.getAccountByEmail(email);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/address-details/{addressId}")
    public  AccountResponseDTO accountByAddressId(@PathVariable String addressId) {
        var result = accountService.getAccountByAddressId(addressId);
        return AccountMapper.dtoMapper(result);
    }
}