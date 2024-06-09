/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

import java.io.Serializable;

/**
 *
 * @author joshz
 */
public class Historial implements Serializable{
    private int nAccidentes;
    private boolean mantenimientoAlDia;
    
    public Historial(){}
    
    public Historial(int n, boolean m){
        this.nAccidentes=n;
        this.mantenimientoAlDia=m;
    }
    public int getnAccidentes() {
        return nAccidentes;
    }

    public boolean isMantenimientoAlDia() {
        return mantenimientoAlDia;
    }
    
    @Override
    public String toString(){
        return "(Accidentes: "+nAccidentes+" | Mantenimiento al dia: "+this.mantenimientoAlDia+")";
    }
}
