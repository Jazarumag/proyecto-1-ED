/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
import com.espol.modelo.TipoVehiculo;
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
    private ComboBox<TipoVehiculo> comboBoxt;
    @FXML
    private GridPane cj;
    @FXML
    private Button botonSubirImg;
    @FXML
    private Button botonRegistrar;
    @FXML
    private ImageView imgVehi;
    
    private User usuario;
    private ArrayListZ<String> vehiculoRqs=new ArrayListZ<>(); //esta kk era para el combobox de vehiculos asi que es inutil
    private ArrayListZ<User> usuarios; //esto se usa para luego sacar el id de usuario
    
    private File imagenElegida;
    
    public void setUsuario(User usuario){
        this.usuario=usuario;
    }
    
    //esto era porque el combobox lo hice dinamico segun el tipo de vehiculo pero ya no es necesario
    @FXML
    private void comboBoxEvent(ActionEvent e){
        TipoVehiculo seleccionado = comboBoxt.getValue();
        vehiculoRqs=new ArrayListZ<>();
        vehiculoRqs.add("Placa");
        vehiculoRqs.add("Marca");
        vehiculoRqs.add("Modelo");
        vehiculoRqs.add("Tipo Motor");
        vehiculoRqs.add("Año");
        vehiculoRqs.add("Recorrido");
        vehiculoRqs.add("Color");
        vehiculoRqs.add("Tipo de combustible");
        vehiculoRqs.add("Precio");
        switch(seleccionado) {
            case MOTO:
                cj.getChildren().clear();       
                imagenElegida=null;
                imgVehi.setImage(null);
                break;
            case CARRO:
                vehiculoRqs.add("Vidrios");
                vehiculoRqs.add("Transmisión");
                cj.getChildren().clear();   
                imagenElegida=null;
                imgVehi.setImage(null);
                break;
            case CAMIONETA:
                vehiculoRqs.add("Vidrios");
                vehiculoRqs.add("Transmisión");
                vehiculoRqs.add("Tracción");    
                cj.getChildren().clear();
                imagenElegida=null;
                imgVehi.setImage(null);
                break;
        }
        
        for(int j=0;j<vehiculoRqs.size();j++){
            Label label = new Label(vehiculoRqs.get(j)+" :");
            label.setTextFill(Color.BLACK);
            label.setFont(Font.font("System", FontWeight.BOLD, 12));

            TextField textField = new TextField();
            textField.setMaxWidth(200);

            textField.setStyle("-fx-background-color: #DAC0A3; -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-border-color: transparent; -fx-border-width: 0;");

            cj.add(label, 0, j);
            cj.add(textField, 1, j);

            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            GridPane.setHalignment(textField, HPos.LEFT);
            GridPane.setValignment(textField, VPos.CENTER);
        }

    }
    
    @Override
    public void initialize(URL url,ResourceBundle rb){
        TipoVehiculo[] t={TipoVehiculo.MOTO,TipoVehiculo.CARRO,TipoVehiculo.CAMIONETA};
        comboBoxt.getItems().addAll(t);
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
    private void registrar(ActionEvent event) throws IOException{  //aqui debes chambear pe
        if(vehiculoRqs.isEmpty()){
            Alert alerta=new Alert(Alert.AlertType.ERROR,"No ha seleccionado tipo de Vehiculo");
            alerta.show();
        }
        else{
            int indiceCol = 1;
            ArrayListZ<String> cosasVehiculo = new ArrayListZ<>();
            for (Node nodo : cj.getChildren()) {
                Integer columna = GridPane.getColumnIndex(nodo);

                if (columna != null && columna == indiceCol) {
                    TextField coso = (TextField) nodo;
                    cosasVehiculo.add(coso.getText());
                }
            }
            if(Utilitaria.obtenerVehiculoPorPlaca(cosasVehiculo.get(0))==null){ //comprueba si la placa ya esta registrada
                try{
                    /*
                    a ver esto es una fumada donde segun el tipo de vehiculo lo inicializaba con el constructor de la clase heredada que cree
                    pero ya no es necesario porque en vez de eso usaremos Enums asi que ahi mira como arreglas no se
                    */
                    
                    Vehiculo nuevo=null;
                    if(cosasVehiculo.size()==9){
                        //nuevo=new Vehiculo(usuario.getID(),cosasVehiculo.get(0),cosasVehiculo.get(1),cosasVehiculo.get(2),cosasVehiculo.get(3),Integer.parseInt(cosasVehiculo.get(4)),Double.parseDouble(cosasVehiculo.get(5)),cosasVehiculo.get(6),cosasVehiculo.get(7),Double.parseDouble(cosasVehiculo.get(8)));
                    }
                    else if(cosasVehiculo.size()==11){
                        //nuevo=new Carro(usuario.getID(),cosasVehiculo.get(0),cosasVehiculo.get(1),cosasVehiculo.get(2),cosasVehiculo.get(3),Integer.parseInt(cosasVehiculo.get(4)),Double.parseDouble(cosasVehiculo.get(5)),cosasVehiculo.get(6),cosasVehiculo.get(7),Double.parseDouble(cosasVehiculo.get(8)),Integer.parseInt(cosasVehiculo.get(9)),cosasVehiculo.get(10));
                    }
                    else{
                        //nuevo=new Camioneta(usuario.getID(),cosasVehiculo.get(0),cosasVehiculo.get(1),cosasVehiculo.get(2),cosasVehiculo.get(3),Integer.parseInt(cosasVehiculo.get(4)),Double.parseDouble(cosasVehiculo.get(5)),cosasVehiculo.get(6),cosasVehiculo.get(7),Double.parseDouble(cosasVehiculo.get(8)),Integer.parseInt(cosasVehiculo.get(9)),cosasVehiculo.get(10),cosasVehiculo.get(11));
                    }
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
                            nuevo.setFoto(relativePath);
                            
                            vehis.add(nuevo);
                            Vehiculo.saveListFileSer("vehiculos.ser", vehis);
                            usuario.getVehiculos().add(nuevo);
                            int indice = usuarios.indexOf(usuario);
                            if (indice != -1) {
                                usuarios.set(indice, usuario);
                            }
                            User.saveListFileSer("usuarios.ser", usuarios);

                            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Vehiculo se ha registrado correctamente");
                            alerta.show();
                            cambiarPantallaUsua(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "No ha seleccionado una imagen");
                        a.show();
                    }

                    
                }
                catch(NumberFormatException errorNum){
                    Alert alerta=new Alert(Alert.AlertType.ERROR,"No ha ingresado datos válidos");
                    alerta.show();
                }
            }
            else{
                Alert alerta=new Alert(Alert.AlertType.ERROR,"Placa del vehiculo ya ha sido registrada");
                alerta.show();
            }
            System.out.println(Vehiculo.readListFileSer("vehiculos.ser"));
        }
        
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
