package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

    Account getAccountById(Integer id);


}
