/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.json;

/**
 *
 * @author esteban
 */
public class CedenteJson {
private String rut;
private String razonsocial;
private String direccion;
private String email;
private RutAutorizadojson rutautorizado;

    public RutAutorizadojson getRutautorizado() {
        return rutautorizado;
    }

    public void setRutautorizado(RutAutorizadojson rutautorizado) {
        this.rutautorizado = rutautorizado;
    }
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
