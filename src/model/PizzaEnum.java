package model;

public enum PizzaEnum {
    // Enum constants (each has its own description)
    Vesuvius("tomatsauce, ost, skinke og oregano", 57),
    Amerikaner ("tomatsauce, ost, oksefars og oregano",53),
    Cacciatore ("tomatsauce, ost, pepperoni og oregano",57);

    // Field (variable) to store the description text
    private String description;
    private int price;

    // Constructor (runs once for each constant above)
    private PizzaEnum(String description, int price) {
        this.description = description;
        this.price = price;
    }

    // Getter method to read the description
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}

