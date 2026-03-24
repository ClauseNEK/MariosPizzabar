package model;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PizzaOrderClass {
    private int orderId;
    private Customer customer;
    private LocalTime pickupTime, myDateObj;
    private ArrayList<Pizza> pizzas;

    public PizzaOrderClass(int orderId, Customer customer, LocalTime pickupTime, ArrayList<Pizza> pizzas){
        this.orderId = orderId;
        this.customer = customer;
        this.pizzas = pizzas;
        this.pickupTime = pickupTime;

    }

    public PizzaOrderClass(int customerType, int customerID, String name, String phoneNumber, ArrayList<Integer> pizzas, LocalTime orderTime) {
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }
    // Beregner samlede pris før rabat.
    public double getSubtotal(){
        double subtotal = 0.0;

        //lægger prisen fra alle pizzaer sammen.
        for (Pizza pizza : pizzas){
            subtotal += pizza.getPrice();
        }
        return subtotal;
    }

    //beregner hvor meget rabat kundetypen få.
    public double getDiscountAmount(){
        return getSubtotal() * customer.getDiscount();
    }

    //Beregner den endelig pris efter rabat
    public double getTotalPrice(){
        return getSubtotal() - getDiscountAmount();
    }

    @Override
    public String toString() {
        myDateObj = pickupTime;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return "OrderID: " + orderId
                + "\nKunde: " + customer.getName()
                + "\nTelefon: " + customer.getPhoneNumber()
                + "\nAfhentning: " + formattedDate   // Pizza's udskrives for sig
                + "\nPris(før rabat): " + getSubtotal() + "kr"
                + "\nRabat: " + getDiscountAmount() + "kr"
                + "\nEndelig pris: " + getTotalPrice() + "kr";

    }
}