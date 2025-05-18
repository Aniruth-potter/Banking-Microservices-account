package com.bank.ServiceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.DTO.AccountDTO;
import com.bank.Services.AccountService;
import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.repos.AccountRepo;
import com.bank.repos.UserRepo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

//    @Override
//    public String createAccount(AccountDTO accountDTO) {
//// Step 1: Create User entity from DTO
//        User user = new User();
//        user.setFirstName(accountDTO.getFirstName());
//        user.setLastName(accountDTO.getLastName());
//        user.setFatherName(accountDTO.getFatherName());
//        user.setJob(accountDTO.getJob());
//        user.setEmail(accountDTO.getEmail());
//        user.setPhone(accountDTO.getPhone());
//        user.setAddress(accountDTO.getAddress());
//
//        // Step 2: Save User FIRST
//        User savedUser = userRepo.save(user);
//
//        // Step 3: Create Account entity and set User
//        Account account = new Account();
//        account.setAccountType(accountDTO.getAccountType());
//        account.setBalance(accountDTO.getBalance());
//        account.setUser(savedUser);  // Link to user
//        account.setAccountNumber(generateAccountNo());
//
//        // Step 4: Save Account
//        Account savedAccount = accountRepo.save(account);
//        
//       
//
//
//        return savedAccount.getAccountNumber();
//    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList=null;
        accountList= accountRepo.findAll();
        return accountList;
    }

    @Override
    public Optional<AccountDTO> getAccountById(Long id) {
        return accountRepo.findById(id)
                .map(account -> {
                    AccountDTO dto = new AccountDTO();
                    dto.setId(account.getId());
                    dto.setAccountNumber(account.getAccountNumber());
                    dto.setAccountType(account.getAccountType());
                    dto.setBalance(account.getBalance());

                    if (account.getUser() != null) {
                        dto.setFirstName(account.getUser().getFirstName());
                        dto.setLastName(account.getUser().getLastName());
                        dto.setFatherName(account.getUser().getFatherName());
                        dto.setJob(account.getUser().getJob());
                        dto.setEmail(account.getUser().getEmail());
                        dto.setPhone(account.getUser().getPhone());
                        dto.setAddress(account.getUser().getAddress());
                    }

                    return dto;
                });
    }


//    @Override
//    public Account updateAccount(Long id, Account accountUpdate) {
//        // Step 1: Fetch existing account by id
//        Account existingAccount = accountRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
//
//        // Step 2: Update fields
//        existingAccount.setAccountType(accountUpdate.getAccountType());
//        existingAccount.setBalance(accountUpdate.getBalance());
//
//        // Step 3: Save updated entity
//        Account updatedAccount = accountRepo.save(existingAccount);
//
//        return updatedAccount;  // Return updated object
//    }
//
//
//    @Override
//    public void deleteAccount(Long id) {
//        accountRepo.deleteById(id);
//    }
    
    public String generateAccountNo() {
    	Random random = new Random();
        long randomPart = random.nextInt(900000); // Still int here, safe range
        long AccNo = randomPart + 10000000000000L; // Note the L for long
        return String.valueOf(AccNo);
	}

	@Override
	public Map<String, BigDecimal> deposit(String accountNo, Map<String, BigDecimal> request) {
		BigDecimal amount = request.get("amount");

        Account account = accountRepo.findByAccountNumber(accountNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepo.save(account);

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("newBalance", account.getBalance());
		return response;
	}

	@Override
	public Map<String, BigDecimal> withdraw(String accountNumber, Map<String, BigDecimal> request) {
		BigDecimal amount = request.get("amount");

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("updatedBalance", account.getBalance());
        
        return response;
	}

	@Override
	public Map<String, BigDecimal> transfer(String accountNumber, String transferAccount,
			Map<String, BigDecimal> request) {
		
		BigDecimal amount = request.get("amount");

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        Account transferaccount = accountRepo.findByAccountNumber(transferAccount)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);
        
        transferaccount.setBalance(transferaccount.getBalance().add(amount));
        accountRepo.save(transferaccount);
        
        

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("transferAmount", account.getBalance());
        
        return response;
	}

	@Override
	public Account saveFromDTO(AccountDTO accountDto) {

	    String action = accountDto.getActionType();

	    if ("CREATE".equalsIgnoreCase(action)) {

	        User user = new User();
	        user.setFirstName(accountDto.getFirstName());
	        user.setLastName(accountDto.getLastName());
	        user.setFatherName(accountDto.getFatherName());
	        user.setJob(accountDto.getJob());
	        user.setEmail(accountDto.getEmail());
	        user.setPhone(accountDto.getPhone());
	        user.setAddress(accountDto.getAddress());

	        User savedUser = userRepo.save(user);

	        Account account = new Account();
	        account.setAccountType(accountDto.getAccountType());
	        account.setBalance(accountDto.getBalance());
	        account.setUser(savedUser);
	        account.setAccountNumber(generateAccountNo());

	        accountRepo.save(account);
	        
	        return account;
	    }

	    else if ("UPDATE".equalsIgnoreCase(action)) {

	        Account existingAccount = accountRepo.findByAccountNumber(accountDto.getAccountNumber())
	                .orElseThrow(() -> new RuntimeException("Account not found with number: " + accountDto.getAccountNumber()));

	        existingAccount.setAccountType(accountDto.getAccountType());
	        existingAccount.setBalance(accountDto.getBalance());

	        accountRepo.save(existingAccount);
	        
	        User existingUser = userRepo.findByEmail(accountDto.getEmail())
	        		 .orElseThrow(() -> new RuntimeException("Account not found with number: " + accountDto.getEmail()));
	        
	        existingUser.setEmail(accountDto.getEmail());
	        existingUser.setFatherName(accountDto.getFatherName());
	        existingUser.setLastName(accountDto.getLastName());
	        existingUser.setFirstName(accountDto.getFirstName());
	        existingUser.setPhone(accountDto.getPhone());
	        existingUser.setJob(accountDto.getJob());
	        existingUser.setAddress(accountDto.getAddress());
	        
	        userRepo.save(existingUser);
	        
	        return existingAccount;
	    }

	    else if ("DELETE".equalsIgnoreCase(action)) {
	        accountRepo.deleteById(accountDto.getId());
	        return null;
	    }

	    else {
	        throw new IllegalArgumentException("Invalid action type: " + action);
	    }
	}

}
