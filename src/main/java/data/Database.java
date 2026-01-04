package data;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Tyre> inventory = new ArrayList<>();
    public static List<Person> people = new ArrayList<>();
    public static List<SalesRecord> sales = new ArrayList<>();

    static {

        inventory.add(new SUV(0, "Hyundai", "Tucson", "Gasoline", "Black", 2025, 6, 2850000, "34NLY25", "MICHELIN",4,"Automatic"));
        inventory.add(new Sedan(1, "Skoda", "Octovia", "Gasoline", "Moon White", 2025, 100, 3000000, "34FB34","GOODYEAR",4,"Automatic"));
        inventory.add(new Hatchback(2, "Hyundai", "Bayon", "Diesel", "Light Blue", 2024, 15000, 2450000, "34UMT123","MICHELIN",4, "Manual"));
        inventory.add(new Sport(3, "Audi", "PB18 E- Tron", "Electric", "Grey", 2025, 0, 8000000, "34SVL2004","LASSA",4,"Automatic"));


        inventory.add(new Motorcycle(4, "Yamaha", "MT-07", "Gasoline", "White", 2025, 0, 450000, "34BSE99","MICHELIN",2,689, "Water Cooled"));
        inventory.add(new Motorcycle(5, "Honda", "R7", "Gasoline", "Light Blue", 2025, 0, 600000, "34DMR34","CONTINENTAL",2, 800, "Water Cooled"));
        inventory.add(new Motorcycle(6, "Kawasaki", "Ninja H2R","Gasoline", "Black", 2025, 0, 2350000, "34AY00","LASSA",2, 1100, "Water Cooled"));


        inventory.add(new Truck(7, "Mercedes", "Actros", "Diesel", "Gri", 2019, 120000, 3500000, "34BAS34","GOODYEAR",8, 18.5, 4));
        inventory.add(new Truck(8, "Volvo ", "FMX 500", "Diesel", "Yellow", 2025, 0, 9000000, "34TR43","GOODYEAR",10,  30.5, 8));
        inventory.add(new Truck(9, "Ford", "CARGO 4142", "Diesel", "Orange", 2024, 144921, 12500000, "06TR55","MICHELIN",8, 32, 8));


        people.add(new Manager(1, "Umut", "Ay", true, "1234", 20));
        people.add(new Employee(2, "Şevval", "Baş", true,"1234"));
        people.add(new Employee(3, "Nilay", "Perktaş", false,"1234"));
        people.add(new Employee(4, "Buse", "Demir", false,"1234"));

    }
}