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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshz
 */
public class ComprarVehiculoController implements Initializable{
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
    private ImageView fotocarro;
    @FXML
    private Button Comprar;
    @FXML
    private Button IZQ;
    @FXML
    private Button DER;
    @FXML
    private Button IZQfotos;
    @FXML
    private Button DERfotos;
    
    private ArrayListZ<Vehiculo> vehiculos;
    private CircleLinkedListZ<Vehiculo> carros;
    private User usuario;
    private Vehiculo vehi;
    private ArrayListZ<Vehiculo> vehisTot;
    private int index;
    private int indicefotos;
    
    
    @FXML
    private Button Volver;
    @FXML
    private Button VendeVehiculo;
    
//    public void setTexto(int num){
//        numVehisLabel.setText("Se han encontrado: "+ num +" vehículo(s) acorde a sus parámetros");
//    }
    
    public void setVehiculos(ArrayListZ<Vehiculo> v){
        this.vehiculos=v;
        carros.addAll(v);
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
        marcamodeloano.setText(vehiculo.getMarca() + " " + vehiculo.getModelo() + " " + vehiculo.getAno());
        Motor.setText(vehiculo.getMotor());
        transmision.setText(String.valueOf(vehiculo.getTransmision()));
        peso.setText(String.valueOf(vehiculo.getPeso()));
        kilo.setText(String.valueOf(vehiculo.getKilometraje()));
        ubi.setText(vehiculo.getUbicacion());
        precio.setText(String.valueOf(vehiculo.getPrecio()));
        historial.setText(siono(vehiculo.getHistorial().isMantenimientoAlDia())+"/"+String.valueOf(vehiculo.getHistorial().getnAccidentes()));
        indicefotos = 0;
        CarruselFotos();
        seteditar(false);
        kilo.setStyle("-fx-background-color: lightgray;");
        ubi.setStyle("-fx-background-color: lightgray;");
        precio.setStyle("-fx-background-color: lightgray;");
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
        kilo.setStyle("-fx-background-color: lightgray;");
        ubi.setStyle("-fx-background-color: lightgray;");
        precio.setStyle("-fx-background-color: lightgray;");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}