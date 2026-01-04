package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Person;
import service.AuthService;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblMessage;

    private AuthService authService = new AuthService();

    @FXML
    public void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty()) {
            lblMessage.setText("Please enter your name.");
            return;
        }


        Person user = authService.login(username, password);

        if (user != null) {

            System.out.println("Logged In: " + user.getFullName() + " (" + user.getRole() + ")");


            openDashboard(user);


            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        } else {

            lblMessage.setText("Username or password is incorrect!");
        }
    }


    private void openDashboard(Person loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
            Parent root = loader.load();


            DashboardController dashboardController = loader.getController();
            dashboardController.setUserInfo(loggedInUser);


            Stage stage = new Stage();
            stage.setTitle("Main Panel - Vehicle Gallery System");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblMessage.setText("Ana ekran açılırken hata oluştu!");
        }
    }
}