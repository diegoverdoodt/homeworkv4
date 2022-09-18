package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Account;
import com.ironhack.homework3.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account getById(Integer id) {
        return accountRepo.getAccountById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepo.delete(accountRepo.getAccountById(id));
    }
}
