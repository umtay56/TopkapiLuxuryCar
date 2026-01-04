package controller;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tyre;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Locale;


public class InventoryController {

    @FXML private TableView<Tyre> tblVehicles;

    @FXML private TableColumn<Tyre, Integer> colId;
    @FXML private TableColumn<Tyre, String> colBrand;
    @FXML private TableColumn<Tyre, String> colModel;
    @FXML private TableColumn<Tyre, Integer> colYear;
    @FXML private TableColumn<Tyre, String> colFuel;
    @FXML private TableColumn<Tyre, Double> colPrice;
    @FXML private TableColumn<Tyre, String> colPlate;
    @FXML private TableColumn<Tyre, String> colType;
    @FXML private TableColumn<Tyre, Integer> colKm;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        colFuel.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        colKm.setCellValueFactory(new PropertyValueFactory<>("kilometer"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));


        setupPriceColumnFormat();
        setupKmColumnFormat();
        setupContextMenu();
        loadData();
    }

    private void setupPriceColumnFormat() {
        colPrice.setCellFactory(tc -> new TableCell<Tyre, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    String formattedPrice = String.format(new Locale("tr", "TR"), "%,.0f TL", price);
                    setText(formattedPrice);
                }
            }
        });
    }

    private void setupKmColumnFormat() {
        colKm.setCellFactory(tc -> new TableCell<Tyre, Integer>() {
            @Override
            protected void updateItem(Integer km, boolean empty) {
                super.updateItem(km, empty);
                if (empty || km == null) {
                    setText(null);
                } else {
                    String formattedKm = String.format(new Locale("tr", "TR"), "%,d KM", km);
                    setText(formattedKm);
                }
            }
        });
    }

    private void setupContextMenu() {
        tblVehicles.setRowFactory(tv -> {
            TableRow<Tyre> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem showDetailsItem = new MenuItem("Show Details");

            showDetailsItem.setOnAction(event -> {
                Tyre selectedVehicle = row.getItem();
                openDetailPopup(selectedVehicle);
            });

            contextMenu.getItems().add(showDetailsItem);

            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    private void openDetailPopup(Tyre vehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehicleDetail.fxml"));
            Parent root = loader.load();

            VehicleDetailController controller = loader.getController();
            controller.setVehicleData(vehicle);

            Stage stage = new Stage();
            stage.setTitle("Details: " + vehicle.getBrand());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadData() {
        ObservableList<Tyre> list = FXCollections.observableArrayList(Database.inventory);
        tblVehicles.setItems(FXCollections.observableArrayList(Database.inventory));
    }
}
