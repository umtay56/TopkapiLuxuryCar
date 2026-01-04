package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

public class CustomerDetailController {

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTc;
    @FXML private TextField txtCity;
    @FXML private TextField txtLicense;
    @FXML private TextArea txtAddress;


    public void setCustomerData(Customer customer) {
        if (customer == null) return;


        txtName.setText(customer.getFullName());


        txtPhone.setText(customer.getPhoneNumber());
        txtEmail.setText(customer.getEmail());
        txtTc.setText(customer.getTcNo());


        String location = customer.getCity() + " / " + customer.getDistrict();
        txtCity.setText(location);


        txtLicense.setText(customer.getLicenseType());
        txtAddress.setText(customer.getFullAddress());
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}