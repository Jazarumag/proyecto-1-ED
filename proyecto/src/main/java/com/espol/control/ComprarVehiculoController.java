package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.estructuras.CircleLinkedListZ;
import com.espol.modelo.User;
import com.espol.modelo.Utilitaria;
import com.espol.modelo.Vehiculo;
import java.io.IOException;
import java.util.Collections;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private TextField marcamodeloano;
    @FXML
    private TextField Motor;
    @FXML
    private TextField transmision;
    @FXML
    private TextField peso;
    @FXML
    private TextField kilo;
    @FXML
    private TextField ubi;
    @FXML
    private TextField precio;
    @FXML
    private TextField historial;
    @FXML
    private Button Volver;
    @FXML
    private Button Comprar;
    @FXML
    private Button IZQfotos;
    @FXML
    private Button DERfotos;
    @FXML
    private Button botonOrdenarPrecio;
    @FXML
    private Button botonOrdenarPorKm;
    @FXML
    private Label numVehisLabel;
    @FXML
    private ImageView fotocarro;
    
    private ArrayListZ<Vehiculo> vehiculos;
    private CircleLinkedListZ<Vehiculo> carros;
    private User usuario;
    private Vehiculo vehi;
    private int index;
    private int indicefotos;
    
    public void setTexto(int num){
        numVehisLabel.setText("Se han encontrado: "+ num +" vehículo(s) acorde a sus parámetros");
    }
    
    public void setVehiculos(ArrayListZ<Vehiculo> v){
        this.vehiculos=v;
        carros=new CircleLinkedListZ<>();
        carros.addAll(v);
        
        mostrarInformacionVehiculo(carros.get(0));
    }
    
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
        menuController.setCarros(Vehiculo.readListFileSer("vehiculos.ser"));
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
    private String siono(boolean a){
        if (a) return "Al día";
        return "No al día";
    }
    private void mostrarInformacionVehiculo(Vehiculo vehiculo) {
        vehi=vehiculo;
        marcamodeloano.setText(vehiculo.getMarca() + " " + vehiculo.getModelo() + " " + vehiculo.getAno());
        Motor.setText(vehiculo.getMotor());
        transmision.setText(String.valueOf(vehiculo.getTransmision()));
        peso.setText(String.valueOf(vehiculo.getPeso()));
        kilo.setText(String.valueOf(vehiculo.getKilometraje()));
        ubi.setText(vehiculo.getUbicacion());
        precio.setText(String.valueOf(vehiculo.getPrecio()));
        historial.setText(siono(vehiculo.getHistorial().isMantenimientoAlDia())+" / "+String.valueOf(vehiculo.getHistorial().getnAccidentes())+" accidente(s)");
        indicefotos = 0;
        CarruselFotos();
        seteditar(false);
    }
    private void seteditar(boolean a){
        marcamodeloano.setEditable(a);
        Motor.setEditable(a);
        transmision.setEditable(a);
        kilo.setEditable(a);
        ubi.setEditable(a);
        peso.setEditable(a);
        precio.setEditable(a);
        historial.setEditable(a);
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
        CircleLinkedListZ<Vehiculo> carrusel = carros;
        Vehiculo carrito = carrusel.get(index);
        mostrarInformacionVehiculo(carrito);
        seteditar(false);
    }
    @FXML
    private void moverDerFotos(){
        indicefotos++;
        CarruselFotos();
    }
    @FXML
    private void moverIzqFotos(){
        indicefotos--;
        CarruselFotos();
    }
    private void CarruselFotos(){
        Vehiculo carrito = carros.get(index);
        ArrayListZ<String> fotosdelcarro = carrito.getFotos();
        CircleLinkedListZ<String> fotosDelcarro = new CircleLinkedListZ<>();
        fotosDelcarro.addAll(fotosdelcarro);
        fotosDelcarro.add(carrito.getFoto());
        String fotoactual = fotosDelcarro.get(indicefotos);
        try {
            Image imagen = new Image("file:"+fotoactual);
            fotocarro.setImage(imagen);
        } catch (IllegalArgumentException e) {
            System.err.println("URL de imagen no válida o recurso no encontrado: " + fotoactual);
            e.printStackTrace();
        }
    }
    @FXML
    private void ofertar(ActionEvent event) throws IOException{
        if(!vehi.getUserId().equals(usuario.getID())){
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere ofertar por "+vehi+" ?");
            if(alerta.showAndWait().get()==ButtonType.OK){
                if(vehi!=null){
                    Alert a=new Alert(Alert.AlertType.INFORMATION,"Se ha enviado su oferta, notificando al comprador... (espere un momento)");
                    a.show();
                    notificarVendedor();
                    notificarComprador();
                    a.close();
                    Alert c=new Alert(Alert.AlertType.INFORMATION,"Se ha notificado al comprador");
                    c.show();
                    cambiarPantallaUsua(event);
                }
                else{
                    Alert a=new Alert(Alert.AlertType.ERROR,"No ha seleccionado vehículo");
                    a.show();
                }
            }else{
                Alert a=new Alert(Alert.AlertType.ERROR,"No puede ofertar por su propio vehículo");
                a.show();
            }
        }
    }
    
    private boolean notificarVendedor(){
        String asunto="OFERTA ENTRANTE";
        String cuerpo="HA RECIBIDO UNA OFERTA POR SU "+vehi.getMarca()+" "+vehi.getModelo()+" CON PLACA "+vehi.getPlaca()+" DE PARTE DEL USUARIO "+usuario.getCorreo();
        try {
            Utilitaria.enviarConGMail(Utilitaria.obtenerUsuarioPorID(vehi.getUserId()).getCorreo(), asunto, cuerpo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", e);
        }
        return true;
    }
    
    private boolean notificarComprador(){
        String asunto="OFERTA ENVIADA";
        String cuerpo="SU OFERTA POR "+vehi.getMarca()+" "+vehi.getModelo()+" HA SIDO NOTIFICADA A "+Utilitaria.obtenerUsuarioPorID(vehi.getUserId()).getCorreo();
        try {
            Utilitaria.enviarConGMail(usuario.getCorreo(), asunto, cuerpo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", e);
        }
        return true;
    }

    @FXML
    private void ordenarPrecio(){
        Collections.sort(vehiculos, (v1, v2) -> Integer.compare(v1.getPrecio(), v2.getPrecio()));
        setVehiculos(vehiculos);
    }
    
    @FXML
    private void ordenarKm(){
        Collections.sort(vehiculos, (v1, v2) -> Integer.compare(v1.getKilometraje(), v2.getKilometraje()));
        setVehiculos(vehiculos);
    }
}