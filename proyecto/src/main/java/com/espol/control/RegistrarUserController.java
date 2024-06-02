/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.modelo.User;
import com.espol.modelo.Utilitaria;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class RegistrarUserController  {

    @FXML
    private Button registrar;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField correo;
    @FXML
    private TextField clave;
   
   private void cambiarPantallaLogin(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/primary.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
   
    @FXML
    private void Atras(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaLogin(event);
        }
    }
    
    @FXML
    private void Registrar(ActionEvent event) throws IOException{
        String nombre=this.nombre.getText();
        String apellido=this.apellido.getText();
        String correo=this.correo.getText();
        String clave=this.clave.getText();
        if(Utilitaria.correoDisponible(correo)){
            User nuevo=new User(nombre,apellido,correo,clave);
            ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
            usuarios.add(nuevo);
            User.saveListFileSer("usuarios.ser", usuarios);
            Alert alerta=new Alert(Alert.AlertType.INFORMATION,"Usuario se ha registrado correctamente");
            alerta.show();
            cambiarPantallaLogin(event);
        }
        else{
            Alert alerta=new Alert(Alert.AlertType.ERROR,"Usuario ya se encuentra registrado");
            alerta.show();
        }
    }

}