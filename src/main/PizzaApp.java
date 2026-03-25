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


        //pizzaUI.pizzaAschiiArt1();
        //pizzaUI.pizzaAschiiArt2();
    }
}
//Solgte:

//Vesuvius:     0
//Amerikaner:   1
//Cacciatore:   2
//Carbonara:    3
//Dennis:       4
//Berti:        5
//Silvia:       6
//Victoria:     6
//Torno:        7
//Capricciosa:  8
//Hawaii:       9
//Le_Blisso:    9
//Venezia:      7
//Mafia:        5
//eller bare sortering af hele listen?
//Bestseller1: navn + antal
//Bestseller2: navn + antal
//Bestseller3: navn + antal

