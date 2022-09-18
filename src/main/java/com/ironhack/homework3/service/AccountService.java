package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getById(Integer id);

    Account save(Account account);

    Account update(Account account);

    void delete(Integer id);
}
