package ServiceImpl;

import DTO.AccountDTO;
import Services.AccountService;
import entity.Account;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import repos.AccountRepo;
import repos.UserRepo;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public long createAccount(AccountDTO accountDTO) {
// Step 1: Create User entity from DTO
        User user = new User();
        user.setFirstName(accountDTO.getFirstName());
        user.setLastName(accountDTO.getLastName());
        user.setFatherName(accountDTO.getFatherName());
        user.setJob(accountDTO.getJob());
        user.setEmail(accountDTO.getEmail());
        user.setPhone(accountDTO.getPhone());
        user.setAddress(accountDTO.getAddress());

        // Step 2: Save User FIRST
        User savedUser = userRepo.save(user);

        // Step 3: Create Account entity and set User
        Account account = new Account();
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setUser(savedUser);  // Link to user

        // Step 4: Save Account
        Account savedAccount = accountRepo.save(account);


        return 1;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList=null;
        accountList= accountRepo.findAll();
        return accountList;
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        Optional <Account> acc;
        acc = accountRepo.findById(id);
        return acc;
    }

    @Override
    public Account updateAccount(Long id, Account accountUpdate) {
        // Step 1: Fetch existing account by id
        Account existingAccount = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        // Step 2: Update fields
        existingAccount.setAccountType(accountUpdate.getAccountType());
        existingAccount.setBalance(accountUpdate.getBalance());

        // Step 3: Save updated entity
        Account updatedAccount = accountRepo.save(existingAccount);

        return updatedAccount;  // Return updated object
    }


    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }
}
