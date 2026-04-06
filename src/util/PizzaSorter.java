package util;

import model.Pizza;
import model.PizzaOrderClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PizzaSorter {

    public static void sortById(ArrayList<Pizza> pizza){
        Collections.sort(pizza, Comparator.comparingInt(Pizza::getId));
    }

    public static void PriceOrderComparator(ArrayList<PizzaOrderClass> order){
        Collections.sort(order, Comparator.comparingDouble(PizzaOrderClass::getTotalPrice));
    }

    public static void sortByPrice2(ArrayList<PizzaOrderClass> pizza){
        pizza.sort(new PriceOrderComparator());
    }
}
