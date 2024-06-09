/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuController implements Initializable{
    
    @FXML
    private Button IZQ;
    @FXML
    private Button DER;
    @FXML
    private Label saludoLabel;
    @FXML
    private Button botonLogout;
    @FXML
    private Button regisVehi;
    @FXML
    private Button comprVehi;
    @FXML
    private Button editarVehi;
    @FXML
    private Button guardar;

    private User usuario;
    private int index=-1;
    private ArrayListZ<Vehiculo> carros;
    @FXML
    private Button botonPerfil;
    @FXML
    private Label descripcion;
    @FXML
    private ImageView foto;
    @FXML
    private ImageView fotoANTERIOR;
    @FXML
    private ImageView fotoSIGUIENTE;
    
    public void setCarros(ArrayListZ<Vehiculo> a){
        this.carros=a;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (carros!=null) if(!carros.isEmpty()) moverDer();
    }
    
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
    private void cambiarComprarVehi(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/espol/proyecto/FiltrarVehiculo.fxml"));
        Parent root = (Parent) loader.load();
        FiltrarVehiculoController vehiculoControlador=loader.getController();
        vehiculoControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    @FXML
    private void cambiarEditarVehi(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/proyecto/EditarVehiculo.fxml"));
        Parent root = (Parent) loader.load();
        EditarVehiculoController vehiculoControlador = loader.getController();
        vehiculoControlador.mostrarUsuarioo(usuario.getNombres());
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
    
    @FXML
    private void moverDer(){
        if (carros!=null) if(!carros.isEmpty()){
        index++;
        CarruselCarros();} else{ System.out.println("No hay Carros");}
    }
    @FXML
    private void moverIzq(){
        if (carros!=null) if(!carros.isEmpty()){
        index--;
        CarruselCarros();} else{ System.out.println("No hay Carros");}
    }
    private void CarruselCarros(){
        CircleLinkedListZ<Vehiculo> carrusel = new CircleLinkedListZ<>();
        ArrayListZ<Vehiculo> a = Vehiculo.readListFileSer("vehiculos.ser");
        carrusel.addAll(a);
        Vehiculo carrito = carrusel.get(index);
        Vehiculo carritoANT = carrusel.get(index-1);
        Vehiculo carritoSIG = carrusel.get(index+1);
        System.out.println(carrito);
        try {
            Image imagen = new Image("file:"+carrito.getFoto());
            Image imagenANT = new Image("file:"+carritoANT.getFoto());
            Image imagenSIG = new Image("file:"+carritoSIG.getFoto());
            foto.setImage(imagen);
            fotoANTERIOR.setImage(imagenANT);
            fotoSIGUIENTE.setImage(imagenSIG);
        } catch (IllegalArgumentException e) {
            System.err.println("URL de imagen no válida o recurso no encontrado: " + carrito.getFoto());
            e.printStackTrace();
        }
        descripcion.setText("$ "+carrito.getPrecio()+" "+carrito.getMarca()+" "+carrito.getModelo());
    }

}