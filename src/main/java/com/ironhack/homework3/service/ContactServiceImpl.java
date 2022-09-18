package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Contact;
import com.ironhack.homework3.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(Integer id) {
        return contactRepo.getContactById(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public void delete(Integer id) {
        contactRepo.delete(contactRepo.getContactById(id));
    }
}
