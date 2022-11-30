/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.util.ArrayList;

/**
 *
 * @author esteban
 */
public class ICVVentaJSON {
    
    private ArrayList <DetalleVenta> detalleventas;

    public ArrayList <DetalleVenta> getDetalledocumento() {
        return detalleventas;
    }

    public void setDetalledocumento(ArrayList <DetalleVenta> detalleventas) {
        this.detalleventas = detalleventas;
    }
    
    
}
