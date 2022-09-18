package com.ironhack.homework3.service;


import com.ironhack.homework3.model.Opportunity;
import com.ironhack.homework3.repository.OpportunityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityServiceImpl implements OpportunityService {

    @Autowired
    private OpportunityRepo opportunityRepo;

    @Override
    public List<Opportunity> getAll() {
        return opportunityRepo.findAll();
    }

    @Override
    public Opportunity getById(Integer id) {
        return opportunityRepo.getOpportunitiesById(id);
    }

    @Override
    public Opportunity save(Opportunity opportunity) {
        return opportunityRepo.save(opportunity);
    }

    @Override
    public Opportunity update(Opportunity opportunity) {
        return opportunityRepo.save(opportunity);
    }

    @Override
    public void delete(Integer id) {
        opportunityRepo.delete(opportunityRepo.getOpportunitiesById(id));
    }
}
