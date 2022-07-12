/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.json;

import java.util.List;

/**
 *
 * @author esteban
 */
public class ResumenConsumoJson {
   private String TipoDocumento;
   private String MntNeto;
   private String MntIva;
   private String TasaIva;
   private String MntExento;
   private String MntTotal;
   private String FoliosEmitidos;
   private String FoliosAnulados;
   private String FoliosUtilizados;
   
   
   private List<RangoUtilizadosJson> RangoUtilizados;
  
   private List<RangoAnuladosJson> RangoAnulados;
   
   
    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }

    public String getMntNeto() {
        return MntNeto;
    }

    public void setMntNeto(String MntNeto) {
        this.MntNeto = MntNeto;
    }

    public String getMntIva() {
        return MntIva;
    }

    public void setMntIva(String MntIva) {
        this.MntIva = MntIva;
    }

    public String getTasaIva() {
        return TasaIva;
    }

    public void setTasaIva(String TasaIva) {
        this.TasaIva = TasaIva;
    }

    public String getMntExento() {
        return MntExento;
    }

    public void setMntExento(String MntExento) {
        this.MntExento = MntExento;
    }

    public String getMntTotal() {
        return MntTotal;
    }

    public void setMntTotal(String MntTotal) {
        this.MntTotal = MntTotal;
    }

    public String getFoliosEmitidos() {
        return FoliosEmitidos;
    }

    public void setFoliosEmitidos(String FoliosEmitidos) {
        this.FoliosEmitidos = FoliosEmitidos;
    }

    public String getFoliosAnulados() {
        return FoliosAnulados;
    }

    public void setFoliosAnulados(String FoliosAnulados) {
        this.FoliosAnulados = FoliosAnulados;
    }

    public String getFoliosUtilizados() {
        return FoliosUtilizados;
    }

    public void setFoliosUtilizados(String FoliosUtilizados) {
        this.FoliosUtilizados = FoliosUtilizados;
    }

    public List<RangoAnuladosJson> getRangoAnulados() {
        return RangoAnulados;
    }

    public void setRangoAnulados(List<RangoAnuladosJson> RangoAnulados) {
        this.RangoAnulados = RangoAnulados;
    }

    public List<RangoUtilizadosJson> getRangoUtilizados() {
        return RangoUtilizados;
    }

    public void setRangoUtilizados(List<RangoUtilizadosJson> RangoUtilizados) {
        this.RangoUtilizados = RangoUtilizados;
    }
   
   
    
    
    
    
}
