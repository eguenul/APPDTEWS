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
public class DocumentoConsumoFoliosJson {
    private CaratulaConsumoJson Caratula;
 private List<ResumenConsumoJson> Resumen;

    public CaratulaConsumoJson getCaratula() {
        return Caratula;
    }

    public void setCaratula(CaratulaConsumoJson Caratula) {
        this.Caratula = Caratula;
    }

    public List<ResumenConsumoJson> getResumen() {
        return Resumen;
    }

    public void setResumen(List<ResumenConsumoJson> Resumen) {
        this.Resumen = Resumen;
    }
 
 
}
