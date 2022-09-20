package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.Lead;
import com.ironhack.homework3.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepo extends JpaRepository<Lead, Integer> {

    Lead getLeadById(Integer id);

    List<SalesRep>getLeadsBySalesRep(SalesRep salesRep);
}
