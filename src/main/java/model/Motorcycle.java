package model;

public class Motorcycle extends Tyre {

    private final int engineCC;
    private final String coolingSystem;

    public Motorcycle(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate, String tyreBrand, int tyreNumber, int engineCC, String coolingSystem) {
        super(id, brand, model, fuelType, color, productionYear, kilometer, price, plate, tyreBrand, tyreNumber);
        this.engineCC = engineCC;
        this.coolingSystem = coolingSystem;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public String getDescription() {
        return getBrand() + " " + getModel() + " (" + engineCC + "cc, " + coolingSystem + ")";
    }

    //getter setters
    public int getEngineCC() {
        return engineCC;
    }
    public String getCoolingSystem() {
        return coolingSystem;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
}