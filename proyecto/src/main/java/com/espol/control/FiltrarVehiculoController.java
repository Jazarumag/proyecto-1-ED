/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.control;

import com.espol.estructuras.ArrayListZ;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class FiltrarVehiculoController implements Initializable{

    @FXML
    private TextField inirecorr;
    @FXML
    private TextField finreco;
    @FXML
    private TextField inipre;
    @FXML
    private TextField finpre;
    
    //private ChoiceBox<TipoVehiculo> tipoVehiCbx;
    @FXML
    private TextField inianio;
    @FXML
    private TextField finanio;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonBuscar;
    @FXML
    private CheckBox porRecorr;
    @FXML
    private CheckBox porAnio;
    @FXML
    private CheckBox porPrec;
    @FXML
    private CheckBox porTipo;
    
    private User usuario;
    //private TipoVehiculo tv;
    private ArrayListZ<Vehiculo> vehiculos;
    
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
   
//    private void cambiarVehisBuscados(ActionEvent event, ArrayListZ<Vehiculo> v) throws IOException{
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/VehiculosBuscados.fxml"));
//        Parent root = (Parent) loader.load();
//        VehiculosBuscadosController menuController=loader.getController();
//        menuController.setUsuario(usuario);
//        menuController.setVehiculos(v);
//        menuController.setTexto(v.size());
//        menuController.setVehisTot(vehiculos);
//        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene escena=new Scene(root);
//        stage.setScene(escena);
//        stage.show();
//    }
    
    @FXML
    private void Atras(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaUsua(event);
        }
    }

    @FXML
    private void buscar(ActionEvent event) throws IOException{
        ArrayListZ<Vehiculo> vehiculosFiltro=new ArrayListZ<>();
        for (Vehiculo vehiculo : vehiculos) {
            vehiculosFiltro.add(vehiculo);
        }
        
        int numFiltros=0;
        //boolean tipo=false;
        boolean recor=false;
        boolean anio=false;
        boolean prec=false;
//        if(porTipo.isSelected()){
//            numFiltros++;
//            tipo=true;
//        }
        if(porRecorr.isSelected()){
            numFiltros++;
            recor=true;
        }
        if(porAnio.isSelected()){
            numFiltros++;
            anio=true;
        }
        if(porPrec.isSelected()){
            numFiltros++;
            prec=true;
        }
        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION,"Confirme la búsqueda de vehículos por: "+numFiltros+" parámetro(s)");
        if(alerta.showAndWait().get()==ButtonType.OK){
            try{
//                if(tipo)
//                    vehiculosFiltro=Utilitaria.filtrarVehiculos(vehiculosFiltro, "tipo", String.valueOf(tv));
                if(recor){
                    double inicio=Double.parseDouble(inirecorr.getText());
                    double fin=Double.parseDouble(finreco.getText());
                    if(fin<inicio){
                        double temp=fin;
                        inicio=fin;
                        fin=temp;
                    }
                    vehiculosFiltro=Utilitaria.filtrarVehiculos(vehiculosFiltro, "recorrido", inicio+"-"+fin);
                }
                if(anio){
                    int inicio=Integer.parseInt(inianio.getText());
                    int fin=Integer.parseInt(finanio.getText());
                    if(fin<inicio){
                        int temp=fin;
                        inicio=fin;
                        fin=temp;
                    }
                    vehiculosFiltro=Utilitaria.filtrarVehiculos(vehiculosFiltro, "anio", inicio+"-"+fin);
                }
                if(prec){
                    double inicio=Double.parseDouble(inipre.getText());
                    double fin=Double.parseDouble(finpre.getText());
                    if(fin<inicio){
                        double temp=fin;
                        inicio=fin;
                        fin=temp;
                    }
                    vehiculosFiltro=Utilitaria.filtrarVehiculos(vehiculosFiltro, "precio", inicio+"-"+fin);
                }
                System.out.println(vehiculos);
                System.out.println(vehiculosFiltro);
                //cambiarVehisBuscados(event,vehiculosFiltro);
            }
            catch(NumberFormatException e){
                Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese datos válidos");
                a.show();
            }
        }
               
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos=Vehiculo.readListFileSer("vehiculos.ser");
//        TipoVehiculo[] t={TipoVehiculo.MOTO,TipoVehiculo.CARRO,TipoVehiculo.CAMIONETA};
//        tipoVehiCbx.getItems().addAll(t);
//        tipoVehiCbx.setOnAction(this::setTipo);
//        tipoVehiCbx.setValue(TipoVehiculo.CARRO);
    }
    
//    public void setTipo(ActionEvent e){
//        tv=tipoVehiCbx.getValue();
//    }
   
}
