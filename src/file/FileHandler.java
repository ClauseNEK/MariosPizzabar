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
//      Setup custom exception handler
//            ExceptionHandler.handle(
//                    new ExceptionHandler.FileWriteException(
//                            "Could not write file: " + filename
//                    )
//            );
            }
        }
    }

    public ArrayList<PizzaOrderClass> readPizzaCsv(){
        String filePath = "src/file/pizzaorders.csv";
        String line;
        ArrayList<PizzaOrderClass> allsales = new ArrayList<>();
    //try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] olddata = line.split(",");


                int orderId = Integer.parseInt(olddata[0]); //int
                Customer Customer = Customer.toString(olddata[1]); //customer??
                LocalTime pickupTime = LocalTime.parse(olddata[2]);
                ArrayList<PizzaEnum> pizza = PizzaEnum.valueOf(olddata[3]); //Array?
                //Genre.valueOf(parts[1]);
                allsales.add(new PizzaOrderClass(orderId,Customer,pickupTime,pizza));
            }
        } catch (IOException e) {
            //      Setup custom exception handler
            e.printStackTrace();
        }
        return allsales;
}





