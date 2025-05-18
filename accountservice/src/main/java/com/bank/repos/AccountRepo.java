package com.bank.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {

    public Optional<Account> findByAccountNumber(String AccountNo);
    public void deleteById(Long id);
}
