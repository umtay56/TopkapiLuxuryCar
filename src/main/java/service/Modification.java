package service;

import model.Tyre;

public abstract class Modification {

    protected double serviceFee;
    public Modification(double serviceFee) {
        this.serviceFee = serviceFee;
    }
    public abstract void apply(Tyre vehicle);
}