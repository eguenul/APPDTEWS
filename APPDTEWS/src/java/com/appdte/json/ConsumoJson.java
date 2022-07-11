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
public class ConsumoJson {
  ArrayList<ResumenJson> resumen;
  EmisorJson emisor;
  ResumenJson objresumen;
  CaratulaJson caratula;

    public CaratulaJson getCaratula() {
        return caratula;
    }

    public void setCaratula(CaratulaJson caratula) {
        this.caratula = caratula;
    }
    public ResumenJson getObjresumen() {
        return objresumen;
    }

    public void setObjresumen(ResumenJson objresumen) {
        this.objresumen = objresumen;
    }
 public ConsumoJson(){
     
     
 }
 
 
    
    public void setEmisor(EmisorJson emisor){
        this.emisor = emisor;
        
    }
    
    public EmisorJson getEmisor(){
        
        return emisor;
    }
    
 
    public ArrayList<ResumenJson> getResumen() {
        return resumen;
    }

    public void setResumen(ArrayList<ResumenJson> resumen) {
        this.resumen = resumen;
    }
    
    
    
    
}
