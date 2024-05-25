package com.espol.proyecto;

import com.espol.modelo.ArrayListZ;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
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
        
        ArrayListZ<String> lista = new ArrayListZ<>();
        System.out.println(Arrays.toString(lista.arreglo));
        lista.add("Hola");
        lista.add("Hello");
        lista.add("Bonjour");
        lista.add("Ciao");
        lista.add("Guten Morgen");
        lista.add("CHAO");
        lista.add("ADIOS");
        System.out.println(lista.contains("Hola"));
        System.out.println(lista.contains("No"));
        System.out.println(Arrays.toString(lista.arreglo));
        lista.remove("Hello");
        System.out.println(Arrays.toString(lista.arreglo));
        
        launch();
    }

}