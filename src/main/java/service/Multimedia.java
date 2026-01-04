package service;

import model.Tyre;

public class Multimedia extends Modification {
    public Multimedia() {
        super(18000);
    }

    @Override
    public void apply(Tyre vehicle) {
        vehicle.setPrice(vehicle.getPrice() + this.serviceFee);

    }
}