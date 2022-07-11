/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

/**
 *
 * @author esteban
 */
public class DetalleCompra {
    private String Tipo_Doc;
    private String Tipo_Compra;
    private String RUT_Proveedor;
    private String Razon_Social;
    private String Folio;
    private String Fecha_Docto;
    private String Fecha_Recepcion;
    private String Fecha_Acuse;
     private String Monto_Exento;
     private String Monto_Neto;
  private String Monto_IVA_Recuperable;
  private String Monto_Iva_No_Recuperable;
  private String Codigo_IVA_No_Rec;
   private String Monto_Total;
 
   private String Monto_Neto_Activo_Fijo;
    private String IVA_Activo_Fijo;
    private String IVA_uso_Comun;
 private String Impto_Sin_Derecho_a_Credito;
 private String IVA_No_Retenido;
  private String Tabacos_Puros;
  private String Tabacos_Cigarrillos;
   private String Tabacos_Elaborados;
    private String NCE_o_NDE_sobre_Fact_de_Compra;
    private String Codigo_Otro_Impuesto;
    private String Valor_Otro_Impuesto;
    private String Tasa_Otro_Impuesto;

    public String getTipo_Doc() {
        return Tipo_Doc;
    }

    public void setTipo_Doc(String Tipo_Doc) {
        this.Tipo_Doc = Tipo_Doc;
    }

    public String getTipo_Compra() {
        return Tipo_Compra;
    }

    public void setTipo_Compra(String Tipo_Compra) {
        this.Tipo_Compra = Tipo_Compra;
    }

    public String getRUT_Proveedor() {
        return RUT_Proveedor;
    }

    public void setRUT_Proveedor(String RUT_Proveedor) {
        this.RUT_Proveedor = RUT_Proveedor;
    }

    public String getRazon_Social() {
        return Razon_Social;
    }

    public void setRazon_Social(String Razon_Social) {
        this.Razon_Social = Razon_Social;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getFecha_Docto() {
        return Fecha_Docto;
    }

    public void setFecha_Docto(String Fecha_Docto) {
        this.Fecha_Docto = Fecha_Docto;
    }

    public String getFecha_Recepcion() {
        return Fecha_Recepcion;
    }

    public void setFecha_Recepcion(String Fecha_Recepcion) {
        this.Fecha_Recepcion = Fecha_Recepcion;
    }

    public String getFecha_Acuse() {
        return Fecha_Acuse;
    }

    public void setFecha_Acuse(String Fecha_Acuse) {
        this.Fecha_Acuse = Fecha_Acuse;
    }

    public String getMonto_Exento() {
        return Monto_Exento;
    }

    public void setMonto_Exento(String Monto_Exento) {
        this.Monto_Exento = Monto_Exento;
    }

    public String getMonto_Neto() {
        return Monto_Neto;
    }

    public void setMonto_Neto(String Monto_Neto) {
        this.Monto_Neto = Monto_Neto;
    }

    public String getMonto_IVA_Recuperable() {
        return Monto_IVA_Recuperable;
    }

    public void setMonto_IVA_Recuperable(String Monto_IVA_Recuperable) {
        this.Monto_IVA_Recuperable = Monto_IVA_Recuperable;
    }

    public String getMonto_Iva_No_Recuperable() {
        return Monto_Iva_No_Recuperable;
    }

    public void setMonto_Iva_No_Recuperable(String Monto_Iva_No_Recuperable) {
        this.Monto_Iva_No_Recuperable = Monto_Iva_No_Recuperable;
    }

    public String getCodigo_IVA_No_Rec() {
        return Codigo_IVA_No_Rec;
    }

    public void setCodigo_IVA_No_Rec(String Codigo_IVA_No_Rec) {
        this.Codigo_IVA_No_Rec = Codigo_IVA_No_Rec;
    }

    public String getMonto_Total() {
        return Monto_Total;
    }

    public void setMonto_Total(String Monto_Total) {
        this.Monto_Total = Monto_Total;
    }

  

    public String getIVA_Activo_Fijo() {
        return IVA_Activo_Fijo;
    }

    public void setIVA_Activo_Fijo(String IVA_Activo_Fijo) {
        this.IVA_Activo_Fijo = IVA_Activo_Fijo;
    }

    public String getIVA_uso_Comun() {
        return IVA_uso_Comun;
    }

    public void setIVA_uso_Comun(String IVA_uso_Comun) {
        this.IVA_uso_Comun = IVA_uso_Comun;
    }

    public String getImpto_Sin_Derecho_a_Credito() {
        return Impto_Sin_Derecho_a_Credito;
    }

    public void setImpto_Sin_Derecho_a_Credito(String Impto_Sin_Derecho_a_Credito) {
        this.Impto_Sin_Derecho_a_Credito = Impto_Sin_Derecho_a_Credito;
    }

    public String getIVA_No_Retenido() {
        return IVA_No_Retenido;
    }

    public void setIVA_No_Retenido(String IVA_No_Retenido) {
        this.IVA_No_Retenido = IVA_No_Retenido;
    }

    public String getTabacos_Puros() {
        return Tabacos_Puros;
    }

    public void setTabacos_Puros(String Tabacos_Puros) {
        this.Tabacos_Puros = Tabacos_Puros;
    }

    public String getTabacos_Cigarrillos() {
        return Tabacos_Cigarrillos;
    }

    public void setTabacos_Cigarrillos(String Tabacos_Cigarrillos) {
        this.Tabacos_Cigarrillos = Tabacos_Cigarrillos;
    }

    public String getTabacos_Elaborados() {
        return Tabacos_Elaborados;
    }

    public void setTabacos_Elaborados(String Tabacos_Elaborados) {
        this.Tabacos_Elaborados = Tabacos_Elaborados;
    }

    public String getNCE_o_NDE_sobre_Fact_de_Compra() {
        return NCE_o_NDE_sobre_Fact_de_Compra;
    }

    public void setNCE_o_NDE_sobre_Fact_de_Compra(String NCE_o_NDE_sobre_Fact_de_Compra) {
        this.NCE_o_NDE_sobre_Fact_de_Compra = NCE_o_NDE_sobre_Fact_de_Compra;
    }

    public String getCodigo_Otro_Impuesto() {
        return Codigo_Otro_Impuesto;
    }

    public void setCodigo_Otro_Impuesto(String Codigo_Otro_Impuesto) {
        this.Codigo_Otro_Impuesto = Codigo_Otro_Impuesto;
    }

    public String getValor_Otro_Impuesto() {
        return Valor_Otro_Impuesto;
    }

    public void setValor_Otro_Impuesto(String Valor_Otro_Impuesto) {
        this.Valor_Otro_Impuesto = Valor_Otro_Impuesto;
    }

    public String getTasa_Otro_Impuesto() {
        return Tasa_Otro_Impuesto;
    }

    public void setTasa_Otro_Impuesto(String Tasa_Otro_Impuesto) {
        this.Tasa_Otro_Impuesto = Tasa_Otro_Impuesto;
    }

    public String getMonto_Neto_Activo_Fijo() {
        return Monto_Neto_Activo_Fijo;
    }

    public void setMonto_Neto_Activo_Fijo(String Monto_Neto_Activo_Fijo) {
        this.Monto_Neto_Activo_Fijo = Monto_Neto_Activo_Fijo;
    }
    
    
    
}
