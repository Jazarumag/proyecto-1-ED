/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;
/**
 *
 * @author joshz
 */
public class User {
    private String mail;
    private String contrasena;
    private String nombre;
    ArrayListZ<Vehiculo> Vregistrados;
    
    public User(String m,String c,String n){
        this.mail=m;
        this.contrasena=c;
        this.nombre=n;
    }

    public String getNombre() {
        return nombre;
    }
    public boolean comprobarCon(String con){
        return con.equals(contrasena);   
    }
    
    public boolean comprobarUser(String m){
        return m.equals(mail);
    }
    
    
}
