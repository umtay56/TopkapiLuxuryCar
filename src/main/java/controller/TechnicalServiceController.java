package controller;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tyre;
import service.*; // Modifiye paketini import ettik

public class TechnicalServiceController {

    @FXML private TableView<Tyre> tblVehicles;
    @FXML private TableColumn<Tyre, String> colBrand;
    @FXML private TableColumn<Tyre, String> colModel;
    @FXML private TableColumn<Tyre, String> colPlate;
    @FXML private TableColumn<Tyre, String> colColor;
    @FXML private TableColumn<Tyre, Double> colPrice;

    @FXML private TextField txtNewColor;
    @FXML private TextField txtNewPlate;
    @FXML private ComboBox<String> cmbSeatType;
    @FXML private CheckBox chkMultimedia;

    @FXML
    public void initialize() {

        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        colPrice.setCellFactory(column -> new TableCell<Tyre, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {

                    setText(String.format(new java.util.Locale("tr", "TR"), "%,.0f", item));
                }
            }
        });
        refreshTable();

        if (cmbSeatType != null) 
        cmbSeatType.getItems().addAll("Leather", "Velvet", "Sport");
    }


    private Tyre getSelectedVehicle() {
        Tyre v = tblVehicles.getSelectionModel().getSelectedItem();
        if (v == null) {
            showAlert("Warning", "Please select a vehicle from the list first!");
        }
        return v;
    }


    @FXML
    public void applyColor() {
        Tyre v = getSelectedVehicle();
        if (v == null) return;

        if (txtNewColor.getText().isEmpty()) {
            showAlert("Warning", "Enter a color name!");
            return;
        }


        Modification process = new ColorCoating(txtNewColor.getText());
        process.apply(v);

        showSuccess("Color Changed!");
        refreshTable();
        txtNewColor.clear();
    }


    @FXML
    public void applyPlate() {
        Tyre v = getSelectedVehicle();
        if (v == null) return;

        if (txtNewPlate.getText().isEmpty()) {
            showAlert("Warning", "Enter a plate number!");
            return;
        }

        Modification process = new PlateChange(txtNewPlate.getText());
        process.apply(v);

        showSuccess("Plate Changed!");
        refreshTable();
        txtNewPlate.clear();
    }


    @FXML
    public void applySeat() {
        Tyre v = getSelectedVehicle();
        if (v == null) return;

        String seat = cmbSeatType.getValue();
        if (seat == null) {
            showAlert("Warning", "Select a seat type!");
            return;
        }

        Modification process = new SeatCovering(seat);
        process.apply(v);

        showSuccess("Seats Upgraded to " + seat);
        refreshTable();
    }


    @FXML
    public void applyMultimedia () {
        Tyre v = getSelectedVehicle();
        if (v == null) return;

        if (!chkMultimedia.isSelected()) {
            showAlert("Warning", "Check the box to confirm multimedia installation.");
            return;
        }

        Modification process = new Multimedia();
        process.apply(v);

        showSuccess("Multimedia System Installed!");
        refreshTable();
        chkMultimedia.setSelected(false);
    }

    private void refreshTable() {
        ObservableList<Tyre> list = FXCollections.observableArrayList(Database.inventory);
        tblVehicles.setItems(list);
        tblVehicles.refresh();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showSuccess(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Technical Service Operation Complete");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}