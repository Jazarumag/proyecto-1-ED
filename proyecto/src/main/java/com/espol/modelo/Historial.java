/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

/**
 *
 * @author joshz
 */
public class Historial {
    private int nAccidentes;
    private boolean mantenimientoAlDia;
    
    public Historial(){}
    
    public Historial(int n, boolean m){
        this.nAccidentes=n;
        this.mantenimientoAlDia=m;
    }
    
    @Override
    public String toString(){
        return "(Accidentes: "+nAccidentes+" | Mantenimiento al dia: "+this.mantenimientoAlDia+")";
    }
}
