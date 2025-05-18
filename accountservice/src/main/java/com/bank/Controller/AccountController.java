package com.bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bank.DTO.AccountDTO;
import com.bank.Services.AccountService;
import com.bank.entity.Account;
import com.bank.repos.AccountRepo;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    
    @PostMapping("/save")
	public ResponseEntity<Account> saveTransaction(@RequestBody AccountDTO accountDto) {
    	Account account=	accountService.saveFromDTO(accountDto);
	    return ResponseEntity.ok(account);
	}

    // Create a new account
//    @PostMapping("/create")
//    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDTO accountDto) {
//        String createdAccountId = accountService.createAccount(accountDto);
//        return new ResponseEntity<>(createdAccountId, HttpStatus.CREATED);
//    }

    // Get all accounts
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        Optional<AccountDTO> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update account
//    @PutMapping("/{id}")
//    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Validated @RequestBody Account accountDetails) {
//        Account updatedAccount = accountService.updateAccount(id, accountDetails);
//        return ResponseEntity.ok(updatedAccount);
//    }
//
//    // Delete account
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
//        accountService.deleteAccount(id);
//        return ResponseEntity.noContent().build();
//    }
    
    @PutMapping("/{accountNo}/deposit")
    public ResponseEntity<Map<String, BigDecimal>> deposit(
            @PathVariable String accountNo,
            @RequestBody Map<String, BigDecimal> request) {

    	Map<String, BigDecimal> response=accountService.deposit(accountNo, request);

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<Map<String, BigDecimal>> withdraw(
            @PathVariable String accountNumber,
            @RequestBody Map<String, BigDecimal> request) {

    	Map<String, BigDecimal> response=accountService.withdraw(accountNumber, request);

        return ResponseEntity.ok(response);
    }
     
    
    @PutMapping("/{accountNumber}/transfer/{transferAccount}")
    public ResponseEntity<Map<String, BigDecimal>> transfer(
            @PathVariable String accountNumber, @PathVariable String transferAccount,
            @RequestBody Map<String, BigDecimal> request) {
    	 Map<String, BigDecimal> response=accountService.transfer(accountNumber, transferAccount, request);

        return ResponseEntity.ok(response);
    }

}

