/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.modelo;

import com.espol.estructuras.ArrayListZ;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author luisa
 */
public class Utilitaria {
    public static String generarID(String nomfile){
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
        return String.valueOf(id);
    }
    
    public static User obtenerUsuario(String correo, String clave){   
        ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
        for(User user:usuarios){
            if(correo.equals(user.getCorreo()) && clave.equals(user.getClave()))
                return user;
        }
        return null;
    }
    
    public static User obtenerUsuarioPorID(String id){   
        ArrayListZ<User> usuarios=User.readListFileSer("usuarios.ser");
        for(User user:usuarios){
            if(id.equals(user.getID()))
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
    
    public static Vehiculo obtenerVehiculoPorPlaca(String placa){
         ArrayListZ<Vehiculo> vehis=Vehiculo.readListFileSer("vehiculos.ser");
         for(Vehiculo v:vehis){
             if(v.getPlaca().equals(placa))
                 return v;
         }
         return null;
    }
    
    public static ArrayListZ<Vehiculo> filtrarVehiculos(ArrayListZ<Vehiculo> vehiculos, String parametro, String datos){
        ArrayListZ<Vehiculo> retorno=new ArrayListZ<>();
        switch(parametro){
            case "tipo":
                for(Vehiculo vehiculo:vehiculos){
                    if(String.valueOf(vehiculo.getTipoAuto()).equals(datos))
                        retorno.add(vehiculo);
                }
                break;
            case "marca":
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getMarca().equals(datos))
                        retorno.add(vehiculo);
                }
                break;
            case "modelo":
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getModelo().equals(datos))
                        retorno.add(vehiculo);
                }
                break;
            case "recorrido":
                double inicioRecorrido=Double.parseDouble(datos.split("-")[0]);
                double finRecorrido=Double.parseDouble(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getKilometraje()>=inicioRecorrido && vehiculo.getKilometraje()<=finRecorrido)
                        retorno.add(vehiculo);
                }
                break;
                
            case "anio":
                int inicioAnio=Integer.parseInt(datos.split("-")[0]);
                int finAnio=Integer.parseInt(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(Integer.parseInt(vehiculo.getAno())>=inicioAnio && Integer.parseInt(vehiculo.getAno())<=finAnio)
                        retorno.add(vehiculo);
                }
                break;
                
            case "precio":
                double inicioPrecio=Double.parseDouble(datos.split("-")[0]);
                double finPrecio=Double.parseDouble(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getPrecio()>=inicioPrecio && vehiculo.getPrecio()<=finPrecio)
                        retorno.add(vehiculo);
                }
                break;
                
        }
        return retorno;
    }
    
    public static HashSet<String> obtenerMarcasPorTipo(ArrayListZ<Vehiculo> vehiculos, TipoAuto ta){
        HashSet<String> retorno=new HashSet<>();
        for(Vehiculo vehiculo:vehiculos){
            if(vehiculo.getTipoAuto().equals(ta))
                retorno.add(vehiculo.getMarca());
        }
        return retorno;
    }
    
    public static HashSet<String> obtenerModelosPorMarca(ArrayListZ<Vehiculo> vehiculos, String marca){
        HashSet<String> retorno=new HashSet<>();
        for(Vehiculo vehiculo:vehiculos){
            if(vehiculo.getMarca().equals(marca))
                retorno.add(vehiculo.getModelo());
        }
        return retorno;
    }
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        Properties configProps = new Properties();
        try {
            configProps.load(new FileInputStream("configuracionCorreo.properties"));
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de configuración: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String remitente = configProps.getProperty("remitente");
        String claveemail = configProps.getProperty("claveemail");
        String smtpHost = configProps.getProperty("smtp.host");
        String smtpPort = configProps.getProperty("smtp.port");

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.trust", smtpHost);
        props.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Correo enviado exitosamente");
        } catch (MessagingException me) {
            System.err.println("Error al enviar el correo: " + me.getMessage());
            me.printStackTrace();
        }
    }
}
