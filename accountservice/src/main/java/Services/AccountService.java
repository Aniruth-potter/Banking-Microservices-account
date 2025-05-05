package Services;

import DTO.AccountDTO;
import entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public long createAccount(AccountDTO accountDTO);
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountById(Long id);
    public Account updateAccount(Long id,Account account);
    public void deleteAccount(Long id);

}
