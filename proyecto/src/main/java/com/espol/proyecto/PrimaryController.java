package com.espol.proyecto;

import com.espol.modelo.User;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrimaryController {
    @FXML
    private VBox contenedor;
    @FXML
    private Button inicio;
    @FXML
    private Button registrar;
    @FXML
    private Button aceptar;
    
    String email, password,nombre;
    
    @FXML
    private void registrar(){
        contenedor.getChildren().clear();
        Label emailLabel = new Label("Ingrese correo:");
        TextField emailField = new TextField();
        
        Label conLabel = new Label("Ingrese contraseña:");
        TextField conField = new TextField();
        
        Label nomLabel = new Label("Ingrese Nombre se Usuario");
        TextField nomField = new TextField();
        aceptar = new Button("Aceptar");
        
        aceptar.setOnAction(event -> {
            email=emailField.getText();
            password=conField.getText();
            nombre=nomField.getText();
            try {
                RegistrarUsuario();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(email+password);
        });
        contenedor.getChildren().addAll(emailLabel,emailField,conLabel,conField,nomLabel,nomField,aceptar);
    }
    private void RegistrarUsuario() throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(email+","+password+","+nombre+"\n");
            App.setU(new User(email,password,nombre));
        }
    }
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
