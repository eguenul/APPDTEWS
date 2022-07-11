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
public class EnvioLibroJson {
   private CaratulaJson Caratula;
   private ResumenSegmentoJson ResumenSegmento;
   private ResumenPeriodoJson ResumenPeriodo;
   private DetalleJson Detalle;

    public DetalleJson getDetalle() {
        return Detalle;
    }

    public void setDetalle(DetalleJson Detalle) {
        this.Detalle = Detalle;
    }
   
   
   
   /*
  private List<DetalleJson> detalle; 
*/
    public CaratulaJson getCaratula() {
        return Caratula;
    }

    public void setCaratula(CaratulaJson Caratula) {
        this.Caratula = Caratula;
    }
    
    
    
   
    
    
    
    
/*
    public List<DetalleJson> getDetalle() {
        return detalle;
    }
/*
    public void setDetalle(List<DetalleJson> detalle) {
        this.detalle = detalle;
    }
    
 */  

    public ResumenPeriodoJson getResumenPeriodo() {
        return ResumenPeriodo;
    }

    public void setResumenPeriodo(ResumenPeriodoJson ResumenPeriodo) {
        this.ResumenPeriodo = ResumenPeriodo;
    }

    public ResumenSegmentoJson getResumenSegmento() {
        return ResumenSegmento;
    }

    public void setResumenSegmento(ResumenSegmentoJson ResumenSegmento) {
        this.ResumenSegmento = ResumenSegmento;
    }
     
    
    
}
