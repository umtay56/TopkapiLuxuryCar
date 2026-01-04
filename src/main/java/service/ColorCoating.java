package service;

import model.Tyre;

public class ColorCoating extends Modification {
    private final String newColor;

    public ColorCoating(String newColor) {
        super(15000);
        this.newColor = newColor;
    }

    @Override
    public void apply(Tyre vehicle) {

        vehicle.setColor(newColor);
        double newPrice = vehicle.getPrice() + this.serviceFee;
        vehicle.setPrice(newPrice);


    }
}