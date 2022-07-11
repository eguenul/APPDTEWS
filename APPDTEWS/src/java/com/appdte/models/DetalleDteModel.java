/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.models;


public class DetalleDteModel {
int nrolinea;
String tpocodigo;
String vlrcodigo;

String nmbitem;
String dscitem;
int qtyitem;
int prcitem;
int montoitem;

int descuentopct;
int descuentomonto;

    public int getDescuentomonto() {
        return descuentomonto;
    }

    public void setDescuentomonto(int descuentomonto) {
        this.descuentomonto = descuentomonto;
    }

public DetalleDteModel(){
  descuentopct=0;
}





    public int getNrolinea() {
        return nrolinea;
    }

    public void setNrolinea(int nrolinea) {
        this.nrolinea = nrolinea;
    }

    public String getTpocodigo() {
        return tpocodigo;
    }

    public void setTpocodigo(String tpocodigo) {
        this.tpocodigo = tpocodigo;
    }

    public String getVlrcodigo() {
        return vlrcodigo;
    }

    public void setVlrcodigo(String vlrcodigo) {
        this.vlrcodigo = vlrcodigo;
    }

    public String getNmbitem() {
        return nmbitem;
    }

    public void setNmbitem(String nmbitem) {
        this.nmbitem = nmbitem;
    }

    public String getDscitem() {
        return dscitem;
    }

    public void setDscitem(String dscitem) {
        this.dscitem = dscitem;
    }

    public int getQtyitem() {
        return qtyitem;
    }

    public void setQtyitem(int qtyitem) {
        this.qtyitem = qtyitem;
    }

    public int getPrcitem() {
        return prcitem;
    }

    public void setPrcitem(int prcitem) {
        this.prcitem = prcitem;
    }

    public int getMontoitem() {
        return montoitem;
    }

    public void setMontoitem(int montoitem) {
        this.montoitem = montoitem;
    }

    public int getDescuentopct() {
        return descuentopct;
    }

    public void setDescuentopct(int descuentoitem) {
        this.descuentopct = descuentoitem;
    }
    


    
}
