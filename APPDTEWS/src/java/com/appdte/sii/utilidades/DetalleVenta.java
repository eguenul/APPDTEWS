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
public class DetalleVenta {
private String Tipo_Doc;
private String Tipo_venta;
private String RUT_cliente;
private String Razon_Social;
private String Folio;
private String Fecha_Docto;
private String Fecha_Recepcion;
private String Fecha_Acuse_Recibo;
private String Monto_Exento;
private String Monto_Neto;
private String Monto_IVA;
private String Monto_Total;
private String IVA_Retenido_Total;
private String IVA_Retenido_Parcial;

private String IVA_no_retenido;
private String IVA_propio;
private String IVA_Terceros;


private String RUT_Emisor_Liquid_Factura;
private String Neto_Comision_Liquid_Factura;
private String Exento_Comision_Liquid_Factura;
private String IVA_Comision_Liquid_Factura;
private String IVA_fuera_de_plazo;
private String Tipo_Docto_Referencia;
private String Folio_Docto_Referencia;

private String Num_Ident_Receptor_Extranjero;
private String Nacionalidad_Receptor_Extranjero;
private String Credito_empresa_constructora;
private String Impto_Zona_Franca_Ley_1821;

private String Garantia_Dep_Envases;
private String Indicador_Venta_sin_Costo;
private String Indicador_Servicio_Periodico;
private String Monto_No_facturable;
private String Total_Monto_Periodo;
private String Venta_Pasajes_Transporte_Nacional;
private String Venta_Pasajes_Transporte_Internacional;
private String Numero_Interno;
private String Codigo_Sucursal;
private String NCE_o_NDE_sobre_Fact_de_Compra;
private String Codigo_Otro_Imp;
        
private String Valor_Otro_Imp;
private String Tasa_Otro_Imp;        

    public String getTipo_Doc() {
        return Tipo_Doc;
    }

    public void setTipo_Doc(String Tipo_Doc) {
        this.Tipo_Doc = Tipo_Doc;
    }

    public String getTipo_venta() {
        return Tipo_venta;
    }

    public void setTipo_venta(String Tipo_venta) {
        this.Tipo_venta = Tipo_venta;
    }

    public String getRUT_cliente() {
        return RUT_cliente;
    }

