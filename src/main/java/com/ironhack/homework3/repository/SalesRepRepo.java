package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepo extends JpaRepository<SalesRep, Integer> {

    SalesRep getSalesRepById(int id);

    SalesRep getSalesRepByName(String name);
}
