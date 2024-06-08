/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.estructuras.CircleLinkedListZ;
import com.espol.modelo.User;
import com.espol.modelo.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class EditarVehiculoController implements Initializable{
    @FXML
    private Button Volver;
    @FXML
    private Button EditaVehiculo;
    @FXML
    private Label marcamodeloano;
    @FXML
    private Label placamotortrans;
    @FXML
    private Label peso;
    @FXML
    private TextField kilo;
    @FXML
    private TextField ubi;
    @FXML
    private TextField precio;
    @FXML
    private ImageView fotocarro;
    private User usuario;
    private CircleLinkedListZ<Vehiculo> carros;
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }
    @FXML
    private void cambiarPantallaUsua(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController menuController=loader.getController();
        menuController.setUsuario(usuario);
        menuController.mostrarUsuario(usuario.toString());
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kilo.setEditable(false);
        ubi.setEditable(false);
        precio.setEditable(false);
        ArrayListZ<Vehiculo> lcarros = Vehiculo.readListFileSer("vehiculos.ser");
        carros = new CircleLinkedListZ<>();
        for (Vehiculo i : lcarros){
            
            /*if (i.getUserID().equals(String.valueOf(usuario.getID()))){
                carros.add(i);
            }*/
        }
        carros.add(lcarros.get(0));
        carros.add(lcarros.get(1));
        carros.add(lcarros.get(2));
        if (!carros.isEmpty()) {
            mostrarInformacionVehiculo(carros.get(0));
        }
    }
    private void mostrarInformacionVehiculo(Vehiculo vehiculo) {
        marcamodeloano.setText(vehiculo.getMarca() + " " + vehiculo.getModelo() + " " + vehiculo.getAno());
        placamotortrans.setText("Placa: " + vehiculo.getPlaca() + "\nMotor: " + vehiculo.getMotor() + "\nTransmisión: " + vehiculo.getTransmision());
        peso.setText("Peso: " + vehiculo.getPeso());
        kilo.setText(String.valueOf(vehiculo.getKilometraje()));
        ubi.setText(vehiculo.getUbicacion());
        precio.setText(String.valueOf(vehiculo.getPrecio()));
        try {
            Image imagen = new Image("file:"+vehiculo.getFoto());
            fotocarro.setImage(imagen);
        } catch (IllegalArgumentException e) {
            System.err.println("URL de imagen no válida o recurso no encontrado: " + vehiculo.getFoto());
            e.printStackTrace();
        }
    }
}
