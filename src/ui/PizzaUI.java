package ui;

import model.PizzaArray;
import model.PizzaEnum;
import model.PizzaOrderClass;
import service.PizzaOrder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaUI {
    public static void start() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==== \uD83C\uDF55 Mario's Pizza \uD83C\uDF55 ====");
            System.out.println("1. Vis menukortet:");
            System.out.println("2. Opret en ordre:");
            System.out.println("3. Vis aktive ordrer:");
            System.out.println("4. Save & Exit");

            System.out.print("Vælg en mulighed?:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    PizzaArray PizzaListe = new PizzaArray();
                    // Printer ny linje for hver pizza
                    PizzaListe.getPizzalist();
                    break;

                case 2: // Erstattes med vores FileHandler og CSV fil
                    ArrayList<PizzaEnum> pizzas = new ArrayList<>();
                    pizzas.add(PizzaEnum.Mafia);
                    pizzas.add(PizzaEnum.Hawaii);

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
                    break;

                case 4:
                    System.out.println("Systemet afsluttes");
                    running = false;
                    break;
                default:
                    System.out.println("Fejl i valg?");
            }

        }

    }
}