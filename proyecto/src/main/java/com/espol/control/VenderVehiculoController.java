/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.modelo.TipoAuto;
import com.espol.modelo.Transmision;
import com.espol.modelo.User;
import com.espol.modelo.Utilitaria;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class VenderVehiculoController implements Initializable{

    @FXML
    private Button botontr;
    @FXML
    private ComboBox<TipoAuto> cbTipoAuto;
    @FXML
    private ComboBox<Transmision> cbTransmision;
    @FXML
    private GridPane cj;
    @FXML
    private Button botonSubirImg;
    @FXML
    private Button botonRegistrar;
    @FXML
    private ImageView imgVehi;
    @FXML
    private TextField tfPrecio;
    @FXML
    private TextField tfKm;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfMotor;
    @FXML
    private TextField tfUbicacion;
    @FXML
    private TextField tfPlaca;
    
    private User usuario;
    private ArrayListZ<User> usuarios; //esto se usa para luego sacar el id de usuario
    
    private File imagenElegida;
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }
 
    @Override
    public void initialize(URL url,ResourceBundle rb){
        TipoAuto[] tipo={TipoAuto.CAMIONETA,TipoAuto.CONVERTIBLE,TipoAuto.DEPORTIVO,TipoAuto.ELECTRICO,TipoAuto.FAMILIAR,TipoAuto.HATCHBACK,TipoAuto.HIBRIDO,TipoAuto.LIMOSINA,TipoAuto.SEDAN,TipoAuto.SUV,TipoAuto.TODOTERRENO,TipoAuto.VAN};
        cbTipoAuto.getItems().addAll(tipo);
                
        Transmision[] t={Transmision.MANUAL,Transmision.AUTOMATICO};
        cbTransmision.getItems().addAll(t);
        usuarios=User.readListFileSer("usuarios.ser");
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
    private void Atras(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaUsua(event);
        }
    }

    @FXML
    private void registrar(ActionEvent event) throws IOException {
        String placa = tfPlaca.getText();
        if (Utilitaria.obtenerVehiculoPorPlaca(placa) == null) { 
            try {
                int precio = Integer.parseInt(tfPrecio.getText());
                int km = Integer.parseInt(tfKm.getText());
                int peso = Integer.parseInt(tfPeso.getText());
                String marca = tfMarca.getText();
                String modelo = tfModelo.getText();
                String anio = tfAnio.getText();
                String motor = tfMotor.getText();
                Transmision transmision = cbTransmision.getValue();
                TipoAuto tipoAuto=cbTipoAuto.getValue();
                String ubicacion = tfUbicacion.getText();

                Vehiculo vehiculo = new Vehiculo(placa, tipoAuto, precio, km, peso, marca, modelo, anio, motor, transmision, ubicacion, usuario.getID());
                System.out.println(vehiculo);

                if (imagenElegida != null) {
                    try {
                        Path destPath;
                        String relativePath;

                        if (getClass().getProtectionDomain().getCodeSource().getLocation().getPath().endsWith(".jar")) {
                            String jarDir = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
                            destPath = Paths.get(jarDir, "imgs", imagenElegida.getName());
                            relativePath = "imgs/" + imagenElegida.getName();
                        } else {
                            destPath = Paths.get("src", "main", "resources", "imgs", imagenElegida.getName());
                            relativePath = "src/main/resources/imgs/" + imagenElegida.getName();
                        }

                        Files.createDirectories(destPath.getParent());
                        Files.copy(imagenElegida.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                        ArrayListZ<Vehiculo> vehis = Vehiculo.readListFileSer("vehiculos.ser");
                        vehiculo.setFoto(relativePath);

                        vehis.add(vehiculo);
                        Vehiculo.saveListFileSer("vehiculos.ser", vehis);
                        usuario.getVehiculos().add(vehiculo);
                        int indice = usuarios.indexOf(usuario);
                        if (indice != -1) {
                            usuarios.set(indice, usuario);
                        }
                        User.saveListFileSer("usuarios.ser", usuarios);

                        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Vehículo registrado correctamente");
                        alerta.show();
                        cambiarPantallaUsua(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Alert a = new Alert(Alert.AlertType.ERROR, "Error al subir la imagen: " + e.getMessage());
                        a.show();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No ha seleccionado una imagen");
                    a.show();
                }

            } catch (NumberFormatException errorNum) {
                Alert alerta = new Alert(Alert.AlertType.ERROR, "No ha ingresado datos válidos: " + errorNum.getMessage());
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "La placa del vehículo ya está registrada");
            alerta.show();
        }
        System.out.println(Vehiculo.readListFileSer("vehiculos.ser"));
    }
    
    @FXML
    public void subirImagen() { //esto es para subir la imagen asi que no toques nada
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*.png", "*.jpeg", "*.jpg")
        );

        imagenElegida = fileChooser.showOpenDialog(botonRegistrar.getScene().getWindow());
        if (imagenElegida != null) {
            Image imagen = new Image(imagenElegida.toURI().toString());
            imgVehi.setImage(imagen);
        }
    }
}
