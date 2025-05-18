package com.bank.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.Account;
import com.bank.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

	public Optional<User> findByEmail(String Email);
}
