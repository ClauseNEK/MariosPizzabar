package model;

import java.util.ArrayList;
//backup in case of ENUM SCREWUP
public class PizzaArray {

    public void getPizzalist() {
        ArrayList<Pizza> pizzalist = new ArrayList<>();
        Pizza pizza1 = new Pizza(1, "Vesuvius", "tomatsauce, ost, skinke og oregano", 57);
        Pizza pizza2 = new Pizza(2, "Amerikaner", "tomatsauce, ost, oksefars og oregano", 53);
        Pizza pizza3 = new Pizza(3, "Cacciatore", "tomatsauce, ost, pepperoni og oregano", 57);
        Pizza pizza4 = new Pizza(4, "Carbonara", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano", 63);
        Pizza pizza5 = new Pizza(5, "Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano", 65);
        Pizza pizza6 = new Pizza(6, "Berti", "tomatsauce, ost, bacon og oregano", 57);
        Pizza pizza7 = new Pizza(7, "Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano", 61);
        Pizza pizza8 = new Pizza(8, "Victoria ", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano", 61);
        Pizza pizza9 = new Pizza(9, "Torno", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano", 61);
        Pizza pizza10 = new Pizza(10, "Capricciosa ", "tomatsauce, ost, skinke, champignon og oregano", 61);
        Pizza pizza11 = new Pizza(11, "Hawaii", "tomatsauce, ost, skinke, ananas og oregano", 61);
        Pizza pizza12 = new Pizza(12, "Le Blisso", " tomatsauce, ost, skinke, rejer og oregano", 61);
        Pizza pizza13 = new Pizza(13, "Venezia", " tomatsauce, ost, skinke, bacon og oregano", 61);
        Pizza pizza14 = new Pizza(14, "Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano", 61);
        pizzalist.add(0, pizza1);
        pizzalist.add(1, pizza2);
        pizzalist.add(2, pizza3);
        pizzalist.add(3, pizza4);
        pizzalist.add(4, pizza5);
        pizzalist.add(5, pizza6);
        pizzalist.add(6, pizza7);
        pizzalist.add(7, pizza8);
        pizzalist.add(8, pizza9);
        pizzalist.add(9, pizza10);
        pizzalist.add(10, pizza11);
        pizzalist.add(11, pizza12);
        pizzalist.add(12, pizza13);
        pizzalist.add(13, pizza14);

        //print all pizza's
        for (Pizza pizza : pizzalist) {
            System.out.println(pizza + " ");
        }
    }
}
