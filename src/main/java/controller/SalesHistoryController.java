package controller;

import data.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.SalesRecord;
import model.Customer;

import java.io.IOException;
import java.time.LocalDate;

public class SalesHistoryController {

    @FXML private TableView<SalesRecord> tblSales;

    @FXML private TableColumn<SalesRecord, Integer> colId;
    @FXML private TableColumn<SalesRecord, LocalDate> colDate;
    @FXML private TableColumn<SalesRecord, String> colCustomer;
    @FXML private TableColumn<SalesRecord, String> colVehicle;
    @FXML private TableColumn<SalesRecord, String> colSeller;
    @FXML private TableColumn<SalesRecord, Double> colPrice;
    @FXML private TableColumn<SalesRecord, String> colPayment;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("salesDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));


        colCustomer.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCustomer().getFullName())
        );

        colVehicle.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVehicle().getDescription())
        );

        colSeller.setCellValueFactory(cellData -> {
            if (cellData.getValue().getSeller() != null) {
                return new SimpleStringProperty(cellData.getValue().getSeller().getFullName());
            }
            return new SimpleStringProperty("Unknown");
        });

        setupDateColumnFormat();
        setupPriceColumnFormat();
        loadData();


        setupContextMenu();

    }

    private void loadData() {
        ObservableList<SalesRecord> list = FXCollections.observableArrayList(Database.sales);
        tblSales.setItems(list);
    }

    private void setupContextMenu() {
        tblSales.setRowFactory(tv -> {
            TableRow<SalesRecord> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();
            MenuItem item = new MenuItem("Show Customer Details");

            item.setOnAction(event -> {
                SalesRecord selectedSale = row.getItem();
                if (selectedSale != null) {
                    openCustomerPopup(selectedSale.getCustomer());
                }
            });

            contextMenu.getItems().add(item);


            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    private void setupPriceColumnFormat() {
        colPrice.setCellFactory(tc -> new javafx.scene.control.TableCell<SalesRecord, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    String formattedPrice = String.format(new java.util.Locale("tr", "TR"), "%,.2f TL", price);
                    setText(formattedPrice);
                }
            }
        });
    }

    private void setupDateColumnFormat() {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy");
        colDate.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }

    private void openCustomerPopup(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerDetail.fxml"));
            Parent root = loader.load();

            CustomerDetailController controller = loader.getController();


            controller.setCustomerData(customer);

            Stage stage = new Stage();
            stage.setTitle("Customer: " + customer.getFullName());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}