package com.ironhack.homework3.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    enum Industry {
        PRODUCE,
        ECOMMERCE,
        MANUFACTURING,
        MEDICAL,
        OTHER;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name="industry")
    private Industry industry;

    @Column(name = "employee_count")
    private int employeeCount;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "account")
    private final List<Contact> contactList;

    @OneToMany(mappedBy = "account")
    private final List<Opportunity> opportunityList;

    public Account(List<Contact> contactList, List<Opportunity> opportunityList){
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    public Account(){

        this.contactList = new ArrayList<>();
        this.opportunityList = new ArrayList<>();
    }
    public Account(String industry, int employeeCount, String city, String country) {

        setIndustry(industry);
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = new ArrayList();
        this.opportunityList = new ArrayList();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        if (industry.equals("ecommerce")) {
            this.industry = Industry.ECOMMERCE;
        } else if (industry.equals("production")) {
            this.industry = Industry.PRODUCE;
        } else if (industry.equals("manufacturing")) {
            this.industry = Industry.MANUFACTURING;
        } else if (industry.equals("medical")) {
            this.industry = Industry.MEDICAL;
        } else {
            this.industry = Industry.OTHER;
        };
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List getContactList() {
        return contactList;
    }

    public void setContact(Contact contact) {
        this.contactList.add(contact);
    }


    public List getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunity(Opportunity opportunity){
        this.opportunityList.add(opportunity);
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
}
