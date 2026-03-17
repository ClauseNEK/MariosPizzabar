package model;

public enum PizzaEnum {
    // Enum constants (each has its own description)
    Vesuvius(1,"tomatsauce, ost, skinke og oregano", 57),
    Amerikaner (2,"tomatsauce, ost, oksefars og oregano",53),
    Cacciatore (3,"tomatsauce, ost, pepperoni og oregano",57),
    Carbonara (4, "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano",63),
    Dennis(5,"tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano",65),
    Berti(6,"tomatsauce, ost, bacon og oregano",57)
    // Field (variable) to store the description text
    private String description;
    private double price;
    private int id;

    // Constructor (runs once for each constant above)
    private PizzaEnum(int id, String description, int price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    // Getter method to read the description
    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

}

