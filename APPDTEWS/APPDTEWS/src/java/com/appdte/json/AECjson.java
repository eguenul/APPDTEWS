/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.json;

import java.util.ArrayList;

/**
 *
 * @author esteban
 */
public class AECjson {
    
private String rutcedente;
private String rutcesionario;
private String nmbcontacto;
private String fonocontacto;
private String mailcontacto;




 private ArrayList<CesionJson> cesiones;

    public String getRutcedente() {
        return rutcedente;
    }

    public void setRutcedente(String rutcedente) {
        this.rutcedente = rutcedente;
    }

    public String getRutcesionario() {
        return rutcesionario;
    }

    public void setRutcesionario(String rutcesionario) {
        this.rutcesionario = rutcesionario;
    }

    public String getNmbcontacto() {
        return nmbcontacto;
    }

    public void setNmbcontacto(String nmbcontacto) {
        this.nmbcontacto = nmbcontacto;
    }

    public String getFonocontacto() {
        return fonocontacto;
    }

    public void setFonocontacto(String fonocontacto) {
        this.fonocontacto = fonocontacto;
    }

    public String getMailcontacto() {
        return mailcontacto;
    }

    public void setMailcontacto(String mailcontacto) {
        this.mailcontacto = mailcontacto;
    }

    public ArrayList<CesionJson> getCesiones() {
        return cesiones;
    }

    public void setCesiones(ArrayList<CesionJson> cesiones) {
        this.cesiones = cesiones;
    }

}
