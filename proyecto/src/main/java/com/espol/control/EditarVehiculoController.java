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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author joshz
 */
public class EditarVehiculoController implements Initializable{
    @FXML
    private Button Volver;
    @FXML
    private Button EditaVehiculo;
    @FXML
    private Label saludo;
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
    @FXML
    private Button btnEliminar;
    @FXML
    private Button IZQ;
    @FXML
    private Button DER;
    
    private User usuario;
    private CircleLinkedListZ<Vehiculo> carros;
    private int index=0;
    
    public void setUsuario(User u){
        this.usuario=u;
        ArrayListZ<Vehiculo> lcarros = Vehiculo.readListFileSer("vehiculos.ser");
        for (Vehiculo i : lcarros){
            if (i.getUserId().equals(u.getID())){
                carros.add(i);
            }
        }
        if (!carros.isEmpty()) {
            mostrarInformacionVehiculo(carros.get(0));
        }
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
        System.out.println(usuario);
        carros = new CircleLinkedListZ<>();
    }
    
    public void mostrarUsuarioo(String nombre){
        saludo.setText("Hola, "+nombre);
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
        seteditar(false);
        kilo.setStyle("-fx-background-color: lightgray;");
        ubi.setStyle("-fx-background-color: lightgray;");
        precio.setStyle("-fx-background-color: lightgray;");
    }
    private void seteditar(boolean a){
        kilo.setEditable(a);
        ubi.setEditable(a);
        precio.setEditable(a);
    }
    @FXML
    private void botoneditar(){
        kilo.setStyle("-fx-background-color: #A8DFF6;");
        ubi.setStyle("-fx-background-color: #A8DFF6;");
        precio.setStyle("-fx-background-color: #A8DFF6;");
        kilo.setStyle("-fx-text-fill: black;");
        ubi.setStyle("-fx-text-fill: black;");
        precio.setStyle("-fx-text-fill: black;");
        seteditar(true);
    }
    @FXML
    private void moverDer(){
        index++;
        CarruselCarros();
    }
    @FXML
    private void moverIzq(){
        index--;
        CarruselCarros();
    }
    private void CarruselCarros(){
        CircleLinkedListZ<Vehiculo> carrusel = new CircleLinkedListZ<>();
        ArrayListZ<Vehiculo> a = Vehiculo.readListFileSer("vehiculos.ser");
        carrusel.addAll(a);
        Vehiculo carrito = carrusel.get(index);
        mostrarInformacionVehiculo(carrito);
        seteditar(false);
        kilo.setStyle("-fx-background-color: lightgray;");
        ubi.setStyle("-fx-background-color: lightgray;");
        precio.setStyle("-fx-background-color: lightgray;");
    }
    @FXML
    private void guardarCambios(){
        Alert alertaLogout = new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere Guardar los cambios?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            seteditar(false);
            kilo.setStyle("-fx-background-color: lightgray;");
            ubi.setStyle("-fx-background-color: lightgray;");
            precio.setStyle("-fx-background-color: lightgray;");
            
            Vehiculo editado = carros.get(index);
        
            editado.setKilometraje(Integer.parseInt(kilo.getText()));
            editado.setUbicacion(ubi.getText());
            editado.setPrecio(Integer.parseInt(precio.getText()));

            ArrayListZ<Vehiculo> vehiculos = Vehiculo.readListFileSer("vehiculos.ser");

            vehiculos.replace(editado);

            Vehiculo.saveListFileSer("vehiculos.ser", vehiculos);

            Alert alertaExito = new Alert(Alert.AlertType.INFORMATION, "Cambios guardados exitosamente.");
            alertaExito.showAndWait();
        }
    }
    @FXML
    private void eliminarVehiculo(){
        Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION,"¿Está seguro de eliminar este vehículo?");
        if(alertaEliminar.showAndWait().get()==ButtonType.OK){
            Vehiculo eliminado = carros.get(index);

            ArrayListZ<Vehiculo> vehiculos = Vehiculo.readListFileSer("vehiculos.ser");

            vehiculos.remove(eliminado);

            Vehiculo.saveListFileSer("vehiculos.ser", vehiculos);

            carros.remove(eliminado);
            if (!carros.isEmpty()) {
                if (index >= carros.size()) {
                    index = 0;
                }
                mostrarInformacionVehiculo(carros.get(index));
            }
        }
    }
}
