package file;

import model.*;
import model.PizzaOrderClass;
import util.ExceptionHandler;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void savePizzaOrder(String filename, ArrayList<PizzaOrderClass> pizzalist){
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
        }
        catch(IOException e){

            ExceptionHandler.handle(
                    new ExceptionHandler.FileWriteException(
                            "Could not write file: " + filename
                    )
            );
            }
        }
    }

    public static void readPizzaCsv(ArrayList<Pizza> pizzaarray){
        String filePath = "src/file/pizzaorders.csv";
        String line;
    //try
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] olddata = line.split(",");

                ArrayList<PizzaOrderClass> allsales = new ArrayList<>();
                int orderId = Integer.parseInt(olddata[0]); //int?
                Customer Customer = Customer.toString(olddata[1]);
                LocalTime pickupTime = LocalTime.parse(olddata[2]);
                pizzaarray pizza = PizzaEnum.valueOf(olddata[3]); //double?
                //Genre.valueOf(parts[1]);
                allsales.add(new PizzaOrderClass(orderId,Customer,pickupTime,pizza));
            }
        }


}
*/
