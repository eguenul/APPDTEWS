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
public class DetalleDteJson {
private int nrolinea;
private String tpocodigo;
private String vlrcodigo;

private String nmbitem;
private String dscitem;
private int qtyitem;
private int prcitem;
private int montoitem;
private int descuentopct;
private int descuentomonto;


/*
private  String item1;
*/
public void DetalleDteJson(){
    descuentopct = 0;
    
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
    
    public void setDescuentopct(int descuentoitem){
        this.descuentopct = descuentoitem;
    }
    
    public int getDescuentopct(){
        return descuentopct;
        
    }

    public int getDescuentomonto() {
        return descuentomonto;
    }

    public void setDescuentomonto(int descuentomonto) {
        this.descuentomonto = descuentomonto;
    }

    
}
