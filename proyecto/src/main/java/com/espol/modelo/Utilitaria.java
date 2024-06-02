/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

import com.espol.estructuras.ArrayListZ;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author luisa
 */
public class Utilitaria {
    public static int generarID(String nomfile){
        int id=1;
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomfile))){
            id=((ArrayListZ<Object>)in.readObject()).size()+1;
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(ClassNotFoundException b){
            System.out.println(b.getMessage());
        }
        catch(Exception d){
            System.out.println(d.getMessage());
        }
        return id;
    }
    
    public static User obtenerUsuario(String correo, String clave){   
        ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
        for(User user:usuarios){
            if(correo.equals(user.getCorreo()) && clave.equals(user.getClave()))
                return user;
        }
        return null;
    }
    
    public static boolean correoDisponible(String correo){
        ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
        for(User user:usuarios){
            if(user.getCorreo().equals(correo))
                return false;
        }
        return true;
    }
}
