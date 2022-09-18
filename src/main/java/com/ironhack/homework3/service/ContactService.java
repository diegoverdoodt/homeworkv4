package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    Contact getById(Integer id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void delete(Integer id);
}
