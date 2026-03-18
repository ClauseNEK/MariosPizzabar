package model;

public class NormalCustomer extends Customer {

    public NormalCustomer(int customerID, String name, String phoneNumber) {
        super(customerID, name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.0;
    }
}
