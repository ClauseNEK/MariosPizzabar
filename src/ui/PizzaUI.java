package ui;

import file.FileHandler;
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
    private ArrayList<Pizza> selectedPizzas;
    private PizzaArray pizzaArray = new PizzaArray();

    public void start() {
        boolean running = true;

        while (running) {
            showmenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        showpizzas();
                        break;

                    case 2:
                        neworder();
                        break;

                    case 3:
                        showActiveOrders();
                        break;

                    case 5:
                        System.out.println("Systemet afsluttes");
                        running = false;
                        break;

                    case 4:
                    System.out.println("Gamle ordrer fra CSV:");
                    System.out.println(FileHandler.readPizzaCsv());
                    break;

                    case 6:
                        sortCSVbyPrice();
                        break;
                    case 7:
                        printPizzaSales();
                        break;
                    case 8:
                        pizzaAschiiArt1();
                        pizzaAschiiArt2();
                        break;
                    case 9:
                        printPizzaSalesByName();
                        break;
                    case 10:
                        printPizzaSalesSort();
                        break;


                    default:
                        System.out.println("Fejl i valg");
                }


            } catch (InputMismatchException e) {
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            } catch (Exception e) {
                ExceptionHandler.handleUnexpectedError(e);
                scanner.nextLine();
            }
        }
    }

    public static void showmenu() {
        System.out.println("\n==== 🍕 Mario's PizzaBar 🍕 ====");
        System.out.println("1. Vis menukortet");
        System.out.println("2. Opret en ordre");
        System.out.println("3. Vis aktive ordrer");
        System.out.println("4. Vis gamle salg");
        System.out.println("5. Save & Exit");
        System.out.println("6. Sorter CSV efter pris");
        System.out.println("7. Hvor mange gange pizzaer er bestilt");
        System.out.println("8. Pizza Art");
        System.out.println("9. Hvor mange gange pizzaer er bestilt(shortversion)");
        System.out.println("10. Hvor mange gange pizzaer er bestilt(Tweaked)");

    }

    public static void showpizzas() {
        System.out.println("Viser menu...");
        PizzaArray pizzaListe = new PizzaArray();
        pizzaListe.printMenu();
    }

    public void neworder() {
        PizzaArray menu = new PizzaArray();
        selectedPizzas = new ArrayList<>();

        System.out.println("Oprettelse af ny ordre...");

        System.out.println("Vælg kundetype:");
        System.out.println("1) Normal");
        System.out.println("2) VIP");
        System.out.println("3) Employee");
        int customerType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundeID:");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Indtast kundens navn:");
        String name = scanner.nextLine();

        System.out.println("Indtast kundens tlf-nr:");
        String phoneNumber = scanner.nextLine();

        boolean choosingPizza = true;

        while (choosingPizza) {
            System.out.println("\nMenukort:");
            menu.printMenu();
            System.out.println("Vælg pizza nr. 1-14, eller 0 for at afslutte:");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 0) {
                    choosingPizza = false;
                } else {
                    Pizza chosenPizza = menu.getPizzaById(choice);

                    if (chosenPizza != null) {
                        selectedPizzas.add(chosenPizza);
                        System.out.println("Tilføjet: " + chosenPizza.getName());
                    } else {
                        System.out.println("Forkert pizza-ID.");
                    }
                }

            } catch (InputMismatchException e) {
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            } catch (Exception e) {
                ExceptionHandler.handleUnexpectedError(e);
                scanner.nextLine();
            }
        }

        if (selectedPizzas.isEmpty()) {
            System.out.println("Ingen pizzaer valgt. Ordren blev annulleret.");
            return;
        }

        LocalTime pickupTime = LocalTime.now().plusMinutes(20);

        currentOrder = PizzaOrder.createOrder(
                customerType,
                customerID,
                name,
                phoneNumber,
                selectedPizzas,
                pickupTime
        );

        ArrayList<PizzaOrderClass> oneOrder = new ArrayList<>();
        oneOrder.add(currentOrder);

        FileHandler.savePizzaOrder(oneOrder);

        System.out.println("\nOrdren er oprettet og gemt i sales.Csv");
        System.out.println(currentOrder);

        System.out.println("Valgte pizzaer:");
        PizzaSorter.sortById(selectedPizzas);
        for (Pizza pizza : selectedPizzas) {
            System.out.println("- " + pizza.getName() + " (" + pizza.getPrice() + " kr)");
        }
    }

    public void showActiveOrders() {
        System.out.println("\nAktive ordrer:");
        try {
            if (selectedPizzas != null){
                for (Pizza pizza : selectedPizzas) {
                    System.out.println("- " + pizza.getName() + " (" + pizza.getPrice() + " kr)");
                }
                System.out.println("---------------------");
            } else System.out.println("Ingen aktive ordrer");
        } catch (NullPointerException e) {
            ExceptionHandler.handleNullError(e);
            scanner.nextLine();
        }
    }

    public void sortCSVbyPrice(){
        ArrayList<PizzaOrderClass> importArray = new ArrayList<>();
        importArray = FileHandler.readPizzaCsv();
        PizzaSorter.PriceOrderComparator(importArray);
        System.out.println(importArray);
    }
