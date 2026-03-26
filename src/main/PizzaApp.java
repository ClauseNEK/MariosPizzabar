package main;
import file.FileHandler;
import model.PizzaArray;
import ui.PizzaUI;

import java.io.File;
import java.util.Arrays;

public class PizzaApp {
    public static void main(String[] args) {
        PizzaUI pizzaUI = new PizzaUI();
        PizzaArray pizzaArray = new PizzaArray();
        pizzaUI.start();

        //temporary test:
        System.out.println("orders: " + FileHandler.countAllOrders());
        System.out.println("Pizza sold by pizza#: " + Arrays.toString(FileHandler.countPizzaSales()));


    }
}

