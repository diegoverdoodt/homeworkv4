package com.ironhack.homework3.model;

import javax.persistence.*;

@Entity
@Table(name = "opportunities")
public class Opportunity {
    public enum Status {
        OPEN,
        CLOSED_WON,
        CLOSED_LOST;
    }
    enum Product {
        HYBRID,
        FLATBED,
        BOX;
    }

    @Id
    @Column(name = "id")
    private int id;
    private static int initialID = 0;
    @Column(name = "product")
    @Enumerated(EnumType.STRING)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRep;

    @OneToOne(mappedBy = "opportunity")
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    public Opportunity(){}

    public Opportunity(String product, int quantity, Contact decisionMaker, SalesRep salesRep) {

        setProduct(product);
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = Status.OPEN;
        this.salesRep = salesRep;
    }

    public void setUniqueId() {
        this.id = initialID++;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(String product) {
        switch (product) {
            case "hybrid":
                this.product = Product.HYBRID;
                break;
            case "flatbed":
                this.product = Product.FLATBED;
                break;
            case "box":
                this.product = Product.BOX;
                break;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if (status.contains("lost")){
            this.status = Status.CLOSED_LOST;
        } else if (status.contains("won")){
            this.status = Status.CLOSED_WON;
        }
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    @ManyToOne(optional = false)
    private Account accounts;

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public static int getInitialID() {
        return initialID;
    }

    public static void setInitialID(int initialID) {
        Opportunity.initialID = initialID;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
