package com.ironhack.homework3.controller;

import com.ironhack.homework3.model.*;
import com.ironhack.homework3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateApp {

//    @Autowired
//    private Actions actions;
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

//    public AccountService getAccountService(){
//        return accountService;
//    }
//    public ContactService getContactService() {
//        return contactService;
//    }
//    public LeadService getLeadService() {
//        return leadService;
//    }
//    public OpportunityService getOpportunittyService(){
//        return opportunittyService;
//    }
//    public SalesRepService getSalesRepService(){
//        return salesRepService;
//    }

    private boolean appIsOn;
    private int intId;

    public CreateApp(){}

    @PostConstruct
    public void initializer() {
        appIsOn = true;
        runApp(System.in);
    }

    public void runApp(InputStream inputStream) {

        llenarTablas();

        Scanner scan = new Scanner(System.in);

        System.out.println("\n\nWelcome to Rakion CRM \n");
        boolean appIsOn = true;

        while (appIsOn) {
            System.out.println(menu);
            String election = scan.nextLine().toLowerCase();
            election = extractIdFromElection(election);
            //he comentado los métodos. Los vamos descomentando a medida que los testeemos / estén listos
            switch (election) {
                case "new lead":
                    createLead();
                    break;
                case "show leads":
                    printShow("lead");
                    break;
                case "lookup lead":
                    printLookup("lead", intId);
                    break;
                case "show salesreps":
                    printShow("salesrep");
                    break;
                case "new salesrep":
                    createSalesRep();
                    break;
                case "lookup salesrep":
                    printLookup("salesrep", intId);
                    break;
                case "show opportunities":
                    printShow("opportunity");
                    break;
                case "lookup opportunity":
                    printLookup("opportunity", intId);
                    break;
                case "show accounts":
                    printShow("account");
                    break;
                case "lookup account":
                    printLookup("account", intId);
                    break;
                case "convert":
                    //convertId();
                    break;
                case "close-lost":
                case "close-won":
                    //changeOpportunityStatus(election);
                    break;
                case "report Lead by salesrep":
                    // funcion que devuelve el numero de Lead de cada SalesRep
                    break;
                case "report opportunity by salesrep":
                    // funcion que devuelve el numero de oportunidades de cada SalesRep
                    break;
                case "report closed-won by salesrep":
                    // funcion que devuelve el numero de closed-won por cada SalesRep
                    break;
                case "exit":
                    appIsOn = false;
                    System.out.println("Saving data...");
                    //Crear CSV con los datos
                    System.out.println("Closing app...");
                    break;
                default:
                    //volver al inicio pq no coincide
                    System.out.println("That was not a valid answer! Please write one of the options!");
            }
        }

    }
    public String extractIdFromElection(String userInput) {
        try {
            if (userInput.matches("^(lookup lead|lookup salesrep|lookup account|lookup opportunity|convert|close-lost|close-won)\s.+")) {
                String[] userInputSplit = userInput.split(" ");
                intId = Integer.parseInt(userInputSplit[(userInputSplit.length - 1)]);
                //deletes last word from userInput (from character 0 to last word obtained with lastindexOf)
                return userInput.substring(0, userInput.lastIndexOf(" "));
            } else if (userInput.matches("^(new lead|show leads|new salesrep|show opportunities|show accounts|show salesrep|exit)")) {
                return userInput;
            }
            return "incorrect input";
        } catch (Throwable exception) {
            System.err.println("" +
                    "\n#################################################\n" +
                    "Invalid id! Please remember id must be a number!\n" +
                    "#################################################\n");
            return "invalid id";
        }
    }


    /**
     * Strings con la información que va preguntando el programa
     */

    String menu = "What do you want to do?\n\n" +
            "New Lead               \n" + //
            "Show Leads             \n" + //
            "Lookup Lead id         \n" + //
            "New SalesRep          \n" + //
            "Show SalesRep          \n" + //
            "Lookup SalesRep id     \n" + //
            "Show Opportunities     \n" + //
            "Lookup Opportunity id  \n" + //
            "Show Accounts          \n" + //
            "Lookup Accounts id     \n" + //
            "Convert id             \n" +
            "Close-Lost id          \n" +
            "Close-Won id           \n" +
            "Exit                   \n\n" +
            "Please write your answer here:";

    String leadString[] = { "Please enter the values for the lead: name, phone number, e-mail and company name",
            "\nEnter the client's name:",
            "\nEnter the phone number of the lead: ",
            "\nEnter the lead contact e-mail: ",
            "\nEnter the name of the company:",
            "\nEnter the name of the SalesRep:"};

    String salesRepString[] = {"Please enter the values for the SalesRep: name",
            "\nEnter the SalesRep name:"};

    String opportunityString[] = {"Which vehicle is the client interested in? HYBRID, FLATBED o BOX: \n",
            "How many of them? \n"};

    String accountString[] = {"Enter the company industry (Produce, ecommerce, manufacturing, medical or other): \n",
            "Enter the number of employees: \n",
            "Enter the city: \n",
            "Enter the country: \n"};


    /**
     * Método que recibe los Strings y los va mostrando al usuario para usar el CRM
     * @param questions
     * @return Devuelve un array con las respuestas para usarlas en los métodos
     */
    public List<String> getInputData(String... questions) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputData = new ArrayList<>();
        for (String question : questions) {
            String answer = "";
            //Answers are checked for specific parameters depending on the question on method isCorrect
            //if answer is correct, it does "while false" and continues to next question.
            //if answer is incorrect, it does "while true" and repeats printing the question.
            do {
                System.out.println(question);
                answer = scanner.nextLine();
            } while (!isCorrect(question, answer));
            inputData.add(answer);
        }
        return inputData;
    }

    /**
     * Metodo que revisa si las respuestas del usuario son correctas
     * @param question mira la pregunta
     * @param answer resuelve si es correcta la respuesta
     * @return valida las respuestas
     */


    public boolean isCorrect(String question, String answer) {
        //depending on the question, it must comply with certain parameters to be a valid answer (and return true)
        switch (question) {
            case "\nEnter the phone number of the lead: ":
                try {
                    Integer.parseInt(answer);
                    if (answer.matches("[0-9]{9}")) {
                        return true;
                    } else {
                        System.err.println("Incorrect phone format! Phone number must only contain nine digits");
                        return false;
                    }
                } catch (Throwable exception) {
                    System.err.println("Invalid phone number! Please only insert numbers.");
                    return false;
                }
            case "\nEnter the lead contact e-mail: ":
                if (answer.matches("^(.+)@(\\S+)$")) {
                    return true;
                } else {
                    System.err.println("The e-mail was invalid. Please write a valid e-mail.");
                    return false;
                }
            case "Which vehicle is the client interested in? HYBRID, FLATBED o BOX: \n":
                if (answer.matches("hybrid|flatbed|box")) {
                    return true;
                } else {
                    System.err.println("That was not a valid vehicle! Please introduce either HYBRID, FLATBED o BOX");
                    return false;
                }
            case "How many of them? \n":
            case "Enter the number of employees: \n":
                try {
                    Integer.parseInt(answer);
                    return true;
                } catch (Throwable exception) {
                    System.err.println("That is not a number! Please only insert numbers.");
                    return false;
                }
            default:
                return true;
        }
    }

    private void createLead(){
        System.out.println(leadString[0]);

        String leadQuest[] = new String[leadString.length-1];
        System.arraycopy(leadString,1, leadQuest, 0, leadQuest.length);
        List<String> clientData = getInputData(leadQuest);

        String salesRepName = clientData.get(4);
        revisarSalesRep(salesRepName);
        Lead lead = new Lead(clientData.get(0), clientData.get(1), clientData.get(2), clientData.get(3));

        leadService.save(lead);
    }

    private SalesRep revisarSalesRep(String salesRepName) {
        SalesRep salesRep1 = new SalesRep();
        for (SalesRep salesRep : salesRepService.getAll()) {
            if (!salesRep.getName().equals(salesRepName)){
                salesRep1.setName(salesRepName);
                salesRepService.save(salesRep1);
            } else {
                salesRep1.setName(salesRep.getName());
            }
        }
        return salesRep1;
    }

    private void createSalesRep() {
        System.out.println(salesRepString[0]);

        String salesRepQuest[] = new String[salesRepString.length - 1];
        System.arraycopy(salesRepString,1, salesRepQuest, 0, salesRepQuest.length);
        List<String> clientData = getInputData(salesRepQuest);


        SalesRep salesRep = new SalesRep(clientData.get(0));
        salesRepService.save(salesRep);
    }


    /**
     * Metodo que printa los Lookup
     * @param element define el tipo de elemento para entrar el switch
     * @param id el id del elemento que queremos mostrar
     */
    private void printLookup(String element, int id){
        switch(element) {
        case "lead":
            try {
                Lead leadSel = leadService.getById(intId);
                int leadId = leadSel.getId();
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %15s %25s %25s", "ID", "Name", "Phone number", "Email", "Company\n");
                System.out.printf("%5s %15s %15s %25s %25s", leadId, leadSel.getName(), leadSel.getPhoneNumber(), leadSel.getEmail(), leadSel.getCompanyName());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
            }
            break;

        case "salesrep":
            try {
                SalesRep salesRep = salesRepService.getById(intId);
                int salesRepId = salesRep.getId();
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s", "ID", "Name\n");
                System.out.printf("%5s %15s", salesRepId, salesRep.getName());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
            }
            break;
        case "opportunity":
            try {
                Opportunity opportunity = opportunittyService.getById(intId);
                int opportunityId = opportunity.getId();
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %25s %25s %25s %25s", "ID", "Product", "Quantity","Decision maker","Status","SalesRep");
                System.out.printf("%5s %15s %25s %25s %25s %25s", opportunityId, opportunity.getProduct(), opportunity.getQuantity(), opportunity.getDecisionMaker(), opportunity.getStatus(),opportunity.getSalesRep());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
            }
            break;

        case "account":
            try {
                Account account = accountService.getById(intId);
                int accountId = account.getId();
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %25s %25s %25s %25s %25s", "ID", "Industry", "Employee count","City","Country","Contact List","Opportunity List");
                System.out.printf("%5s %15s %25s %25s %25s %25s %25s", accountId, account.getIndustry(), account.getEmployeeCount(),account.getCity(), account.getCountry(), account.getContactList(), account.getOpportunityList());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
            }
            break;
        }
    }
    private void printShow(String element){
        switch (element){
            case "lead":
                if(!leadService.getAll().isEmpty()){
                    System.out.println("\n-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s", "ID", "Name", "Phone number", "Email", "Company\n");
                    for (Lead lead : leadService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s", lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName() + lead.getSalesRep() + "\n");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                }
                break;

            case "salesrep":
                if(!salesRepService.getAll().isEmpty()){
                    System.out.println("\n-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s", "ID", "Name\n");
                    for (SalesRep salesRep : salesRepService.getAll()) {
                        System.out.printf("%5s %15s", salesRep.getId(), salesRep.getName());
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                }
                break;

            case "opportunity":
                if(!opportunittyService.getAll().isEmpty()){
                    System.out.println("\n-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s %25s", "ID", "Product", "Quantity","Decision maker","Status","SalesRep");
                    for (Opportunity opportunity : opportunittyService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s %25s", opportunity.getId(), opportunity.getProduct(), opportunity.getQuantity(), opportunity.getDecisionMaker(), opportunity.getStatus(),opportunity.getSalesRep());
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                }
                break;
            case "account":
                if(!accountService.getAll().isEmpty()){
                    System.out.println("\n-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s %25s %25s", "ID", "Industry", "Employee count","City","Country","Contact List","Opportunity List");
                    for (Account account: accountService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s %25s %25s", account.getId(), account.getIndustry(), account.getEmployeeCount(),account.getCity(), account.getCountry(), account.getContactList(), account.getOpportunityList());
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                }
                break;
        }
    }






    private void llenarTablas(){
        SalesRep salesRep1 = new SalesRep("Antonio");
        SalesRep salesRep2 = new SalesRep("Miguel");


        salesRepService.save(salesRep1);
        salesRepService.save(salesRep2);



//
//        Lead lead1 = new Lead("Diego", "987654321","diego@diego.com","DD", salesRep1);
//        Lead lead2 = new Lead("Javier", "123456789","javier@javier.com","JJ", salesRep1);
//        Lead lead3 = new Lead("Daniel", "321654987","daniel@daniel.com","DADA", salesRep2);
//        Lead lead4 = new Lead("Genaro", "789456123","Genaro@Genaro.com","GG", salesRep2);
//        Lead lead5 = new Lead("David", "789456123","David@David.com","dada", salesRep1);
//
//        leadService.save(lead1);
//        leadService.save(lead2);
//        leadService.save(lead3);
//
//        Contact contact1 = new Contact(lead4);
//        Contact contact2 = new Contact(lead5);
//
//        contactService.save(contact1);
//        contactService.save(contact2);
//
//        Account account1 = new Account("Manufacturing", 20, "Girona","Espania");
//        Account account2 = new Account("Ecommerce", 40, "Sevilla","Espania");
//        contact1.setAccount(account1);
//        contact2.setAccount(account2);
//
//        Opportunity opportunity1 = new Opportunity("hybrid", 45, contact1,salesRep1);
//        Opportunity opportunity2 = new Opportunity("flatbed",2,contact2, salesRep2);
//
//        contact1.setOpportunity(opportunity1);
//        contact2.setOpportunity(opportunity2);
//
//        opportunity1.setDecisionMaker(contact1);
//        opportunity2.setDecisionMaker(contact2);
//
//        account1.setOpportunity(opportunity1);
//        account1.setOpportunity(opportunity2);
    }



}
