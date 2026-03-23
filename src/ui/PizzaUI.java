package ui;

import model.*;
import service.PizzaOrder;
import util.ExceptionHandler;
import util.PizzaSorter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PizzaUI {
    private Scanner scanner = new Scanner(System.in);
    private PizzaOrderClass currentOrder;
    private int orderId = 0;
    private Customer customer;
    ArrayList<Pizza> selectedPizzas;

    public void start() {


        boolean running = true;

        while (running) {
            showmenu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    // CASE 1 | Fremviser PizzaArrayet og printer hele pizzaarayet ud (for loop)
                    case 1:
                        showpizzas();
                        break;

                    // CASE 2 | CSV - FileHandler

                    case 2: // Erstattes med vores FileHandler og CSV fil
                        neworder();
                        break;


                    // CASE 3 | Viser aktive ordrer | Giver Mario en idé om hvor mange pizzaer han skal lave

                    case 3:
                        if (currentOrder != null) {
                            System.out.println(currentOrder);
                            System.out.println(" ");// Dette printer både kunde og pizzaer (hvis toString er lavet)
                            PizzaSorter.sortById(selectedPizzas);
                            for (Pizza s : selectedPizzas){
                            System.out.println(s + " ");
                        }
                        } else {
                            System.out.println("Ingen aktive ordrer...");
                        }
                        break;


                    // CASE 4 | Simpel afslutning dog mangler en "Save" til vores CSV.

                    case 4:
                        System.out.println("Systemet afsluttes");
                        running = false;
                        break;
                    default:
                        System.out.println("\u001B[31mFejl i valg?\u001B[0m");
                }

            }
            catch (InputMismatchException e){
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            }
            catch (Exception e) {
                ExceptionHandler.handleUnexpectedError(e);
                scanner.nextLine();
            }
        }
    }

    public static void showmenu() {
        System.out.println("\n==== \uD83C\uDF55 Mario's PizzaBar \uD83C\uDF55 ====");
        System.out.println("1. Vis menukortet");
        System.out.println("2. Opret en ordre");
        System.out.println("3. Vis aktive ordrer");
        System.out.println("4. Save & Exit");

    }

    public static void showpizzas() {
        System.out.println("Viser menu....");
        PizzaArray PizzaListe = new PizzaArray();
        // Printer ny linje for hver pizza
        PizzaListe.printMenu();
    }

    public void neworder(){

        PizzaArray menu = new PizzaArray();
        selectedPizzas = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        LocalTime orderTime = LocalTime.now(); // time of order
        System.out.println("Oprettelse af ny ordre...");

        System.out.println("Vælg kundetype:");
        System.out.println("Kundetype: 1) Normal");
        System.out.println("Kundetype: 2) VIP");
        System.out.println("Kundetype: 3) Employee");
        int customerType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundeID:");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundens navn:");
        String name = scanner.nextLine();

        System.out.println("Indtast kundens tlf-nr:");
        String phoneNumber = scanner.nextLine();
//Fucking around and finding out **
        ArrayList<Integer> pizzas = new ArrayList<>();
        boolean choosingPizza = true;

        while (choosingPizza) {
            System.out.println("Menu kortet");
            PizzaArray pizzaList = new PizzaArray();
            pizzaList.printMenu();
            System.out.println("Vælg et nr: 1-14 ellers tryk 0 for afslutte");
            try {
                // ... inde i dit loop hvor brugeren taster tal ...
                int choice = scanner.nextInt();
                if (choice > 0 && choice <= 14) {
                    // Vi henter det RIGTIGE pizza-objekt fra menuen
                    Pizza chosen = menu.getPizzaById(choice);
                    if (chosen != null) {
                        selectedPizzas.add(chosen);
                        System.out.println("Tilføjet: " + chosen.getName());
                    }
                } else if (choice == 0) {
                    choosingPizza = false;
                }

            } catch (InputMismatchException e) {
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            } catch (Exception e) {
                ExceptionHandler.handleUnexpectedError(e);
                scanner.nextLine();
            }

        }
        this.customer = switch (customerType) {
            case 2 -> new VipCustomer(customerID, name, phoneNumber);
            case 3 -> new EmployeeCustomer(customerID, name, phoneNumber);
            default -> new NormalCustomer(customerID, name, phoneNumber);
        };

// Gem det hele i dit klasse-felt 'currentOrder'
        this.currentOrder = new PizzaOrderClass(orderId++, customer, LocalTime.now(), selectedPizzas);
    }


    public void PizzaOrderNew() {
        Scanner scanner = new Scanner(System.in);

        LocalTime orderTime = LocalTime.now(); // time of order
        System.out.println("Oprettelse af ny ordre...");

        System.out.println("Vælg kundetype:");
        System.out.println("Kundetype: 1) Normal");
        System.out.println("Kundetype: 2) VIP");
        System.out.println("Kundetype: 3) Employee");
        int customerType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundeID:");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundens navn:");
        String name = scanner.nextLine();

        System.out.println("Indtast kundens tlf-nr:");
        String phoneNumber = scanner.nextLine();
