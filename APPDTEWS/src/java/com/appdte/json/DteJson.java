/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.json;

import java.util.List;

/**
 *
 * @author esteban
 */
public class DteJson {
    
   
   

   String tipodte;
   String numdte;
   
   
 IdDteJson iddoc;
 EmisorJson emisor;
 ReceptorJson receptor;

 List<DetalleDteJson> detalle;
 TotalesJson totales;
 ReferenciaJson referencia; 

    public void setIdDte(IdDteJson iddoc){
        this.iddoc = iddoc;
        
    }
    
    public IdDteJson getIdDte(){
        return iddoc;
       
    }
    
    public void setEmisor(EmisorJson emisor){
        this.emisor = emisor;
        
    }
    
    public EmisorJson getEmisor(){
        
        return emisor;
    }
    
    
    
    public void setReceptor(ReceptorJson receptor){
        this.receptor = receptor;
        
    }
    
    public ReceptorJson getReceptor(){
        
        return receptor;
    }
    
    
    public void setTotales(TotalesJson totales){
        this.totales = totales;
        
    }
    
    public TotalesJson getTotales(){
           return totales; 
        
    }
    
    
    
    
    
    


   public void setDetalleDteJson(List<DetalleDteJson> detalle){
       this.detalle = detalle;
       
   }
   
   public List<DetalleDteJson> getDetalleDteJson(){
        return detalle;
       
   }

    public String getTipodte() {
        return tipodte;
    }

    public void setTipodte(String tipodte) {
        this.tipodte = tipodte;
    }

    public String getNumdte() {
        return numdte;
    }

    public void setNumdte(String numdte) {
        this.numdte = numdte;
    }

    public ReferenciaJson getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaJson referencia) {
        this.referencia = referencia;
    }

    
}
