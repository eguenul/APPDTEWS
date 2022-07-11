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
public class ICVCompraJSON {
    
    private ArrayList <DetalleCompra> detallecompras;

    public ArrayList <DetalleCompra> getDetalledocumento() {
        return detallecompras;
    }

    public void setDetalledocumento(ArrayList <DetalleCompra> detallecompras) {
        this.detallecompras = detallecompras;
    }
    
    
}
