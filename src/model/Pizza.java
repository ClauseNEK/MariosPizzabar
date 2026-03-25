package model;

public class Pizza {
    String name, description;
    int id;
    double price;

    public Pizza(int id, String name, String description, double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    //Vesuvius(1,"tomatsauce, ost, skinke og oregano", 57)

    @Override
    public String toString() {
        return id + ". " + name + " description: " + description + "Pris: " + price + "kr";
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
