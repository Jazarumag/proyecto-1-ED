/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

import com.espol.estructuras.ArrayListZ;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author joshz
 */
public class Vehiculo implements Serializable{
    private String placa;
    private TipoAuto tipoAuto;
    private int precio, kilometraje, peso;
    private String marca, modelo, anio;
    private String motor;
    private Transmision transmision; //Manual o Automatico
    private String ubicacion;
    private Historial historial;
    // Usuario
    private String userID;
    // Fotos
    private String foto;
    private ArrayListZ<String> fotos;

    public Vehiculo(String placa, TipoAuto tipoAuto, int precio, int kilometraje,
            int peso, String marca, String modelo, String anio,
            String motor, Transmision transmision, String ubicacion, String userID) {
        this.placa = placa;
        this.tipoAuto=tipoAuto;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.peso = peso;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.motor = motor;
        this.historial=new Historial();
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.userID = userID;
        this.fotos=new ArrayListZ<>();
    }

    public String getUserId(){
        return this.userID;
    }
    
    public String getPlaca(){
        return placa;
    }
    
    public TipoAuto getTipoAuto(){
        return this.tipoAuto;
    }
    
    public void setPlaca(String placa){
        this.placa=placa;
    }
    
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getKilometraje() {
        return kilometraje;
    }
    public void setKilometraje(int kilometraje) {
        if (kilometraje>this.kilometraje) this.kilometraje = kilometraje;
    }
    public int getPeso() {
        return peso;
    }
    public void setHistorial(Historial h){
        this.historial=h;
    }
    
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getAno() {
        return anio;
    }
    public String getMotor() {
        return motor;
    }
    public Transmision getTransmision() {
        return transmision;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public Historial getHistorial() {
        return historial;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public ArrayListZ<String> getFotos(){
        return this.fotos;
    }
    
    public String getFoto(){
        return foto;
    }
    
    public void setFoto(String foto){
        this.foto=foto;
    }
    
    public static void saveListFileSer(String nomfile, ArrayListZ<Vehiculo> vehiculos){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(vehiculos);
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(Exception b){
            System.out.println(b.getMessage());
        }
    }
    
    public static ArrayListZ<Vehiculo> readListFileSer(String nomfile){
        ArrayListZ<Vehiculo> vehiculos=new ArrayListZ<>();
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomfile))){
            vehiculos=(ArrayListZ<Vehiculo>) in.readObject();
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(ClassNotFoundException b){
            System.out.println(b.getMessage());
        }
        catch(Exception c){
            System.out.println(c.getMessage());
        }
        return vehiculos;
    }
 
    @Override
    public boolean equals(Object o){
        if(o==null || this.getClass()!=o.getClass())
            return false;
        if(this==o)
            return true;
        Vehiculo other=(Vehiculo) o;
        return this.placa.equals(other.placa);
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", precio=" + precio + ", kilometraje=" + kilometraje + ", peso=" + peso + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + anio + ", motor=" + motor + ", transmision=" + transmision + ", ubicacion=" + ubicacion + ", historial=" + historial + ", userID=" + userID + ", foto=" + foto + '}';
    }
    
}
