package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepo extends JpaRepository<Lead, Integer> {

    Lead getLeadById(Integer id);
}
