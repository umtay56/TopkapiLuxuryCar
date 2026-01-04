package interfaces;

public interface IVehicle {

    String getVehicleType();
    String getDescription();

    double getPrice();
    void setPrice(double price);
    void setColor(String color);
    void setPlate(String plate);


}