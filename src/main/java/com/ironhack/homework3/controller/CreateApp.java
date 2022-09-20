package com.ironhack.homework3.controller;

import com.ironhack.homework3.model.*;
import com.ironhack.homework3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class CreateApp {

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

    private boolean appIsOn;
    private int intId;

    public CreateApp(){}

    /**
     * Metodo que inicia la aplicacion
     * La anotacion @PostConstruct hace que se inicie cuando los Beans ya se han creado
     * Esto evita que las tablas sean nulas
     *
     */
    @PostConstruct
    public void initializer() {
        appIsOn = true;
        runApp(System.in);
    }

    /**
     * Metodo de inicio de la aplicacion
     * Muestra el menu y hace posible la primera eleccion de la tarea que quiere hacer el usuario
     *
     * @param inputStream
     */
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
                    convertId();
                    break;
                case "close-lost":
                case "close-won":
                    changeOpportunityStatus(election);
                    printLookup("opportunity", intId);
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
                    System.err.println("\nThat was not a valid answer! Please write one of the options!\n\n");
            }
        }

    }

    public void reportMenu(String election){
        //remove "report" from  string if present
        if (Objects.equals(election.split(" ")[0], "report")){ election = election.substring(7);}
        switch (election) {
            // BY SALES REP
            case "lead by salesrep":
                salesRepService.countLeadsBySalesRep();
                System.out.println("entró");
                break;
            case "closed-won by salesrep":
                System.out.println(election);
                break;
            case "closed-lost by salesrep":
                System.out.println(election);
                break;
            case "open by salesrep":
                System.out.println(election);
                break;
                // BY PRODUCT
            case "opportunity by the product":
                System.out.println(election);
                break;
            case "closed-won by the product":
                System.out.println(election);
                break;
            case "closed-lost by the product":
                System.out.println(election);
                break;
            case "open by the product":
                System.out.println(election);
                break;
                // BY COUNTRY
            case "opportunity by country":
                System.out.println(election);
                break;
            case "closed-won by country":
                System.out.println(election);
                break;
            case "closed-lost by country":
                System.out.println(election);
                break;
            case "open by country":
                System.out.println(election);
                break;
                // BY CITY
            case "opportunity by city":
                System.out.println(election);
                break;
            case "closed-won by city":
                System.out.println(election);
                break;
            case "closed-lost by city":
                System.out.println(election);
                break;
            case "open by city":
                System.out.println(election);
                break;
                // BY INDUSTRY
            case "opportunity by industry":
                System.out.println(election);
                break;
            case "closed-won by industry":
                System.out.println(election);
                break;
            case "closed-lost by industry":
                System.out.println(election);
                break;
            case "open by industry":
                System.out.println(election);
                break;
                // EMPLOYEE COUNT STATES
            case "mean employeecount":
                System.out.println(election);
                break;
            case "median employeecount":
                System.out.println(election);
                break;
            case "max employeecount":
                System.out.println(election);
                break;
            case "min employeecount":
                System.out.println(election);
                break;
                // QUANTITY STATES
            case "mean quantity":
                System.out.println(election);
                break;
            case "median quantity":
                System.out.println(election);
                break;
            case "max quantity":
                System.out.println(election);
                break;
            case "min quantity":
                System.out.println(election);
                break;
                // OPPORTUNITY STATES
            case "mean opps per account":
                System.out.println(election);
                break;
            case "median opps per account":
                System.out.println(election);
                break;
            case "max opps per account":
                System.out.println(election);
                break;
            case "min opps per account":
                System.out.println(election);
        }
    }


    /**
     * Metodo que extrae el Id dela seleccion que realiza el usuario
     *
     * @param userInput
     * @return
     */
    public String extractIdFromElection(String userInput) {
        try {
            if (userInput.matches("^(lookup lead|lookup salesrep|lookup account|lookup opportunity|convert|close-lost|close-won)\s.+")) {
                String[] userInputSplit = userInput.split(" ");
                intId = Integer.parseInt(userInputSplit[(userInputSplit.length - 1)]);
                //deletes last word from userInput (from character 0 to last word obtained with lastindexOf)
                return userInput.substring(0, userInput.lastIndexOf(" "));
            } else if (userInput.matches("^(new lead|show leads|new salesrep|show opportunities|show accounts|show salesrep|exit)")) {
                return userInput;
            } else if (userInput.matches("^(report lead by salesrep|report closed-won by salesrep|report closed-lost by salesrep|report open by salesrep" +
                    "|report opportunity by the product|report closed-won by the product|report closed-lost by the product|report open by the product" +
                    "|report opportunity by country|report closed-won by country|report closed-lost by country|report open by country" +
                    "|report opportunity by city|report closed-won by city|report closed-lost by city|report open by city" +
                    "|report opportunity by industry|report closed-won by industry|report closed-lost by industry|report open by industry" +
                    "|mean employeecount|median employeecount|max employeecount|min employeecount" +
                    "|mean quantity|median quantity|max quantity|min quantity" +
                    "|mean opps per account|median opps per account|max opps per account|min opps per account)")) {

                reportMenu(userInput);
                return "report executed";
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
            "New SalesRep           \n" + //
            "Show SalesReps         \n" + //
            "Lookup SalesRep id     \n" + //
            "Convert id             \n" + //
            "Show Opportunities     \n" + //
            "Lookup Opportunity id  \n" + //
            "Show Accounts          \n" + //
            "Lookup Account id     \n" + //
            "Close-Lost id          \n" +
            "Close-Won id           \n" +
            "Exit                   \n\n" +
            "Please write your answer here:";

    String leadString[] = { "Please enter the values for the lead: name, phone number, e-mail and company name",
            "\nEnter the client's name:",
            "\nEnter the phone number of the lead: ",
            "\nEnter the lead contact e-mail: ",
            "\nEnter the name of the company:",
            "\nEnter the ID of the SalesRep:"};

    String salesRepString[] = {"Please enter the values for the SalesRep: name",
            "\nEnter the SalesRep name:"};

    String opportunityString[] = {"Which vehicle is the client interested in? HYBRID, FLATBED o BOX: \n",
            "How many of them? \n"};

    String accountString[] = {"Enter the company industry (Production, ecommerce, manufacturing, medical or other): \n",
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
                        System.out.println("\n");
                        return false;
                    }
                } catch (Throwable exception) {
                    System.err.println("Invalid phone number! Please only insert numbers.");
                    System.out.println("\n");
                    return false;
                }
            case "\nEnter the lead contact e-mail: ":
                if (answer.matches("^(.+)@(\\S+)$")) {
                    return true;
                } else {
                    System.err.println("The e-mail was invalid. Please write a valid e-mail.");
                    System.out.println("\n");
                    return false;
                }
            case "Which vehicle is the client interested in? HYBRID, FLATBED o BOX: \n":
                if (answer.matches("hybrid|flatbed|box")) {
                    return true;
                } else {
                    System.err.println("That was not a valid vehicle! Please introduce either HYBRID, FLATBED o BOX");
                    System.out.println("\n");
                    return false;
                }
            case "Which account do you want associate with this opportunity":
                try {
                    Integer.parseInt(answer);
                    if (accountService.getById(Integer.parseInt(answer)) == null){
                        System.err.println("Incorrect ID. Choose a correct one");
                        System.out.println("\n");
                        return false;
                    } else {
                        return true;
                    }
                } catch (Throwable exception) {
                    System.err.println("Write a number");
                    System.out.println("\n");
                    return false;
                }
            case "\nEnter the ID of the SalesRep:":
                try {
                    Integer.parseInt(answer);
                    if (salesRepService.getById(Integer.parseInt(answer)) == null){
                        System.err.println("Incorrect ID. Choose a correct one");
                        System.out.println("\n");
                        return false;
                    } else {
                        return true;
                    }
                } catch (Throwable exception) {
                    System.err.println("Write a number");
                    System.out.println("\n");
                    return false;
                }
            case "How many of them? \n":
            case "Enter the number of employees: \n":
                try {
                    Integer.parseInt(answer);
                    return true;
                } catch (Throwable exception) {
                    System.err.println("That is not a number! Please only insert numbers.");
                    System.out.println("\n");
                    return false;
                }
            default:
                return true;
        }
    }

    /**
     * Funcion que crea el Lead.
     * Printa la pregunta incial y luego separamos en dos Strings el String inicial
     * System.arraycopy - coge del String de inicio desde la posicion que le marcamos y lo copia en el de destino desde
     * la posicion deseada hasta el largo que queramos. En este caso no lo cogemos hasta el final. La ultima la usamos
     * para averiguar el SalesRep asociado
     * String leadQuest[] - preguntas sin el SalesRep
     * String leadSalesRep - pregunta del salesRep
     *
     * printShow muestra los SalesReps elegibles
     *
     * Recibimos la respuesta del usuario para el SalesRep y revisamos si es correcta. Si no es correcta devuelve el
     * tipo de error.
     *
     */
    private void createLead(){
        System.out.println(leadString[0]);

        String leadQuest[] = new String[leadString.length-2];
        String leadSalesRep[] = {leadString[leadString.length-1]};
        System.arraycopy(leadString,1, leadQuest, 0, leadQuest.length);

        List<String> clientData = getInputData(leadQuest);

        System.out.println("\nChoose the ID from the SalesRep");
        printShow("salesrep");
        List<String> clientSalesRep = getInputData(leadSalesRep);

        Lead lead = new Lead(clientData.get(0), clientData.get(1), clientData.get(2), clientData.get(3), salesRepService.getById(Integer.parseInt(clientSalesRep.get(0))));
        leadService.save(lead);

        System.out.println("New lead created.\n");

    }

    /**
     * CreateSalesRep
     * Recibe un nombre y lo guarda en la base de datos SalesRep añadiendo un ID automatico
     */
    private void createSalesRep() {
        System.out.println(salesRepString[0]);

        String salesRepQuest[] = new String[salesRepString.length - 1];
        System.arraycopy(salesRepString,1, salesRepQuest, 0, salesRepQuest.length);
        List<String> clientData = getInputData(salesRepQuest);


        SalesRep salesRep = new SalesRep(clientData.get(0));
        salesRepService.save(salesRep);
        System.out.println("New SalesRep created.\n");
    }

    public void convertId() {  //Crea el contacto, oportunidad y cuenta
        try {
            Lead lead = leadService.getById(intId);

            // CREA EL CONTACTO
            Contact contact = createContact(lead);

            // CREA LA OPORTUNIDAD
            Opportunity opportunity = createOpportunity(contact,lead);

            //BORRA LEAD
            leadService.delete(intId);

        } catch (Throwable exception) {
            System.err.println("" +
                    "\n######################################################\n" +
                    "The id was not found! Please enter a valid id number!\n" +
                    "######################################################\n");
        }
    }

    private Contact createContact(Lead lead){
        Contact contact = new Contact(lead);
        contactService.save(contact);
        return contact;
    }

    private Opportunity createOpportunity(Contact contact, Lead lead){
        List<String> opportunityData = getInputData(opportunityString);

        SalesRep salesRep = lead.getSalesRep();

        // Creamos oportunidad antes de asociar a una cuenta
        Opportunity opportunity = new Opportunity(opportunityData.get(0), Integer.parseInt(opportunityData.get(1)),contact,salesRep);

        // Preguntamos si queremos crear una cuenta o utilizar una existente para asociar a esta oportunidad
        System.out.println("Would you like to create a new Account? (Y/N)");
        Scanner scan = new Scanner(System.in);
        String election = scan.nextLine().toLowerCase();

        Account account = new Account();
        // Evaluamos si el usuario responde que quiere crear una cuenta
        if (election.equals("y")) {

            // Creamos la cuenta con el cuestionario de creacion de cuenta
            account = createAccount(contact);
            accountService.save(account);

            // Recogemos el ID de la cuenta para
            // int accountId = account.getId();

        } else if (election.equals("n")) {
            System.out.println("Choose the ID if one of the following account to associate to this opportunity");
            printShow("account");

            List<String> newOp = getInputData("Which account do you want associate with this opportunity");

            account = accountService.getById(Integer.parseInt(newOp.get(0)));
        }

        opportunity.setAccount(account);
        opportunittyService.save(opportunity);

        System.out.println("New Opportunity created.\n");

        contact.setAccount(account);
        contact.setOpportunity(opportunity);
        contactService.save(contact);
        return opportunity;
    }

    private Account createAccount(Contact contact){
        List<String> accountData = getInputData(accountString);
        Account account = new Account(accountData.get(0), Integer.parseInt(accountData.get(1)),accountData.get(2), accountData.get(3));
        account.setContact(contact);
        System.out.println("New Account created.\n");
        return account;
    }


    public void changeOpportunityStatus(String election) {
        try {
            Opportunity opportunity = opportunittyService.getById(intId);
            opportunity.setStatus(election);
            opportunittyService.update(opportunity);
            System.out.println("Changed Status to " + election + " to opportunity " + opportunity.getId() + ".");
        } catch (Throwable exception) {
            System.err.println("" +
                    "\n######################################################\n" +
                    "The id was not found! Please enter a valid id number!\n" +
                    "######################################################\n");
        }



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
                Lead leadSel = leadService.getById(id);
                int leadId = leadSel.getId();
                System.out.println("\n LEAD INFORMATION");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %15s %25s %25s", "ID", "Name", "Phone number", "Email", "Company" + "\n");
                System.out.printf("%5s %15s %15s %25s %25s", leadId, leadSel.getName(), leadSel.getPhoneNumber(), leadSel.getEmail(), leadSel.getCompanyName());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
                System.out.println("\n");
            }
            break;

        case "salesrep":
            try {
                SalesRep salesRep = salesRepService.getById(id);
                int salesRepId = salesRep.getId();
                System.out.println("\n SALESREP INFORMATION");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s", "ID", "Name\n");
                System.out.printf("%5s %15s", salesRepId, salesRep.getName());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
                System.out.println("\n");
            }
            break;
        case "opportunity":
            try {
                Opportunity opportunity = opportunittyService.getById(id);
                System.out.println("\n OPPORTUNITY INFORMATION");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %25s %25s %25s %25s %25s %25s", "ID", "Product", "Quantity","Decision maker ID", "Decision maker","Status","SalesRep ID", "SalesRep" + "\n");
                System.out.printf("%5s %15s %25s %25s %25s %25s %25s %25s", opportunity.getId(), opportunity.getProduct(), opportunity.getQuantity(),opportunity.getContact().getId(), opportunity.getContact().getName(), opportunity.getStatus(),opportunity.getSalesRep().getId(),opportunity.getSalesRep().getName());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
                System.out.println("\n");
            }
            break;

        case "account":
            try {
                Account account = accountService.getById(id);
                System.out.println("\n ACCOUNT INFORMATION");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %15s %25s %25s %25s", "ID", "Industry", "Employee count","City","Country" + "\n");
                System.out.printf("%5s %15s %25s %25s %25s", account.getId(), account.getIndustry(), account.getEmployeeCount(),account.getCity(), account.getCountry());
                System.out.println("\n\n");
            } catch (Throwable exception) {
                System.err.println("" +
                        "\n######################################################\n" +
                        "The id was not found! Please enter a valid id number!\n" +
                        "######################################################\n");
                System.out.println("\n");
            }
            break;
        }
    }
    private void printShow(String element){
        switch (element){
            case "lead":
                if(!leadService.getAll().isEmpty()){
                    System.out.println("\n LEADS INFORMATION");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s %15s %25s", "ID", "Name", "Phone number", "Email", "Company", "SalesRep ID", "SalerRep Name\n");
                    for (Lead lead : leadService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s %15s %25s", lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesRepId(), lead.getSalesRepName()+"\n");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                    System.out.println("\n");
                }
                break;

            case "salesrep":
                if(!salesRepService.getAll().isEmpty()){
                    System.out.println("\n SALESREPS INFORMATION");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s", "ID", "Name\n");
                    for (SalesRep salesRep : salesRepService.getAll()) {
                        System.out.printf("%5s %15s", salesRep.getId(), salesRep.getName()+"\n");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                    System.out.println("\n");
                }
                break;

            case "opportunity":
                if(!opportunittyService.getAll().isEmpty()){
                    System.out.println("\n OPPORTUNITIES INFORMATION");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s %25s %25s %25s", "ID", "Product", "Quantity","Decision maker ID", "Decision maker","Status","SalesRep ID", "SalesRep" + "\n");
                    for (Opportunity opportunity : opportunittyService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s %25s %25s %25s", opportunity.getId(), opportunity.getProduct(), opportunity.getQuantity(),opportunity.getContact().getId(), opportunity.getContact().getName(), opportunity.getStatus(),opportunity.getSalesRep().getId(),opportunity.getSalesRep().getName()+"\n");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                    System.out.println("\n");
                }
                break;
            case "account":
                if(!accountService.getAll().isEmpty()){
                    System.out.println("\n ACCOUNTS INFORMATION");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s %15s %25s %25s %25s", "ID", "Industry", "Employee count","City","Country" + "\n");
                    for (Account account: accountService.getAll()) {
                        System.out.printf("%5s %15s %25s %25s %25s", account.getId(), account.getIndustry(), account.getEmployeeCount(),account.getCity(), account.getCountry()+"\n");
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("There are currently no leads to display!");
                    System.out.println("\n");
                }
                break;
        }
    }


    /**
     * Funcion que llena las tablas de Leads y SalesReps
     *
     */
    private void llenarTablas(){
        SalesRep salesRep1 = new SalesRep("Antonio");
        SalesRep salesRep2 = new SalesRep("Miguel");
        SalesRep salesRep3 = new SalesRep("Miguel");
        SalesRep salesRep4 = new SalesRep("Miguel");

        salesRepService.save(salesRep1);
        salesRepService.save(salesRep2);
        salesRepService.save(salesRep3);
        salesRepService.save(salesRep4);

        Lead lead1 = new Lead("Diego", "987654321","diego@diego.com","DD", salesRep1);
        Lead lead2 = new Lead("Javier", "123456789","javier@javier.com","JJ", salesRep1);
        Lead lead3 = new Lead("Daniel", "321654987","daniel@daniel.com","DADA", salesRep2);
        Lead lead4 = new Lead("Genaro", "789456123","Genaro@Genaro.com","GG", salesRep3);
        Lead lead5 = new Lead("David", "789456123","David@David.com","dada", salesRep4);

        leadService.save(lead1);
        leadService.save(lead2);
        leadService.save(lead3);
        leadService.save(lead4);
        leadService.save(lead5);
    }



}
