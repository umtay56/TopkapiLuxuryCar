package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.*;

public class VehicleDetailController {

    @FXML private TextArea txtDetails;

    public void setVehicleData(Tyre v) {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(v.getId()).append("\n");
        sb.append("Brand: ").append(v.getBrand()).append("\n");
        sb.append("Model: ").append(v.getModel()).append("\n");
        sb.append("Type: ").append(v.getVehicleType()).append("\n");
        sb.append("Year: ").append(v.getProductionYear()).append("\n");
        sb.append("KM: ").append(v.getKilometer()).append("\n");
        sb.append("Fuel: ").append(v.getFuelType()).append("\n");
        sb.append("Price: ").append(v.getPrice()).append(" TL\n");
        sb.append("Plate: ").append(v.getPlate()).append("\n");
        sb.append("Color: ").append(v.getColor()).append("\n");
        if (v instanceof Tyre) {
            Tyre t = (Tyre) v;
            sb.append("Tyre Brand: ").append(t.getTyreBrand()).append("\n");
            sb.append("Tyre Count: ").append(t.getTyreNumber()).append("\n");
        }
        sb.append("--------------------------\n");
        sb.append("SPECIFIC FEATURES:\n");


        if (v instanceof Car) {
            Car c = (Car) v;
            sb.append("Gear: ").append(c.getTransmission()).append("\n");
        }

        if (v instanceof Motorcycle) {
            Motorcycle m = (Motorcycle) v;
            sb.append("Engine CC: ").append(m.getEngineCC()).append("\n");
            sb.append("Cooling: ").append(m.getCoolingSystem()).append("\n");
        }

        if (v instanceof Truck) {
            Truck t = (Truck) v;
            sb.append("Load Capacity: ").append(t.getLoadCapacity()).append(" Tons\n");
            sb.append("Axles: ").append(t.getAxleCount()).append("\n");
        }

        txtDetails.setText(sb.toString());
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) txtDetails.getScene().getWindow();
        stage.close();
    }
}