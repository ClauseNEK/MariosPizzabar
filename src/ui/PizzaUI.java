package ui;

import java.util.Scanner;

public class PizzaUI {
    public static void start() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (true) {
            System.out.println("==== \uD83C\uDF55 Mario's Pizza \uD83C\uDF55 ====");
            System.out.println("1. Vis menukortet:");
            System.out.println("2. Opret en ordre:");
            System.out.println("3. Vis aktive ordrer:");
            System.out.println("4. Save & Exit");

            System.out.print("Vælg en mulighed?:");

            int choice = scanner.nextInt();
            scanner.nextLine();
        }

    }
}