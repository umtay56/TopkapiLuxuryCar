package model;

import interfaces.IVehicle;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Vehicle implements IVehicle {
    private  String brand;
    private  String model;
    private String fuelType;
    public  String color;
    private  int productionYear;
    private  int kilometer;
    public  double price;
    public  String plate;
    private  int id;


    public Vehicle(int id, String brand, String model, String fuelType, String color, int productionYear, int kilometer, double price, String plate) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.color = color;
        this.productionYear = productionYear;
        this.kilometer = kilometer;
        this.price = price;
        this.plate = plate;
        this.id = id;
    }

    //constructor overloading
    public Vehicle(int id, String brand, String model, String fuelType, String color, int productionYear, double price) {
        this(id, brand, model, fuelType, color, productionYear, 0, price, "No Plate.");
    }

    public int getId() { return id; }
    public String getBrand() {
        return brand;
    }
    public String getPlate() {
        return plate;
    }
    public int getKilometer() {
        return kilometer;
    }
    public int getProductionYear() {
        return productionYear;
    }
    public String getColor() {
        return color;
    }
    public String getFuelType() {
        return fuelType;
    }
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getBrand() + " " + getModel() + " (" + getProductionYear() + ") - " + getPrice() + " TL";
    }

    public String getFormattedPrice() {
        NumberFormat trFormat = NumberFormat.getInstance(new Locale("tr", "TR"));
        return trFormat.format(this.price) + " TL";
    }
}