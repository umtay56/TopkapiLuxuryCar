package model;

public abstract class Tyre extends Vehicle {

    private final String tyreBrand;
    private final int tyreNumber;

    //constructor method
    public Tyre(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate, String tyreBrand, int tyreNumber) {
        super(id, brand, model, fuelType, color, productionYear, kilometer, price, plate);
        this.tyreBrand = tyreBrand;
        this.tyreNumber = tyreNumber;
    }

    public String getTyreBrand() {
        return tyreBrand;
    }

    public int getTyreNumber() {
        return tyreNumber;
    }
}
