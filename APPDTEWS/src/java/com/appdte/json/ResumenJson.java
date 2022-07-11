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
public class ResumenJson {
    
  private  String tipodocumento;
      
  private  String mntneto;
  private  String mntiva;
  private  String tasaiva;
  private  String mntexento;
  private  String mnttotal;
  private  String foliosemitidos;
  private  String foliosanulados;
  private  String foliosutilizados;
  private ArrayList<RangoJson> rango;
    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getMntneto() {
        return mntneto;
    }

    public void setMntneto(String mntneto) {
        this.mntneto = mntneto;
    }

    public String getMntiva() {
        return mntiva;
    }

    public void setMntiva(String mntiva) {
        this.mntiva = mntiva;
    }

    public String getTasaiva() {
        return tasaiva;
    }

    public void setTasaiva(String tasaiva) {
        this.tasaiva = tasaiva;
    }

    public String getMntexento() {
        return mntexento;
    }

    public void setMntexento(String mntexento) {
        this.mntexento = mntexento;
    }

    public String getMnttotal() {
        return mnttotal;
    }

    public void setMnttotal(String mnttotal) {
        this.mnttotal = mnttotal;
    }

    public String getFoliosemitidos() {
        return foliosemitidos;
    }

    public void setFoliosemitidos(String foliosemitidos) {
        this.foliosemitidos = foliosemitidos;
    }

    public String getFoliosanulados() {
        return foliosanulados;
    }

    public void setFoliosanulados(String foliosanulados) {
        this.foliosanulados = foliosanulados;
    }

    public String getFoliosutilizados() {
        return foliosutilizados;
    }

    public void setFoliosutilizados(String foliosutilizados) {
        this.foliosutilizados = foliosutilizados;
    }

    public ArrayList<RangoJson> getRango() {
        return rango;
    }

    public void setRango(ArrayList<RangoJson> rango) {
        this.rango = rango;
    }
    
    
       
}
