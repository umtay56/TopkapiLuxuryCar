package service;

import data.Database;
import model.Tyre;

public class StockManager {

    public void removeVehicleFromInventory(Tyre vehicle) {
        if (Database.inventory.contains(vehicle)) {
            Database.inventory.remove(vehicle);
            System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + "was deducted from stock.");
        } else {
            System.out.println("Error: Vehicle not found in stock!");
        }
    }
}