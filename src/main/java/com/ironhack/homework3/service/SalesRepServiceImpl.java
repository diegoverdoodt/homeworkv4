package com.ironhack.homework3.service;

import com.ironhack.homework3.model.SalesRep;
import com.ironhack.homework3.repository.SalesRepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesRepServiceImpl implements SalesRepService {

    @Autowired
    private SalesRepRepo salesRepRepo;

    @Override
    public List<SalesRep> getAll() {
        return salesRepRepo.findAll();
    }

    @Override
    public SalesRep getById(Integer id) {
        return salesRepRepo.getSalesRepById(id);
    }

    @Override
    public SalesRep save(SalesRep salesRep) {
        return salesRepRepo.save(salesRep);
    }

    @Override
    public SalesRep update(SalesRep salesRep) {
        return salesRepRepo.save(salesRep);
    }

    @Override
    public void delete(Integer id) {
        salesRepRepo.delete(salesRepRepo.getSalesRepById(id));
    }

    @Override
    public SalesRep getSalesRepByName(String name) {
        SalesRep salesRep1 = new SalesRep();
        for (SalesRep salesRep : salesRepRepo.findAll()){
            if (!salesRep.getName().equals(name)){
                salesRep1.setName(name);
                salesRepRepo.save(salesRep1);
            }else{
                salesRepRepo.save(salesRepRepo.getSalesRepByName(name));
                salesRep1.setName(name);
            }
        }
        return salesRep1;
    }

    @Override
    public void create(String name) {
        salesRepRepo.save(new SalesRep(name));
    }

    @Override
    public List<SalesRep> getLeadsBySalesRep(SalesRep salesRep) {
        return null;
    }


}
