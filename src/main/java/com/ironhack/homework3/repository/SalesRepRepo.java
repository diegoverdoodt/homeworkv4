package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SalesRepRepo extends JpaRepository<SalesRep, Integer> {

    SalesRep getSalesRepById(int id);

    SalesRep getSalesRepByName(String name);


//    @Query("SELECT SalesRep.name, COUNT(SalesRep.leads) FROM SalesRep group by SalesRep.name")
//    List<String, Integer> countLeadsBySalesRep();



}
