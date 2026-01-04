package model;

public class Car extends Tyre {

    private final String transmission;
    private final String bodyType;

    public Car(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate, String tyreBrand, int tyreNumber, String transmission, String bodyType) {
        super(id, brand, model, fuelType, color, productionYear, kilometer, price, plate, tyreBrand, tyreNumber);
        this.transmission = transmission;
        this.bodyType = bodyType;
    }

    public Car(int id, String brand, String model, double price, String transmission, String bodyType) {
        // super() method overloading
        super(id, brand, model, "Fuel", "White", 2024, 0, price, "No Plate", "Standard Tyre", 4);
        this.transmission = transmission;
        this.bodyType = bodyType;
    }

    @Override
    public String getVehicleType() {
        return "Car (" + bodyType + ")";
    }

    @Override
    public String getDescription() {
        return getBrand() + " " + getModel() + " - " + transmission + " Gear";
    }
    public String getTransmission() { return transmission; }
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