package file;

import model.Pizza;

import model.*;
import util.ExceptionHandler;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {

    final static String filename = "src/file/sales.csv";

    public static void savePizzaOrder(ArrayList<PizzaOrderClass> orders) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true));

            for (PizzaOrderClass order : orders) {

                Customer c = order.getCustomer();

                String customerType = "Normal";
                if (c instanceof VipCustomer) {
                    customerType = "VIP";
                } else if (c instanceof EmployeeCustomer) {
                    customerType = "Employee";
                }

                String customerData = customerType + ";" +
                        c.getCustomerID() + ";" +
                        c.getName() + ";" +
                        c.getPhoneNumber();

                String pizzaData = "";
                for (Pizza p : order.getPizzas()) {
                    pizzaData += p.getId() + ":" +
                            p.getName() + ":" +
                            p.getDescription() + ":" +
                            p.getPrice() + ";";
                }

                writer.println(
                        getNextOrderId() + "|" +
                                customerData + "|" +
                                order.getPickupTime() + "|" +
                                pizzaData
                );
            }

            writer.close();

        } catch (IOException e) {
            ExceptionHandler.handleFileError(e);
        }
    }

    public static ArrayList<PizzaOrderClass> readPizzaCsv() {
        ArrayList<PizzaOrderClass> orders = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                int orderId = Integer.parseInt(data[0]);

                Customer customer = getCustomer(data);

                LocalTime pickupTime = LocalTime.parse(data[2]);

                ArrayList<Pizza> pizzas = new ArrayList<>();
                String[] pizzaParts = data[3].split(";");

                for (String pizzaInfo : pizzaParts) {
                    if (!pizzaInfo.isEmpty()) {
                        String[] p = pizzaInfo.split(":");

                        Pizza pizza = new Pizza(
                                Integer.parseInt(p[0]),
                                p[1],
                                p[2],
                                Double.parseDouble(p[3])
                        );

                        pizzas.add(pizza);
                    }
                }

                PizzaOrderClass order = new PizzaOrderClass(orderId, customer, pickupTime, pizzas);
                orders.add(order);
            }

            reader.close();

        } catch (IOException e) {
            ExceptionHandler.handleFileError(e);
        }

        return orders;
    }

    private static Customer getCustomer(String[] data) {
        String[] customerParts = data[1].split(";");
        String type = customerParts[0];
        int customerId = Integer.parseInt(customerParts[1]);
        String name = customerParts[2];
        String phone = customerParts[3];

        Customer customer;
        if (type.equals("VIP")) {
            customer = new VipCustomer(customerId, name, phone);
        } else if (type.equals("Employee")) {
            customer = new EmployeeCustomer(customerId, name, phone);
        } else {
            customer = new NormalCustomer(customerId, name, phone);
        }
        return customer;
    }

    public static int getNextOrderId() {
        int maxId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Spring tomme linjer over

                // Vi splitter kun ved det første skilletegn for at spare tid
                String[] data = line.split("\\|");
                int currentId = Integer.parseInt(data[0].trim());

                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Hvis filen ikke findes endnu, starter vi bare ved ID 1
            return 1;
        }

        return maxId + 1;
    }
    public static int countAllOrders() {
        ArrayList<PizzaOrderClass> orders = readPizzaCsv();
        return orders.size();
    }
    // metode til at tælle hvor mange gange en pizza er bestilt.
    public static Map<String, Integer> countPizzaSales() {
        ArrayList<PizzaOrderClass> orders = readPizzaCsv();
        Map<String, Integer> pizzaCount = new HashMap<>();
        //for loop deg går igennem alle vores orders.
        for (PizzaOrderClass order : orders){
            //denne loop går igennem alle vores pizza, inde i en order.
            for (Pizza pizza : order.getPizzas()){
                //get pizza navn
                String pizzaName = pizza.getName();
                //tæller pizza. Om en pizza er i loopen får den pizza +1
                pizzaCount.put(pizzaName, pizzaCount.getOrDefault(pizzaName,0) + 1);

            }

        }
        return pizzaCount;
    }
    //denne metode finder den mest solte pizza.
    public static String findBestSeller(){
        //caller på countPizzaSales for at få solgte pizza count.
        Map<String, Integer> pizzaCount = countPizzaSales();
        //variable til navnet på pizza, som starter på null.
        String bestSeller = null;
        //den mest soglte pizza.
        int highestCount = 0;
        //looper gennem hver pizzanavn plus pizza count
        for (Map.Entry<String, Integer> entry : pizzaCount.entrySet()){
            //if, for nuværende pizza om den er højere end highestCount. Om den er updaderer
            if (entry.getValue() > highestCount){
                highestCount = entry.getValue();
                bestSeller = entry.getKey();
            }
        }
        return bestSeller;
    }

}

