package model;

public class Hatchback extends Car { //Inheritance

    public Hatchback(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate, String tyreBrand, int tyreNumber, String transmission) {
        super(id, brand, model, fuelType, color, productionYear, kilometer, price, plate, tyreBrand, tyreNumber, transmission, "Hatchback");
    }
}