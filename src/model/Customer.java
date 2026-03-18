package model;

public abstract class Customer {

    private int customerID;
    private String name;
    private String phoneNumber;

    public Customer(int customerID, String name, String phoneNumber) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName()  {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract double getDiscount();

    @Override
    public String toString() {
        return "Navn: " + name + ", Telefon: " + phoneNumber;
    }
}
