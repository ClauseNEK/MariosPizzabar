package model;

import file.FileHandler;

import java.util.ArrayList;
//backup in case of ENUM SCREWUP
           public class PizzaArray {
            // Vi gemmer listen her, så den overlever mellem metodekald
            private ArrayList<Pizza> menu = new ArrayList<>();

            // Constructor: Denne kører ÉN gang når du skriver 'new PizzaArray()'
            public PizzaArray() {
                fillMenu();
            }

            private void fillMenu() {
                menu.add(new Pizza(1, "Vesuvius", "tomatsauce, ost, skinke og oregano", 57));
                menu.add(new Pizza(2, "Amerikaner", "tomatsauce, ost, oksefars og oregano", 53));
                menu.add(new Pizza(3, "Cacciatore", "tomatsauce, ost, pepperoni og oregano", 57));
                menu.add(new Pizza(4, "Carbonara", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano", 63));
                menu.add(new Pizza(5, "Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano", 65));
                menu.add(new Pizza(6, "Berti", "tomatsauce, ost, bacon og oregano", 57));
                menu.add(new Pizza(7, "Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano", 61));
                menu.add(new Pizza(8, "Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano", 61));
                menu.add(new Pizza(9, "Torno", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano", 61));
                menu.add(new Pizza(10, "Capricciosa", "tomatsauce, ost, skinke, champignon og oregano", 61));
                menu.add(new Pizza(11, "Hawaii", "tomatsauce, ost, skinke, ananas og oregano", 61));
                menu.add(new Pizza(12, "Le Blisso", " tomatsauce, ost, skinke, rejer og oregano", 61));
                menu.add(new Pizza(13, "Venezia", " tomatsauce, ost, skinke, bacon og oregano", 61));
                menu.add(new Pizza(14, "Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano", 61));

            }

            public void printMenu() {
                for (Pizza p : menu) {
                    System.out.println(p);
                }
            }

            // Her finder vi den rigtige pizza i listen baseret på ID
            public Pizza getPizzaById(int id) {
                for (Pizza p : menu) {
                    if (p.getId() == id) { // Bruger din getorderId fra Pizza-klassen
                        return p;
                    }
                }
                return null; // Hvis kunden taster nr. 99, som ikke findes
            }

            public String getPizzaNameById(int id) {
            for (Pizza p : menu) {
                if (p.getId() == id) { // Bruger din getId fra Pizza-klassen
                return p.getName();
            }
            }
            return null; // Hvis kunden taster nr. 99, som ikke findes
    }

        }
