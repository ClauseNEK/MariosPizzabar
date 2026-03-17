package model;

public class VipCustomer extends Customer {

    public VipCustomer(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    @Override
    public double getDiscount() {
        return 0.10;
    }


}
