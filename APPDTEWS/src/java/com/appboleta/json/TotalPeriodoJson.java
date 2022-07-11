/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.json;

/**
 *
 * @author esteban
 */
public class TotalPeriodoJson {
    
   private String TpoDoc;
   private String TotAnulado;
   private TotalesServicio2Json TotalesServicio;

    public String getTpoDoc() {
        return TpoDoc;
    }

    public void setTpoDoc(String TpoDoc) {
        this.TpoDoc = TpoDoc;
    }

    public String getTotAnulado() {
        return TotAnulado;
    }

    public void setTotAnulado(String TotAnulado) {
        this.TotAnulado = TotAnulado;
    }
       
  

    public TotalesServicio2Json getTotalesServicio() {
        return TotalesServicio;
    }

    public void setTotalesServicio(TotalesServicio2Json TotalesServicio) {
        this.TotalesServicio = TotalesServicio;
    }
    
    
}
