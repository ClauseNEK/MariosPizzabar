package main;
import file.FileHandler;
import ui.PizzaUI;

public class PizzaApp {
    public static void main(String[] args) {
        PizzaUI pizzaUI = new PizzaUI();
        pizzaUI.start();

        //temporary test:
        System.out.println("orders: " + FileHandler.countAllOrders());
        System.out.println("Pizza Count: " + FileHandler.countPizzaSales());
        System.out.println("Best seller: " + FileHandler.findBestSeller());

        pizzaUI.pizzaAschiiArt1();
        pizzaUI.pizzaAschiiArt2();
    }
}