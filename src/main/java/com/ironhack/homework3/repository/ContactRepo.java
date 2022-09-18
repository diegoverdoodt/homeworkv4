package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {

    Contact getContactById(Integer id);
}
