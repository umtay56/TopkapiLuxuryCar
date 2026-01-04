package controller;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import service.DiscountCalculator;

import java.time.LocalDate;
import java.util.Locale;

public class MakeSaleController {


    @FXML private ComboBox<Tyre> cmbVehicles;
    @FXML private Label lblPrice;
    @FXML private Label lblDiscountedPrice;
    @FXML private CheckBox chkAdminDiscount;
    @FXML private Label lblNoPermission;
    @FXML private Button btnCompleteSale;
    @FXML private CheckBox chkTestDrive;
    @FXML private TextField txtName;
    @FXML private TextField txtSurname;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTcNo;
    @FXML private TextField txtLicenseType;
    @FXML private TextField txtCity;
    @FXML private TextField txtDistrict;
    @FXML private TextArea txtAddress;


    @FXML private TextField txtCash;
    @FXML private TextField txtCredit;


    private Person currentUser;
    private double currentPrice = 0.0;

    @FXML
    public void initialize() {
        ObservableList<Tyre> list = FXCollections.observableArrayList(Database.inventory);
        cmbVehicles.setItems(list);


        cmbVehicles.setConverter(new javafx.util.StringConverter<Tyre>() {
            @Override
            public String toString(Tyre vehicle) {
                if (vehicle == null) return null;


                return vehicle.getBrand() + " " + vehicle.getModel() + " - " + vehicle.getFormattedPrice();

            }

            @Override
            public Tyre fromString(String string) {
                return null;
            }
        });

        cmbVehicles.setOnAction(e -> {
            updatePriceDisplay();
        });


        addThousandSeparator(txtCash);
        addThousandSeparator(txtCredit);
    }

    public void setSeller(Person person) {
        this.currentUser = person;


        service.SalesAuthorityChecker authorityChecker = new service.SalesAuthorityChecker();

        boolean hasPermission = (person instanceof model.Manager) || authorityChecker.hasPermission(person);

        if (!hasPermission) {
            btnCompleteSale.setDisable(true);
            lblNoPermission.setVisible(true);
            txtCash.setDisable(true);
            txtCredit.setDisable(true);

            cmbVehicles.setDisable(true);
        } else {

            btnCompleteSale.setDisable(false);
            lblNoPermission.setVisible(false);
            txtCash.setDisable(false);
            txtCredit.setDisable(false);
            cmbVehicles.setDisable(false);
        }

        if (person instanceof model.Manager) {
            chkAdminDiscount.setVisible(true);
            model.Manager m = (model.Manager) person;
            chkAdminDiscount.setText("Apply Manager Discount (%" + m.getAdminDiscount() + ")");
        } else {
            chkAdminDiscount.setVisible(false);
            chkAdminDiscount.setSelected(false);
        }
    }



    @FXML
    public void handleTestDrive() {

        if (chkTestDrive.isSelected()) {

            Tyre selected = cmbVehicles.getSelectionModel().getSelectedItem();

           service.TestDriveService service = new service.TestDriveService();
            boolean isSuccess = service.performTestDrive(selected);

            if (isSuccess) {

                showAlert("Test Drive", "Test Drive Completed Successfully!\nVehicle: " + selected.getBrand() + " " + selected.getModel());
            } else {

                chkTestDrive.setSelected(false);
                showAlert("Warning", "Please select a vehicle first!");
            }
        }
    }


    @FXML
    public void handleDiscountToggle() {
        updatePriceDisplay();
    }

    private void updatePriceDisplay() {
        Tyre selected = cmbVehicles.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        double originalPrice = selected.getPrice();
        Locale trLocale = new Locale("tr", "TR");


        DiscountCalculator discountCalculator = new DiscountCalculator();


        if (chkAdminDiscount.isSelected() && currentUser instanceof Manager) {

            currentPrice = discountCalculator.calculateDiscountedPrice(currentUser, originalPrice);

            lblPrice.setStyle("-fx-strikethrough: true; -fx-text-fill: #95a5a6;");
            lblDiscountedPrice.setVisible(true);
            String formattedDiscount = String.format(trLocale, "%,.0f", currentPrice);
            lblDiscountedPrice.setText(" -> " + formattedDiscount + " TL");
        } else {
            currentPrice = originalPrice;
            lblPrice.setStyle("-fx-strikethrough: false; -fx-text-fill: #e74c3c;");
            lblDiscountedPrice.setVisible(false);
        }

        String formatliNormal = String.format(trLocale, "%,.0f", originalPrice);
        lblPrice.setText(formatliNormal + " TL");
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


    @FXML
    public void handleSale() {
        try {

            Tyre selectedVehicle = cmbVehicles.getSelectionModel().getSelectedItem();
            if (selectedVehicle == null) {
                showAlert("Error", "Please select a vehicle.");
                return;
            }


            double cash = 0, credit = 0;
            if (!txtCash.getText().isEmpty()) {
                String cleanCash = txtCash.getText().replace(".", "").replace(",", "");
                cash = Double.parseDouble(cleanCash);
            }

            if (!txtCredit.getText().isEmpty()) {
                String cleanCredit = txtCredit.getText().replace(".", "").replace(",", "");
                credit = Double.parseDouble(cleanCredit);
            }


            service.PaymentValidator paymentValidator = new service.PaymentValidator();


            if (!paymentValidator.isPaymentSufficient(cash, credit, currentPrice)) {
                showAlert("Insufficient Funds", "Total payment must match the price: " + currentPrice + " TL");
                return;
            }


            String paymentMethod = paymentValidator.determinePaymentType(cash, credit);


            int newId = Database.people.size() + 1;
            Customer customer = new Customer(
                    newId, txtName.getText(), txtSurname.getText(),
                    txtPhone.getText(), txtEmail.getText(), txtLicenseType.getText(),
                    txtPhone.getText(), txtEmail.getText(), txtTcNo.getText(),
                    txtCity.getText(), txtDistrict.getText(), txtAddress.getText()
            );

            int saleId = Database.sales.size() + 1;
            SalesRecord record = new SalesRecord(
                    saleId, customer, (Employee) currentUser, selectedVehicle,
                    LocalDate.now(), currentPrice, paymentMethod, cash, credit
            );


            service.StockManager stockManager = new service.StockManager();
            stockManager.removeVehicleFromInventory(selectedVehicle);


            Database.sales.add(record);
            Database.people.add(customer);


            service.ReceiptBuilder receiptBuilder = new service.ReceiptBuilder();
            String receiptText = receiptBuilder.generateReceipt(record);


            showReceiptDialog(receiptText);


            finalizeSaleUI();

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numeric values for payment.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }


    private void finalizeSaleUI() {
        clearFields();
        cmbVehicles.setItems(FXCollections.observableArrayList(Database.inventory));
        lblDiscountedPrice.setVisible(false);
        lblPrice.setText("0.0 TL");
        chkAdminDiscount.setSelected(false);
        currentPrice = 0.0;
    }


    private void showReceiptDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sale Completed");
        alert.setHeaderText("Transaction Successful");
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    private void clearFields() {
        txtName.clear(); txtSurname.clear(); txtPhone.clear(); txtEmail.clear();
        txtTcNo.clear(); txtLicenseType.clear(); txtCity.clear(); txtDistrict.clear();
        txtAddress.clear(); txtCash.clear(); txtCredit.clear();
        cmbVehicles.getSelectionModel().clearSelection();
        chkTestDrive.setSelected(false);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}