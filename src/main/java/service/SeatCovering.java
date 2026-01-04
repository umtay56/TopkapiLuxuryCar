package service;

import model.Tyre;

public class SeatCovering extends Modification {
    final String material;

    public SeatCovering(String material) {
        super(15000);
        this.material = material;
    }

    @Override
    public void apply(Tyre vehicle) {

        vehicle.setPrice(vehicle.getPrice() + this.serviceFee);

    }
}