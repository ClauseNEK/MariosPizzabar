package model;

public enum PizzaEnum {
    // Enum constants (each has its own description)
    Vesuvius(1,"tomatsauce, ost, skinke og oregano", 57),
    Amerikaner (2,"tomatsauce, ost, oksefars og oregano",53),
    Cacciatore (3,"tomatsauce, ost, pepperoni og oregano",57),
    Carbonara (4, "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano",63),
    Dennis(5,"tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano",65),
    Berti(6,"tomatsauce, ost, bacon og oregano",57),
    Silvia(7, "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano", 61),
    Victoria(8,"tomatsauce, ost, skinke, ananas, champignon, løg og oregano",61),
    Torno(9,"tomatsauce, ost, skinke, bacon, kebab, chili og oregano",61),
    Capricciosa(10," tomatsauce, ost, skinke, champignon og oregano",61),
    Hawaii(11,"tomatsauce, ost, skinke, ananas og oregano",61),
    Le_Blisso(12,"tomatsauce, ost, skinke, rejer og oregano",61),
    Venezia(13,"tomatsauce, ost, skinke, bacon og oregano",61),
    Mafia(14,"tomatsauce, ost, pepperoni, bacon, løg og oregano",61);

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

