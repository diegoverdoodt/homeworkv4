package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Opportunity;

import java.util.List;

public interface OpportunityService {

    List<Opportunity> getAll();

    Opportunity getById(Integer id);

    Opportunity save(Opportunity opportunity);

    Opportunity update(Opportunity opportunity);

    void delete(Integer id);

}
