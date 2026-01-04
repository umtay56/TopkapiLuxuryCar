package model;

public class Truck extends Tyre {

    //truck properties
    private final double loadCapacity;
    private final int axleCount;


    public Truck(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate, String tyreBrand, int tyreNumber, double loadCapacity, int axleCount) {
        super(id, brand, model, fuelType, color, productionYear, kilometer, price, plate,tyreBrand ,tyreNumber);
        this.loadCapacity = loadCapacity;
        this.axleCount = axleCount;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    @Override
    public String getDescription() {
        return getBrand() + " " + getModel() + " [" + loadCapacity + " Ton Capacity]";
    }

    public double getLoadCapacity() { return loadCapacity; }
    public int getAxleCount() { return axleCount; }
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