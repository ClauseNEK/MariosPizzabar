package util;

import java.io.IOException;
import java.util.InputMismatchException;


public class ExceptionHandler {

    public static void handleInputMismatch(InputMismatchException e) {

        System.out.println("Indput fejl: Kun tal.");

    }

    public static void handleFileError(IOException e) {

        System.out.println("Fil fejl opstod.");
        e.printStackTrace();

    }

    public static void handleNullError(NullPointerException e) {

        System.out.println("Fejl: Null værdi fundet.");
        e.printStackTrace();

    }

    public static void handleUnexpectedError(Exception e) {

        System.out.println("Uforudset fejl.");
        e.printStackTrace();

    }
}
