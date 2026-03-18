package model;

public class EmployeeCustomer extends Customer {

    public EmployeeCustomer(int customerID, String name, String phoneNumber) {
        super(customerID, name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.20;
    }
}
