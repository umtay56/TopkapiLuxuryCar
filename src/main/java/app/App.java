package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import java.io.IOException;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException { //try-catch exception

        scene = new Scene(loadFXML("Login"), 1408, 768);

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo2.png")));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Logo is not found: " + e.getMessage());
        }

        stage.setTitle("Vehicle Gallery Management System");
        stage.setScene(scene);
        stage.show();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

