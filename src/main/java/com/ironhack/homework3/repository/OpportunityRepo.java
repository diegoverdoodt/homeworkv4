package com.ironhack.homework3.repository;

import com.ironhack.homework3.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Integer> {

    Opportunity getOpportunitiesById(Integer id);

}
