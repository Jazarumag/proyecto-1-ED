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
    private int precio, kilometraje, peso;
    private String marca, modelo, ano;
    private String motor;
    private Transmision transmision; //Manual o Automatico
    private String ubicacion;
    private Historial historial;
    // Usuario
    private String userID;
    // Foto
    private String foto = "/com/espol/proyecto/files/";

    public Vehiculo(String placa, int precio, int kilometraje,
            int peso, String marca, String modelo, String ano,
            String motor, Transmision transmision, String ubicacion, String userID) {
        this.placa = placa;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.peso = peso;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.motor = motor;
        this.transmision = transmision;
        this.ubicacion = ubicacion;
        this.userID = userID;
    }
    
    public String getPlaca(){
        return placa;
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
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public String getAno() {
        return ano;
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
        if(this.placa.equals(other.placa))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", precio=" + precio + ", kilometraje=" + kilometraje + ", peso=" + peso + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", motor=" + motor + ", transmision=" + transmision + ", ubicacion=" + ubicacion + ", historial=" + historial + ", userID=" + userID + ", foto=" + foto + '}';
    }
    
}
