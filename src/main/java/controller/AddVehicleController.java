package controller;

import data.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

public class AddVehicleController {

    @FXML private ComboBox<String> cmbType;
    @FXML private TextField txtBrand;
    @FXML private TextField txtModel;
    @FXML private TextField txtYear;
    @FXML private TextField txtPrice;
    @FXML private TextField txtFuel;
    @FXML private TextField txtColor;
    @FXML private TextField txtPlate;
    @FXML private TextField txtKm;
    @FXML private TextField txtTyreBrand;
    @FXML private TextField txtTyreNumber;
    @FXML private TextField txtTransmission;
    @FXML private TextField txtCc;
    @FXML private TextField txtCooling;
    @FXML private TextField txtLoad;
    @FXML private TextField txtAxle;

    @FXML
    public void initialize() {

        cmbType.getItems().addAll(
                "Sedan", "SUV", "Hatchback", "Sport",
                "Motorcycle",
                "Truck"
        );

        updateFieldAccess();

        cmbType.setOnAction(e -> {
            updateFieldAccess();
        });

        addThousandSeparator(txtPrice);
        addThousandSeparator(txtKm);

    }

    @FXML
    public void handleSave() {
        try {

            String type = cmbType.getValue();
            if (type == null) {
                showAlert("Error!", "Please select a vehicle type!");
                return;
            }


            int id = Database.inventory.size() + 1;
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            int year = Integer.parseInt(txtYear.getText().replace(".", ""));
            double price = Double.parseDouble(txtPrice.getText().replace(".", ""));
            String fuel = txtFuel.getText();
            String color = txtColor.getText();
            String plate = txtPlate.getText();
            int km = Integer.parseInt(txtKm.getText().replace(".", ""));
            int tNumber = Integer.parseInt(txtTyreNumber.getText().replace(".", ""));
            String tBrand = txtTyreBrand.getText();



            switch (type) {
                case "Sedan":
                    Database.inventory.add(new Sedan(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber, txtTransmission.getText()));
                    break;

                case "SUV":
                    Database.inventory.add(new SUV(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber, txtTransmission.getText()));
                    break;

                case "Hatchback":
                    Database.inventory.add(new Hatchback(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber, txtTransmission.getText()));
                    break;

                case "Sport":
                    Database.inventory.add(new Sport(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber, txtTransmission.getText()));
                    break;

                case "Motorcycle":
                    int cc = Integer.parseInt(txtCc.getText());
                    Database.inventory.add(new Motorcycle(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber, cc, txtCooling.getText()));
                    break;

                case "Truck":
                    double load = Double.parseDouble(txtLoad.getText());
                    int axle = Integer.parseInt(txtAxle.getText());
                    Database.inventory.add(new Truck(id, brand, model, fuel, color, year, km, price, plate, tBrand, tNumber,load, axle));
                    break;
            }

            showAlert("Successful", type + " successfully added to stock!");
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numbers for Year, Price, KM, and Tyre Count!");
        } catch (Exception e) {
            showAlert("Error", "An error occurred: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtBrand.clear(); txtModel.clear(); txtYear.clear();
        txtPrice.clear(); txtFuel.clear(); txtColor.clear();
        txtPlate.clear(); txtKm.clear(); txtTransmission.clear();
        txtCc.clear(); txtCooling.clear(); txtLoad.clear(); txtAxle.clear();
        cmbType.getSelectionModel().clearSelection();
        txtTyreBrand.clear();
        txtTyreNumber.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void updateFieldAccess() {
        String type = cmbType.getValue();

        txtTransmission.setDisable(true);
        txtCc.setDisable(true);
        txtCooling.setDisable(true);
        txtLoad.setDisable(true);
        txtAxle.setDisable(true);

        if (type == null) return;

        switch (type) {
            case "Sedan":
            case "SUV":
            case "Hatchback":
            case "Sport":
                txtTransmission.setDisable(false);
                break;

            case "Motorcycle":
                txtCc.setDisable(false);
                txtCooling.setDisable(false);
                break;

            case "Truck":
                txtLoad.setDisable(false);
                txtAxle.setDisable(false);
                break;
        }
    }

    private void addThousandSeparator(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }

            String plainText = newValue.replaceAll("[^\\d]", "");

            try {

                if (plainText.length() > 15) {
                    plainText = plainText.substring(0, 15);
                }

                long value = Long.parseLong(plainText);
                String formatted = String.format(new java.util.Locale("tr", "TR"), "%,d", value);

                if (!formatted.equals(newValue)) {
                    textField.setText(formatted);
                    textField.positionCaret(formatted.length());
                }
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
        });
    }
}
