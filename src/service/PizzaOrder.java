package service;
import java.time.LocalTime;

import model.*;

import java.util.ArrayList;

public class PizzaOrder {
       public PizzaOrderClass createOrder(int customerType, int customerId, String name,
                                          String phoneNumber, ArrayList<PizzaEnum> pizzas,
                                          LocalTime pickupTime){
           Customer customer;

           if (customerType == 1) {
               customer = new NormalCustomer(customerId, name, phoneNumber);
           } else if (customerType == 2) {
               customer = new VipCustomer(customerId, name, phoneNumber);
           } else {
               customer = new EmployeeCustomer(customerId, name, phoneNumber);
           }

           return new PizzaOrder(customerId, customer, pizzas, pickupTime);
       }
       }
   }


