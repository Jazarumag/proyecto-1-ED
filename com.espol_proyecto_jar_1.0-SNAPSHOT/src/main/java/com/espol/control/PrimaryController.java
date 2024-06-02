package com.espol.control;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {
    
    @FXML
    private Button botonLoggear;
    @FXML
    private PasswordField clavePasswordField;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private Button botonRegistrar;
    
    private void cambiarMenuUsuario(ActionEvent event, Parent root) throws IOException{
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    @FXML
    private void loggearse(ActionEvent event) throws IOException{
        String correo=usuarioTextField.getText();
        String clave=clavePasswordField.getText();
        User user=Utilitaria.obtenerUsuario(correo, clave);
        Alert alerta;
        if(user!=null){
            alerta=new Alert(Alert.AlertType.INFORMATION,"Usuario correcto");
            alerta.show();

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/UserMenu.fxml"));
            Parent root = (Parent) loader.load();
            UserMenuController menuControlador=loader.getController();
            menuControlador.mostrarUsuario(user.toString());
            menuControlador.setUsuario(user);
            
            cambiarMenuUsuario(event, root);
        }
        else{
            alerta=new Alert(Alert.AlertType.ERROR,"Usuario incorrecto");
            alerta.show();
        }
    }
    
    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/RegistrarUser.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
}
