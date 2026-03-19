/* package file;

import model.Pizza;

import model.*;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandler {



    public static void savePizzaOrder(ArrayList<PizzaOrderClass> orders) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

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
                        order.getOrderId() + "|" +
                                customerData + "|" +
                                order.getPickupTime() + "|" +
                                pizzaData
                );
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil");
        }
    }

    public static ArrayList<PizzaOrderClass> readPizzaCsv(String filename) {
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
            System.out.println("Fejl ved læsning af fil");
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
}


 */