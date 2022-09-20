package com.ironhack.homework3.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="company_name")
    private String companyName;
    
    @ManyToOne
    @JoinColumn(name = "account", nullable = true)
    private Account account;

    @OneToOne
    @JoinColumn(name = "opportunity_id", nullable = true)
    private Opportunity opportunity;

    public Contact(){}
    public Contact(Lead lead) {

        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();
        this.email = lead.getEmail();
        this.companyName = lead.getCompanyName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        account.setContact(this);
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }
}
