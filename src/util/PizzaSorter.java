package util;

import model.Pizza;
import model.PizzaOrderClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PizzaSorter {

    public static void sortByName(ArrayList<Pizza> pizza){
        Collections.sort(pizza, Comparator.comparing(Pizza::getName));
    }

    public static void sortByPrice(ArrayList<Pizza> pizza){
        Collections.sort(pizza, Comparator.comparingDouble(Pizza::getPrice));
    }

    public static void sortByDescription(ArrayList<Pizza> pizza){

        Collections.sort(pizza, Comparator.comparing(Pizza::getDescription));
    }

    public static void sortById(ArrayList<Pizza> pizza){
        Collections.sort(pizza, Comparator.comparingInt(Pizza::getId));
    }

    public static void PriceOrderComparator(ArrayList<PizzaOrderClass> order){
        Collections.sort(order, Comparator.comparingDouble(PizzaOrderClass::getTotalPrice));
    }
}
