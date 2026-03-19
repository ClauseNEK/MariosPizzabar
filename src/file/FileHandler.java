package file;

import model.*;
import util.ExceptionHandler;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandler {

    public static void savePizzaOrder(String filename, ArrayList<PizzaOrderClass> pizzalist) {
        //try (maybe with resources)?
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            for (PizzaOrderClass pizza : pizzalist) {
                writer.println((pizza.getPizzas()) + ","
                        + pizza.getCustomer() + ","
                        + pizza.getOrderId() + ","
                        + pizza.getPickupTime());

                writer.close();
            }
        } catch (IOException e) {
//      Setup custom exception handler
//            ExceptionHandler.handle(
//                    new ExceptionHandler.FileWriteException(
//                            "Could not write file: " + filename
//                    )
//            );

        }
    }

    public ArrayList<PizzaOrderClass> readPizzaCsv() {
        String filePath = "src/file/pizzaorders.csv";
        ArrayList<PizzaOrderClass> allsales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] olddata = line.split(",");

                // 1. Ordre ID
                int orderId = Integer.parseInt(olddata[0].trim());

                // 2. Kunde (TYPE;ID;NAVN;TELEFON)
                String[] cParts = olddata[1].split(";");
                String type = cParts[0].trim();
                int cID = Integer.parseInt(cParts[1].trim());
                String cName = cParts[2].trim();
                String cPhone = cParts[3].trim();

                Customer customer = switch (type) {
                    case "VIP" -> new VipCustomer(cID, cName, cPhone);
                    case "Employee" -> new EmployeeCustomer(cID, cName, cPhone);
                    default -> new NormalCustomer(cID, cName, cPhone);
                };

                // 3. Tid
                LocalTime pickupTime = LocalTime.parse(olddata[2].trim());

                // 4. Pizza-liste (ID:NAVN:BESKRIVELSE:PRIS)
                ArrayList<Pizza> pizzaList = new ArrayList<>();
                String[] allPizzas = olddata[3].split(";");
                for (String pInfo : allPizzas) {
                    String[] p = pInfo.split(":");
                    pizzaList.add(new Pizza(
                            Integer.parseInt(p[0].trim()), // ID
                            p[1].trim(),                   // Navn
                            p[2].trim(),                   // Beskrivelse
                            Double.parseDouble(p[3].trim()) // Pris
                    ));
                }

                // 5. Gem ordren
                allsales.add(new PizzaOrderClass(orderId, customer, pickupTime, pizzaList));
            }
        } catch (Exception e) {
            System.err.println("Fejl ved indlæsning af pizza-data: " + e.getMessage());
        }
        return allsales;
    }
}





