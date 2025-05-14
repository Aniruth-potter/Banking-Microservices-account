package com.bank.Services;


import java.util.List;
import java.util.Optional;

import com.bank.DTO.AccountDTO;
import com.bank.entity.Account;

public interface AccountService {

    public String createAccount(AccountDTO accountDTO);
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountById(Long id);
    public Account updateAccount(Long id,Account account);
    public void deleteAccount(Long id);

}
