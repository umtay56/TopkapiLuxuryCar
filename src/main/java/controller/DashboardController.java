package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;

import java.io.IOException;

public class DashboardController {

    @FXML private BorderPane mainPane;
    @FXML private Label lblWelcome;

    private Person currentUser;

    public void setUserInfo(Person user) {
        this.currentUser = user;
        lblWelcome.setText("Welcome, " + user.getName());
    }


    @FXML
    public void showMakeSale() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MakeSale.fxml"));
            Parent root = loader.load();


            MakeSaleController controller = loader.getController();
            controller.setSeller(currentUser);


            mainPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showTechnicalService() {
        loadPage("TechnicalService");
    }

    @FXML
    public void showInventory() {

        loadPage("Inventory");
    }

    @FXML
    public void showHistory() {
        loadPage("SalesHistory");
    }

    @FXML
    public void showAddVehicle() {

        loadPage("AddVehicle");
    }

    @FXML
    public void showGalleryInfo() {

        loadPage("GalleryInfo");
    }


    @FXML
    public void handleLogout() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        System.out.println("Checked out.");
    }


    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlFileName + ".fxml"));
            Parent root = loader.load();
            mainPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}