//test for print hvor ofte en pizza er bestilt.
    public void printPizzaSales() {
        int[] pizzaCounts = FileHandler.countPizzaSales();

        for (int i = 1; i <pizzaCounts.length; i++ ){
            System.out.println(pizzaArray.getPizzaById(i) + "\u001B[32m" + " Salg: " + pizzaCounts[i] + "\u001B[0m");
        }
        }

    public void printPizzaSalesByName() {
        int[] pizzaCounts = FileHandler.countPizzaSales();

        for (int i = 1; i <pizzaCounts.length; i++ ){
            System.out.println(i + ". " + pizzaArray.getPizzaNameById(i) + "\u001B[32m" + " Salg: " + pizzaCounts[i] + "\u001B[0m");
        }
    }

    public void printPizzaSalesSort() {
        int[] pizzaCounts = FileHandler.countPizzaSales();
        System.out.println("Nummer Pizza Salg");
        for (int i = 1; i <pizzaCounts.length; i++ ){
            System.out.printf("%02d %-10s \t \u001B[32m %d\n\u001B[0m",i,pizzaArray.getPizzaNameById(i),pizzaCounts[i]);
        }
    }





    public void pizzaAschiiArt1(){
        System.out.print("""
                                ⣠⣤⣶⣶⣦⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣷⣤⠀⠈⠙⢿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⠆⠰⠶⠀⠘⢿⣿⣿⣿⣿⣿⣆⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⠏⠀⢀⣠⣤⣤⣀⠙⣿⣿⣿⣿⣿⣷⡀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢠⠋⢈⣉⠉⣡⣤⢰⣿⣿⣿⣿⣿⣷⡈⢿⣿⣿⣿⣿⣷⡀
                ⠀⠀⠀⠀⠀⠀⠀⡴⢡⣾⣿⣿⣷⠋⠁⣿⣿⣿⣿⣿⣿⣿⠃⠀⡻⣿⣿⣿⣿⡇
                ⠀⠀⠀⠀⠀⢀⠜⠁⠸⣿⣿⣿⠟⠀⠀⠘⠿⣿⣿⣿⡿⠋⠰⠖⠱⣽⠟⠋⠉⡇
                ⠀⠀⠀⠀⡰⠉⠖⣀⠀⠀⢁⣀⠀⣴⣶⣦⠀⢴⡆⠀⠀⢀⣀⣀⣉⡽⠷⠶⠋⠀
                ⠀⠀⠀⡰⢡⣾⣿⣿⣿⡄⠛⠋⠘⣿⣿⡿⠀⠀⣐⣲⣤⣯⠞⠉⠁⠀⠀⠀⠀⠀
                ⠀⢀⠔⠁⣿⣿⣿⣿⣿⡟⠀⠀⠀⢀⣄⣀⡞⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⡜⠀⠀⠻⣿⣿⠿⣻⣥⣀⡀⢠⡟⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⢰⠁⠀⡤⠖⠺⢶⡾⠃⠀⠈⠙⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠈⠓⠾⠇
                """);
    }

    public void pizzaAschiiArt2(){
        System.out.print("""
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠤⠴⠖⡚⢉⠍⡉⢡⠂⠔⡉⡙⠶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠒⡍⠩⢐⠨⢐⠂⡡⠌⡐⠌⠤⠘⡠⠑⡐⢂⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠚⢩⠠⠘⡐⠠⠃⡌⠄⡃⢌⠐⣤⣡⢮⣖⣻⣵⠿⠽⠛⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡶⠏⡀⠸⠈⡀⢆⠱⠈⢁⠶⢀⢱⢰⡾⢿⣶⣹⡾⠏⠉⠀⠀⠀⠀⠈⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⢀⡤⠞⡉⠰⠠⠡⢑⠨⠐⡂⢌⡘⢠⢦⡗⢯⣳⡿⠛⠋⠁⢀⣤⣶⢿⣻⣿⣶⣤⡈⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⣀⠴⢋⠐⠤⢁⠡⠡⠑⢂⠡⡑⣨⢴⡺⣏⡷⡞⠋⠁⠀⠀⠀⢤⣿⢯⣟⣯⠿⣽⣞⡯⣿⣎⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⢀⣼⠏⡐⠌⡨⠐⠌⢂⠥⠉⣄⡧⣞⢧⡯⠗⠉⠁⠀⢠⠖⢦⠀⠘⣿⣯⣟⣾⣟⡿⣷⣫⡿⣵⣿⠠⠱⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⣰⢿⣽⠤⠼⣤⡁⠎⡘⢠⡦⡟⣧⣽⠞⠫⠂⠀⡀⠒⠀⠈⠑⢅⢀⠘⡸⣷⣭⢾⣽⣻⣞⣏⣟⡷⢃⠂⠀⠈⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⣼⡿⠋⡇⠀⠀⠀⠙⣧⡞⡯⢵⣻⠞⠁⠊⠀⢀⣤⣶⡾⣟⣿⢷⣶⣤⣀⠈⠢⠙⡛⠾⠷⠿⠚⡋⠁⠊⠀⣀⣀⣈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠻⢤⡡⢨⡀⠀⠀⠀⠸⣯⣝⡿⠁⠀⡊⠀⣴⡿⣯⢷⣻⡽⣳⣟⡾⣽⣻⣶⠀⠀⠀⢁⠀⠀⠁⠀⣀⣶⣿⣿⣻⢯⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠙⠳⣔⡀⠀⠀⠀⣷⠏⠀⣠⡘⠀⣾⡿⣽⡽⣛⣳⣟⣷⣻⣽⢳⣟⡾⡇⠀⠐⠏⠇⠀⡠⣵⣿⣳⠿⣼⣳⡟⣾⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠙⠶⣀⢀⡇⠀⠀⠓⠃⠀⣿⡽⣯⡽⣿⡽⣞⣯⢷⢾⣟⡾⣽⡇⠀⠀⠀⠀⠀⡆⣿⣍⣿⣻⢧⡾⣽⡿⣞⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠈⠙⡏⠃⠦⣀⡀⠄⠘⢿⣧⣼⢷⣻⣟⣾⢻⣉⣹⣷⠟⠀⢀⠴⡄⠀⠀⢁⣿⡽⡞⣷⢿⣛⣷⣻⢽⡾⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⢸⠆⡴⢦⣑⠉⠓⢦⡀⠙⠛⠿⢧⣿⡾⠯⠟⠋⠁⠀⠀⠈⠗⠁⠠⢄⠀⠪⠻⣷⣯⣟⡽⣶⢯⡿⣤⣼⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⢸⢸⡀⠀⠈⠓⡎⡀⠙⠒⢄⡠⠐⠀⠀⠀⠀⠀⠀⠀⢁⣨⣤⣶⡶⣶⢦⣤⣀⠑⠈⠹⠛⠛⠛⠉⠩⠄⠈⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠸⣆⡇⠀⠀⠀⣧⠟⡇⠇⡠⢬⠙⠒⠤⣀⠀⠀⢁⣴⡿⣯⡷⣯⣽⢿⣯⣻⣽⢿⣦⠀⠀⠀⢰⡲⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⡇⣸⡄⢀⣄⡀⠀⠐⢿⣯⣽⣳⠿⣧⣟⣻⣶⣻⣞⠿⣞⣧⠀⠀⠀⠀⠀⠘⠓⠀⢈⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡋⢀⡇⠙⠤⠜⠀⠈⢳⠀⣀⠈⠳⢯⣟⣷⣻⢷⣫⣶⣯⢿⡽⣾⠆⠀⠀⠀⡐⡁⢁⣴⡿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⡁⢸⠇⠀⠀⠀⠀⠀⡞⢸⠁⠉⠲⢄⡈⠳⢯⣟⡏⢱⣟⡾⣽⡿⡀⢀⢤⡰⠀⢰⣿⢯⣟⣳⠿⣆⠀⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠇⢸⠀⠀⠀⠀⠀⣸⢁⡼⠀⠀⠀⠀⠙⣦⣠⡎⠻⢿⣽⣽⠞⠑⠁⠀⠉⢀⡆⢺⡿⣾⡝⣯⣟⡿⣆⠀⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⠴⠃⠀⠀⠀⠀⠀⠹⡴⠇⠀⠀⠀⠀⠀⠻⠏⡇⡖⠦⣌⡁⡁⣀⠀⠀⠀⠀⠀⠹⢟⣷⣹⢯⣟⣽⢿⡄⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢧⠃⠀⠈⢙⡆⢎⣁⡂⠀⡀⠀⠀⠈⢛⠯⣿⣮⣟⣽⣷⠀⠀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣾⠀⠀⠀⠸⣄⡼⠁⠙⢶⠈⠳⠄⢀⡀⠀⠐⣠⠭⡉⠂⢳⡀⠀⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡎⢰⠑⠲⢄⡙⠂⠀⠑⠚⠁⠀⠀⠹⡄⠀⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⢸⠀⠀⠀⠙⢶⡀⠀⠢⣄⡀⠀⠀⠑⣄⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠃⣸⠀⠀⠀⠀⠀⠙⡎⢐⡀⢝⠢⡀⠀⢸⡀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⠏⠁⠀⠀⠀⠀⠀⠀⣷⡏⠹⢆⡀⠉⠷⠆⣷
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠙⢦⡀⡤⠏
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡁⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⡇⠀
                                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀
                """);
    }

}