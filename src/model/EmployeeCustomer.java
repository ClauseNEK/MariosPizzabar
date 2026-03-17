package model;

public class EmployeeCustomer extends Customer {

    public EmployeeCustomer(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.20;
    }
}
