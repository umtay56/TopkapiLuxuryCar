package service;

import model.Tyre;

public class PlateChange extends Modification {
    private final String newPlate;

    public PlateChange(String newPlate) {
        super(20000);
        this.newPlate = newPlate;
    }

    @Override
    public void apply(Tyre vehicle) {
        vehicle.setPlate(newPlate);
        vehicle.setPrice(vehicle.getPrice() + this.serviceFee);
    }
}