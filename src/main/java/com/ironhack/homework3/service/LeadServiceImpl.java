package com.ironhack.homework3.service;

import com.ironhack.homework3.model.Lead;
import com.ironhack.homework3.repository.LeadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService{

    @Autowired
    private LeadRepo leadRepo;

    @Override
    public List<Lead> getAll() {
        return leadRepo.findAll();
    }

    @Override
    public Lead getById(Integer id){
        return leadRepo.getLeadById(id);
    }


    @Override
    public Lead save(Lead lead){
        return leadRepo.save(lead);
    }


    @Override
    public Lead update(Lead lead){
        return leadRepo.save(lead);
    }


    @Override
    public void delete(Integer id){
        leadRepo.delete(leadRepo.getLeadById(id));
    }
}
