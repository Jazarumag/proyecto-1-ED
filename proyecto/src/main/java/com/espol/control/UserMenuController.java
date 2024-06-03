/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.modelo.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserMenuController {

    @FXML
    private Label saludoLabel;
    @FXML
    private Button botonLogout;
    @FXML
    private Button regisVehi;
    @FXML
    private Button comprVehi;

    private User usuario;
    
    @FXML
    private Button consultarOfer;
    @FXML
    private Button botonPerfil;
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }
    
    private void cambiarPantallaLogin(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/primary.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    public void mostrarUsuario(String nombre){
        saludoLabel.setText("Hola, "+nombre);
    }
    
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere cerrar la sesión?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaLogin(event);
        }
    }
    
    @FXML
    private void cambiarRegistroVehi(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/VenderVehiculo.fxml"));
        Parent root = (Parent) loader.load();
        VenderVehiculoController vehiculoControlador=loader.getController();
        vehiculoControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

    @FXML
    private void verPerfil(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/EditarCuenta.fxml"));
        Parent root = (Parent) loader.load();
        EditarCuentaController userControlador=loader.getController();
        userControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

}