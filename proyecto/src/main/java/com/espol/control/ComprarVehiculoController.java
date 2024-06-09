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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshz
 */
public class ComprarVehiculoController{

    private ArrayListZ<Vehiculo> vehiculos;
    private User usuario;
    private Vehiculo vehi;
    private ArrayListZ<Vehiculo> vehisTot;
    
    @FXML
    private Button Volver;
    @FXML
    private Button VendeVehiculo;
    
//    public void setTexto(int num){
//        numVehisLabel.setText("Se han encontrado: "+ num +" vehículo(s) acorde a sus parámetros");
//    }
    
    public void setVehiculos(ArrayListZ<Vehiculo> v){
        this.vehiculos=v;
    }
    
    public void setVehisTot(ArrayListZ<Vehiculo> v){
        this.vehisTot=v;
    }
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }

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
    
    @FXML
    private void regresar(ActionEvent event) throws IOException{
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere regresar al menú principal?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaUsua(event);
        }
    }

//    @FXML
//    private void ofertar(ActionEvent event) throws IOException{
//        if(!vehi.getVendedor().equals(usuario)){
//            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere ofertar por "+vehi+" ?");
//            if(alerta.showAndWait().get()==ButtonType.OK){
//                if(vehi!=null){
//                    try{
//                        double precioOferta=Double.parseDouble(ofertarTxt.getText());
//                        Oferta oferta=new Oferta(usuario,vehi,precioOferta);
//                        vehi.getOfertas().add(oferta);
//                        for(Vehiculo v:vehisTot){
//                            if(vehi.equals(v))
//                                v=vehi;
//                        }
//                        Vehiculo.saveListFileSer("vehiculos.ser", vehisTot);
//                        Alert a=new Alert(Alert.AlertType.INFORMATION,"Se ha puesto su oferta");
//                        a.show();
//                        cambiarPantallaUsua(event);
//                    }
//                    catch(NumberFormatException e){
//                        Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese una oferta válida");
//                        a.show();
//                    }
//                }
//                else{
//                    Alert a=new Alert(Alert.AlertType.ERROR,"No ha seleccionado vehículo");
//                    a.show();
//                }
//            }
//        }
//        else{
//            Alert a=new Alert(Alert.AlertType.ERROR,"No puede ofertar por su propio vehículo");
//            a.show();
//        }
//    }
}