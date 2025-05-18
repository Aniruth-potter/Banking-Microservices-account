package com.bank.Services;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.bank.DTO.AccountDTO;
import com.bank.entity.Account;

public interface AccountService {

  //  public String createAccount(AccountDTO accountDTO);
    public List<Account> getAllAccounts();
    public Optional<AccountDTO> getAccountById(Long id);
 //   public Account updateAccount(Long id,Account account);
  //  public void deleteAccount(Long id);
    public Map<String, BigDecimal> deposit(String accountNo, Map<String, BigDecimal> request);
    public Map<String, BigDecimal> withdraw(String accountNumber, Map<String, BigDecimal> request);
    public Map<String, BigDecimal> transfer(String accountNumber,String transferAccount, Map<String, BigDecimal> request);
	public Account saveFromDTO(AccountDTO accountDto);

}
