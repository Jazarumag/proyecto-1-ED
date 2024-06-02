/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;
/**
 *
 * @author joshz
 */
public class Vehiculo {
    private int precio, kilometraje, peso;
    private String marca, modelo, ano;
    private String motor;
    private String transmision; //Manual o Automatico, crear Enum
    private String ubicacion;
    private Historial historial;
    // Usuario
    private String userID;
    public Vehiculo(int precio, int kilometraje, String ubicacion) {
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.ubicacion = ubicacion;
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
    public String getTransmision() {
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
}
