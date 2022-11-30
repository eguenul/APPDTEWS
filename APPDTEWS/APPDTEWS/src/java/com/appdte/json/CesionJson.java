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
public class CesionJson {
   private CedenteJson cedente;
   private CesionarioJson cesionario;
   private String seqcesion;
   private RutAutorizadojson rutautorizado;
   private IdDteCesionjson iddte;
   private String ultimovencimiento;
   private String montocesion;
   /*
   private String rsreceptor;
   */
    public CedenteJson getCedente() {
        return cedente;
    }

    public void setCedente(CedenteJson cedente) {
        this.cedente = cedente;
    }

    public CesionarioJson getCesionario() {
        return cesionario;
    }

    public void setCesionario(CesionarioJson cesionario) {
        this.cesionario = cesionario;
    }

    public RutAutorizadojson getRutautorizado() {
        return rutautorizado;
    }

    public void setRutautorizado(RutAutorizadojson rutautorizado) {
        this.rutautorizado = rutautorizado;
    }

    public String getSeqcesion() {
        return seqcesion;
    }

    public void setSeqcesion(String seqcesion) {
        this.seqcesion = seqcesion;
    }

    public IdDteCesionjson getIddte() {
        return iddte;
    }

    public void setIddte(IdDteCesionjson iddte) {
        this.iddte = iddte;
    }

    public String getUltimovencimiento() {
        return ultimovencimiento;
    }

    public void setUltimovencimiento(String ultimovencimiento) {
        this.ultimovencimiento = ultimovencimiento;
    }

    public String getMontocesion() {
        return montocesion;
    }

    public void setMontocesion(String montocesion) {
        this.montocesion = montocesion;
    }
/*
    public String getRsreceptor() {
        return rsreceptor;
    }

    public void setRsreceptor(String rsreceptor) {
        this.rsreceptor = rsreceptor;
    }
    
  */  
}
