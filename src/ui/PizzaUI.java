package ui;

import model.Pizza;
import model.PizzaArray;
import model.PizzaOrderClass;
import service.PizzaOrder;
import util.ExceptionHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaUI {
    public static void start() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==== \uD83C\uDF55 Mario's PizzaBar \uD83C\uDF55 ====");
            System.out.println("1. Vis menukortet");
            System.out.println("2. Opret en ordre");
            System.out.println("3. Vis aktive ordrer");
            System.out.println("4. Save & Exit");
            System.out.println(" ");
            int choice = scanner.nextInt();

            switch (choice) {

                // CASE 1 | Fremviser PizzaArrayet og printer hele pizzaarayet ud (for loop)

                case 1:
                    System.out.println("Viser menu....");
                    PizzaArray PizzaListe = new PizzaArray();
                    // Printer ny linje for hver pizza
                    PizzaListe.getPizzalist();
                    break;




                    // CASE 2 | CSV - FileHandler



                case 2: // Erstattes med vores FileHandler og CSV fil
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
                    break;



                    // CASE 3 | Viser aktive ordrer | Giver Mario en idé om hvor mange pizzaer han skal lave

                case 3:
                    System.out.println("Viser aktive ordrer...");
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
    }
}