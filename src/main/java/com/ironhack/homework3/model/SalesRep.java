package com.ironhack.homework3.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "sales_rep")
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    private static int initialID = 0;

    @OneToMany(mappedBy = "salesRep")
    private Map<Integer, Lead> leads;
    @OneToMany(mappedBy = "salesRep")
    private Map<Integer, Opportunity> opportunities;

    public SalesRep(){}

    public SalesRep(String name) {
        setUniqueId();
        this.name = name;
        this.leads = new HashMap<>();
        this.opportunities = new HashMap<>();
    }

    public void setUniqueId() {
        this.id = initialID++;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getInitialID() {
        return initialID;
    }

    public static void setInitialID(int initialID) {
        SalesRep.initialID = initialID;
    }

    public Map<Integer, Lead> getLeads() {
        return leads;
    }

    public void setLeads(Map<Integer, Lead> leads) {
        this.leads = leads;
    }

    public Map<Integer, Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Map<Integer, Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
