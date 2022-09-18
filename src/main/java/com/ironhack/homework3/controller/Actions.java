/*package com.ironhack.homework3.controller;

import com.ironhack.homework3.model.Opportunity;
import com.ironhack.homework3.model.SalesRep;
import com.ironhack.homework3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Actions {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private LeadService leadService;

    @Autowired
    private OpportunityService opportunittyService;

    @Autowired
    private SalesRepService salesRepService;

    public AccountService getAccountService(){
        return accountService;
    }
    public ContactService getContactService() {
        return contactService;
    }
    public LeadService getLeadService() {
        return leadService;
    }
    public OpportunityService getOpportunittyService(){
        return opportunittyService;
    }
    public SalesRepService getSalesRepService(){
        return salesRepService;
    }

    public SalesRep  addSalesRep(SalesRep salesRep){
        return salesRepService.save(salesRep);
    }

}*/
