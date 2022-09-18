package com.ironhack.homework3.service;

import com.ironhack.homework3.model.SalesRep;

import java.util.List;

public interface SalesRepService {

    List<SalesRep> getAll();

    SalesRep getById(Integer id);

    SalesRep save(SalesRep salesRep);

    SalesRep update(SalesRep salesRep);

    void delete(Integer id);

    SalesRep getSalesRepByName(String name);

    void create(String name);

    List<SalesRep> getLeadsBySalesRep(SalesRep salesRep);
}
