/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.models;


public class DetalleDteModel {
private int nrolinea;
private String tpocodigo;
private String vlrcodigo;

private String nmbitem;
private String dscitem;
private String qtyitem;
private String prcitem;
private String montoitem;

private String descuentopct;
private String descuentomonto;
private String undmditem;

 private String indexe;

    public String getDescuentomonto() {
        return descuentomonto;
    }

    public void setDescuentomonto(String descuentomonto) {
        this.descuentomonto = descuentomonto;
    }

public DetalleDteModel(){
  descuentopct="0";
  indexe="0";
  descuentomonto="0";
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

    public String getQtyitem() {
        return qtyitem;
    }

    public void setQtyitem(String qtyitem) {
        this.qtyitem = qtyitem;
    }

    public String getPrcitem() {
        return prcitem;
    }

    public void setPrcitem(String prcitem) {
        this.prcitem = prcitem;
    }

    public String getMontoitem() {
        return montoitem;
    }

    public void setMontoitem(String montoitem) {
        this.montoitem = montoitem;
    }

    public String getDescuentopct() {
        return descuentopct;
    }

    public void setDescuentopct(String descuentoitem) {
        this.descuentopct = descuentoitem;
    }

    public String getIndexe() {
        return indexe;
    }

    public void setIndexe(String indexe) {
        this.indexe = indexe;
    }

    /**
     * @return the undmdref
     */
    public String getUndmdItem() {
        return undmditem;
    }

    
    public void setUndmdItem(String undmditem) {
        this.undmditem = undmditem;
    }
    


    
}
