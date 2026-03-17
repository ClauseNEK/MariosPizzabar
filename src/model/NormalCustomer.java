package model;

public class NormalCustomer extends Customer {

    public NormalCustomer(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.0;
    }
}
