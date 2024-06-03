/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class VenderVehiculoController implements Initializable {
    
    private User usuario;
    
    @FXML
    private TextField precio;
    
    @FXML
    private TextField km;
    
    @FXML
    private TextField peso;
    
    @FXML
    private TextField marca;
    
    @FXML
    private TextField modelo;
    
    @FXML
    private TextField anio;
    
    @FXML
    private TextField motor;
    
    @FXML
    private TextField transmision;
    
    @FXML
    private TextField ubicacion;
    
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void Vender(ActionEvent event) throws IOException{
        int precio=Integer.parseInt(this.precio.getText());
        int km=Integer.parseInt(this.km.getText());
        int peso=Integer.parseInt(this.peso.getText());
        String marca=this.marca.getText();
        String modelo=this.modelo.getText();
        String anio=this.anio.getText();
        String motor=this.motor.getText();
        String transmision=this.transmision.getText();
        String ubicacion=this.ubicacion.getText();
        
        Vehiculo carro= new Vehiculo(precio, km, ubicacion);
        
    }
    
    @FXML
    private void Atras(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaLogin(event);
        }
    }
    private void cambiarPantallaLogin(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/primary.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
}
