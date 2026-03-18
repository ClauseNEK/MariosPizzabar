package model;

public class VipCustomer extends Customer {

    public VipCustomer(int customerID, String name, String phoneNumber) {
        super(customerID, name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.10;
    }


}
