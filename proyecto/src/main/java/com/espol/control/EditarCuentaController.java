/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.modelo.User;
import com.espol.modelo.Vehiculo;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class EditarCuentaController{

    @FXML
    private Button botonMenu;
    @FXML
    private TextField newpasstxt;
    @FXML
    private TextField oldpasstxt;
    @FXML
    private Button contranuevaBoton;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField orgtxt;
    @FXML
    private TextField mailtxt;

    private User usuario;
    private ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
    /**
     * Initializes the controller class.
     */
    public void setUsuario(User user) {
        usuario=user;
        nomtxt.setText(usuario.getNombres()+" "+usuario.getApellidos());
        nomtxt.setStyle("-fx-background-color: lightgray;");
        mailtxt.setText(usuario.getCorreo());
        mailtxt.setStyle("-fx-background-color: lightgray;");
    }    

    @FXML
    private void cambiarMenu(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/UserMenu.fxml"));
        Parent root = (Parent) loader.load();
        UserMenuController menuController=loader.getController();
        menuController.setUsuario(usuario);
        menuController.mostrarUsuario(usuario.toString());
        menuController.setCarros(Vehiculo.readListFileSer("vehiculos.ser"));
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

    @FXML
    private void cambiarPass(ActionEvent event) {
        if(!newpasstxt.getText().isEmpty()){
            String contra=oldpasstxt.getText();
            if(usuario.getClave().equals(contra)){
                String nuevaContra=newpasstxt.getText();
                usuario.setClave(nuevaContra);
                int indice=usuarios.indexOf(usuario);
                if (indice != -1)
                    usuarios.set(indice, usuario);
                User.saveListFileSer("usuarios.ser", usuarios);
                Alert a=new Alert(Alert.AlertType.INFORMATION,"Su contraseña ha sido cambiada con éxito");
                a.show();
                newpasstxt.clear();
                oldpasstxt.clear();
            }
            else{
                Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese su antigua contraseña para confirmar");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese una nueva contraseña");
            a.show();
        }
        
    }
    
}

