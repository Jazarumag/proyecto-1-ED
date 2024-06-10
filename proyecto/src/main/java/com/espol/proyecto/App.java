package com.espol.proyecto;

import com.espol.modelo.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
    private static User u;
    public static void setU(User u) {
        App.u = u;
    }
    public static User getU() {
        return u;
    }
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Garaje Tornillo EC");
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/espol/proyecto/files/GarajeTuercaLogo.png")));
        } catch (NullPointerException e) {
            System.out.println("Image resource not found: " + e.getMessage());
        }
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}