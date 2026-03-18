package model;
import java.time.LocalTime;
import java.util.ArrayList;

public class PizzaOrderClass {
    private int orderId;
    private Customer customer;
    private LocalTime pickupTime;
    private ArrayList<PizzaEnum> pizzas;

    public PizzaOrderClass(int orderId, Customer customer, LocalTime pickupTime, ArrayList<PizzaEnum> pizzas){
        this.orderId = orderId;
        this.customer = customer;
        this.pizzas = pizzas;
        this.pickupTime = pickupTime;

    }
    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<PizzaEnum> getPizzas() {
        return pizzas;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public String toString() {
        return "OrderID: " + orderId + " " + "Kunde: " + customer.getName() + " " + "Telefon: " + customer.getPhoneNumber()
            + " " + "Afhentning: " + pickupTime;   // Pizza's udskrives for sig
    }
}
