package util;

import java.util.Scanner;

public class ExceptionHandler {

    public static int getIntInput(Scanner scanner, String message) {
        while(true) {
            try {
                System.out.println(message);
                int input = scanner.nextInt();
                return  input;

            } catch (Exception e) {
                System.out.println("\u001B[31mFejl: Indtast et tal: 1-5\u001B[0m");
                scanner.nextLine();
            }
        }
    }

    public static String getStringInput(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
