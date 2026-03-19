package service;
import java.time.LocalTime;
import model.*;

import java.util.ArrayList;

public class PizzaOrder {
       public static PizzaOrderClass createOrder(int customerType, int customerId, String name,
                                                 String phoneNumber, ArrayList<Pizza> pizzas,
                                                 LocalTime pickupTime){
           // polymorfi fordi den refererer til Customer
           Customer customer;
           // bestemer hvilken customertype opprettes
           if (customerType == 1) {
               //normal kunde ingen rabat
               customer = new NormalCustomer(customerId, name, phoneNumber);
           } else if (customerType == 2) {
               //VIPCustomer 10% rabat
               customer = new VipCustomer(customerId, name, phoneNumber);
           } else {
               //EmployeeCustomer 20% rabat
               customer = new EmployeeCustomer(customerId, name, phoneNumber);
           }
           //returner ny ordre med: kunde, afhentingstid, og pizzaer
           return new PizzaOrderClass(customerId, customer, pickupTime, pizzas);
       }

    public static PizzaOrderClass createOrder() {
           return createOrder();
    }
}