    public void setRUT_cliente(String RUT_cliente) {
        this.RUT_cliente = RUT_cliente;
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

    public String getFecha_Acuse_Recibo() {
        return Fecha_Acuse_Recibo;
    }

    public void setFecha_Acuse_Recibo(String Fecha_Acuse_Recibo) {
        this.Fecha_Acuse_Recibo = Fecha_Acuse_Recibo;
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

    public String getMonto_IVA() {
        return Monto_IVA;
    }

    public void setMonto_IVA(String Monto_IVA) {
        this.Monto_IVA = Monto_IVA;
    }

    public String getMonto_Total() {
        return Monto_Total;
    }

    public void setMonto_Total(String Monto_Total) {
        this.Monto_Total = Monto_Total;
    }

    public String getIVA_Retenido_Total() {
        return IVA_Retenido_Total;
    }

    public void setIVA_Retenido_Total(String IVA_Retenido_Total) {
        this.IVA_Retenido_Total = IVA_Retenido_Total;
    }

    public String getIVA_Retenido_Parcial() {
        return IVA_Retenido_Parcial;
    }

    public void setIVA_Retenido_Parcial(String IVA_Retenido_Parcial) {
        this.IVA_Retenido_Parcial = IVA_Retenido_Parcial;
    }

    public String getRUT_Emisor_Liquid_Factura() {
        return RUT_Emisor_Liquid_Factura;
    }

    public void setRUT_Emisor_Liquid_Factura(String RUT_Emisor_Liquid_Factura) {
        this.RUT_Emisor_Liquid_Factura = RUT_Emisor_Liquid_Factura;
    }

    public String getNeto_Comision_Liquid_Factura() {
        return Neto_Comision_Liquid_Factura;
    }

    public void setNeto_Comision_Liquid_Factura(String Neto_Comision_Liquid_Factura) {
        this.Neto_Comision_Liquid_Factura = Neto_Comision_Liquid_Factura;
    }

    public String getExento_Comision_Liquid_Factura() {
        return Exento_Comision_Liquid_Factura;
    }

    public void setExento_Comision_Liquid_Factura(String Exento_Comision_Liquid_Factura) {
        this.Exento_Comision_Liquid_Factura = Exento_Comision_Liquid_Factura;
    }

    public String getIVA_Comision_Liquid_Factura() {
        return IVA_Comision_Liquid_Factura;
    }

    public void setIVA_Comision_Liquid_Factura(String IVA_Comision_Liquid_Factura) {
        this.IVA_Comision_Liquid_Factura = IVA_Comision_Liquid_Factura;
    }

    public String getIVA_fuera_de_plazo() {
        return IVA_fuera_de_plazo;
    }

    public void setIVA_fuera_de_plazo(String IVA_fuera_de_plazo) {
        this.IVA_fuera_de_plazo = IVA_fuera_de_plazo;
    }

    public String getTipo_Docto_Referencia() {
        return Tipo_Docto_Referencia;
    }

    public void setTipo_Docto_Referencia(String Tipo_Docto_Referencia) {
        this.Tipo_Docto_Referencia = Tipo_Docto_Referencia;
    }

    public String getFolio_Docto_Referencia() {
        return Folio_Docto_Referencia;
    }

    public void setFolio_Docto_Referencia(String Folio_Docto_Referencia) {
        this.Folio_Docto_Referencia = Folio_Docto_Referencia;
    }

    public String getNum_Ident_Receptor_Extranjero() {
        return Num_Ident_Receptor_Extranjero;
    }

    public void setNum_Ident_Receptor_Extranjero(String Num_Ident_Receptor_Extranjero) {
        this.Num_Ident_Receptor_Extranjero = Num_Ident_Receptor_Extranjero;
    }

    public String getNacionalidad_Receptor_Extranjero() {
        return Nacionalidad_Receptor_Extranjero;
    }

    public void setNacionalidad_Receptor_Extranjero(String Nacionalidad_Receptor_Extranjero) {
        this.Nacionalidad_Receptor_Extranjero = Nacionalidad_Receptor_Extranjero;
    }

    public String getCredito_empresa_constructora() {
        return Credito_empresa_constructora;
    }

    public void setCredito_empresa_constructora(String Credito_empresa_constructora) {
        this.Credito_empresa_constructora = Credito_empresa_constructora;
    }

    public String getImpto_Zona_Franca_Ley_1821() {
        return Impto_Zona_Franca_Ley_1821;
    }

    public void setImpto_Zona_Franca_Ley_1821(String Impto_Zona_Franca_Ley_1821) {
        this.Impto_Zona_Franca_Ley_1821 = Impto_Zona_Franca_Ley_1821;
    }

    public String getGarantia_Dep_Envases() {
        return Garantia_Dep_Envases;
    }

    public void setGarantia_Dep_Envases(String Garantia_Dep_Envases) {
        this.Garantia_Dep_Envases = Garantia_Dep_Envases;
    }

    public String getIndicador_Venta_sin_Costo() {
        return Indicador_Venta_sin_Costo;
    }

    public void setIndicador_Venta_sin_Costo(String Indicador_Venta_sin_Costo) {
        this.Indicador_Venta_sin_Costo = Indicador_Venta_sin_Costo;
    }

    public String getIndicador_Servicio_Periodico() {
        return Indicador_Servicio_Periodico;
    }

    public void setIndicador_Servicio_Periodico(String Indicador_Servicio_Periodico) {
        this.Indicador_Servicio_Periodico = Indicador_Servicio_Periodico;
    }

    public String getMonto_No_facturable() {
        return Monto_No_facturable;
    }

    public void setMonto_No_facturable(String Monto_No_facturable) {
        this.Monto_No_facturable = Monto_No_facturable;
    }

    public String getTotal_Monto_Periodo() {
        return Total_Monto_Periodo;
    }

    public void setTotal_Monto_Periodo(String Total_Monto_Periodo) {
        this.Total_Monto_Periodo = Total_Monto_Periodo;
    }

    public String getVenta_Pasajes_Transporte_Nacional() {
        return Venta_Pasajes_Transporte_Nacional;
    }

    public void setVenta_Pasajes_Transporte_Nacional(String Venta_Pasajes_Transporte_Nacional) {
        this.Venta_Pasajes_Transporte_Nacional = Venta_Pasajes_Transporte_Nacional;
    }

    public String getVenta_Pasajes_Transporte_Internacional() {
        return Venta_Pasajes_Transporte_Internacional;
    }

    public void setVenta_Pasajes_Transporte_Internacional(String Venta_Pasajes_Transporte_Internacional) {
        this.Venta_Pasajes_Transporte_Internacional = Venta_Pasajes_Transporte_Internacional;
    }

    public String getNumero_Interno() {
        return Numero_Interno;
    }

    public void setNumero_Interno(String Numero_Interno) {
        this.Numero_Interno = Numero_Interno;
    }

    public String getCodigo_Sucursal() {
        return Codigo_Sucursal;
    }

    public void setCodigo_Sucursal(String Codigo_Sucursal) {
        this.Codigo_Sucursal = Codigo_Sucursal;
    }

    public String getNCE_o_NDE_sobre_Fact_de_Compra() {
        return NCE_o_NDE_sobre_Fact_de_Compra;
    }

    public void setNCE_o_NDE_sobre_Fact_de_Compra(String NCE_o_NDE_sobre_Fact_de_Compra) {
        this.NCE_o_NDE_sobre_Fact_de_Compra = NCE_o_NDE_sobre_Fact_de_Compra;
    }

    public String getCodigo_Otro_Imp() {
        return Codigo_Otro_Imp;
    }

    public void setCodigo_Otro_Imp(String Codigo_Otro_Imp) {
        this.Codigo_Otro_Imp = Codigo_Otro_Imp;
    }

    public String getValor_Otro_Imp() {
        return Valor_Otro_Imp;
    }

    public void setValor_Otro_Imp(String Valor_Otro_Imp) {
        this.Valor_Otro_Imp = Valor_Otro_Imp;
    }

    public String getTasa_Otro_Imp() {
        return Tasa_Otro_Imp;
    }

    public void setTasa_Otro_Imp(String Tasa_Otro_Imp) {
        this.Tasa_Otro_Imp = Tasa_Otro_Imp;
    }

    public String getIVA_no_retenido() {
        return IVA_no_retenido;
    }

    public void setIVA_no_retenido(String IVA_no_retenido) {
        this.IVA_no_retenido = IVA_no_retenido;
    }

    public String getIVA_propio() {
        return IVA_propio;
    }

    public void setIVA_propio(String IVA_propio) {
        this.IVA_propio = IVA_propio;
    }

    public String getIVA_Terceros() {
        return IVA_Terceros;
    }

    public void setIVA_Terceros(String IVA_Terceros) {
        this.IVA_Terceros = IVA_Terceros;
    }











}  
