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
        System.out.println("4. vis gamle salg");
        System.out.println("5. Save & Exit");

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

        System.out.println("\nOrdren er oprettet og gemt i sales.txt");
        System.out.println(currentOrder);

        System.out.println("Valgte pizzaer:");
        PizzaSorter.sortById(selectedPizzas);
        for (Pizza pizza : selectedPizzas) {
            System.out.println("- " + pizza.getName() + " (" + pizza.getPrice() + " kr)");
        }
    }

    public void showActiveOrders() {
        ArrayList<PizzaOrderClass> orders = FileHandler.readPizzaCsv();

        if (orders.isEmpty()) {
            System.out.println("Ingen aktive ordrer fundet.");
            return;
        }

        System.out.println("\nAktive ordrer:");
        for (PizzaOrderClass order : orders) {
            System.out.println(order);
            System.out.println("Pizzaer:");
            for (Pizza pizza : order.getPizzas()) {
                System.out.println("- " + pizza.getName() + " (" + pizza.getPrice() + " kr)");
            }
            System.out.println("---------------------");
        }
    }
}