//Fucking around and finding out **
        ArrayList<Integer> pizzas = new ArrayList<>();
        boolean choosingPizza = true;

        while (choosingPizza) {
            System.out.println("Menu kortet");
            PizzaArray pizzaList = new PizzaArray();
            pizzaList.printMenu();
            System.out.println("Vælg et nr: 1-14 ellers tryk 0 for afslutte");
            try {
                int PizzaChoice = scanner.nextInt();

                switch (PizzaChoice) {
                    case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14:
                        pizzas.add(PizzaChoice);
                        break;

                    case 0:
                        choosingPizza = false;

                        System.out.println("Aktiv Pizzaordrer:");
                        //System.out.println(pizzas);//classic arraylist
                        //one by one
                        for (int pizzaorder : pizzas) {
                            System.out.println("Nr. " + pizzaorder + " ");

                        }

                        break;

                    default:
                        System.out.println("Kun tal fra 0-14: ");
                }
            }
            catch (InputMismatchException e){
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            }
            catch (Exception e) {
                ExceptionHandler.handleUnexpectedError(e);
                scanner.nextLine();
            }
//Need too send data to PizzaOrderClass createOrder(int customerType, int customerId, String name,
//                                                 String phoneNumber, ArrayList<Pizza> pizzas,
//                                                 LocalTime pickupTime)
            // with local variables createOrder(customerType, customerID, name, phoneNumber, pizzaList, ordertime)



//            if (PizzaChoice == 0) {
//                choosingPizza = false;
//
//                    System.out.println("Aktiv Pizzaordrer:");
//                    System.out.println(pizzas);//classic arraylist
//                    //one by one
//                    for (int pizzaorder : pizzas) {
//                    System.out.println(pizzaorder);
//                }
//
//                    break;
//
//            }
//            else {
//                //scanner.nextLine();
//                pizzas.add(PizzaChoice);
//            }


        }
        //currentOrder = new PizzaOrderClass(customerType, customerID, name, phoneNumber, pizzas, orderTime);
        ArrayList<Pizza> selectedPizzas = new ArrayList<>();
        PizzaArray menu = new PizzaArray(); // Vi henter menuen

        for (int id : pizzas) {
            // Her skal du have en metode i din PizzaArray der kan finde en pizza ud fra ID
            Pizza p = menu.getPizzaById(id);
            if (p != null) {
                selectedPizzas.add(p);
            }
        }

// Gem i den statiske variabel
        currentOrder = new PizzaOrderClass(orderId, customer, orderTime, selectedPizzas);
        System.out.println("Ordre oprettet succesfuldt!");

        //int orderId, Customer customer, LocalTime pickupTime, ArrayList<Pizza> pizzas

        //return new PizzaOrderClass(customerType, customerID, name, phoneNumber, pizzas, orderTime);
    }
}
                /*
//Fucking around and finding out **
            } else {
                Pizza chosenPizza = PizzaArray.FindPizzaById(PizzaChoice);

                if (choosingPizza != null) {
                    pizzas.add(chosenPizza);
                    System.out.println(chosenPizza.getName() + "Er tilføjet til ordren:");
                } else {
                    System.out.println("Forkert PizzaID");
                }
            }
        }

        System.out.println("Der er ikke blevet valgt nogen pizzaer");

        LocalTime pickupTime = LocalTime.now().plusMinutes(20);

        PizzaOrderClass orderClass = PizzaOrder.createOrder(
                customerType,
                customerID,
                name,
                phoneNumber,
                pizzas,
                pickupTime
        );


        ArrayList<PizzaOrderClass> OneOrder = new ArrayList<>();
        OneOrder.add(orderClass);
        file.FileHandler.savePizzaOrder(OneOrder);
        System.out.println("Ordren er blevet gemt og oprettet:");
        System.out.println(orderClass);
    }

    /*
    ID:
    KundeType:
    Navn:
    Tlf nummer:

     */

    /*
                        ArrayList<Pizza> pizzas = new ArrayList<>();
                    // Midlertidlig - INDTIL CSV
                    PizzaOrderClass orderClass = PizzaOrder.createOrder(
                            1,
                            100,
                            "Test",
                            "+45 12 12 12 12",
                            pizzas,
                            LocalTime.now().plusMinutes(20)
                    );
                    System.out.println("Ordren er oprettet!");
                    System.out.println(orderClass);





}*/