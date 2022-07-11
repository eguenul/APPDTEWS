/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import com.google.gson.Gson;
import com.appdte.sii.cl.Semilla;
import com.appdte.sii.cl.Token;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ICVVenta {
    
    
    

    
public String obtieneVentas(String login, String clave,String rutempresa,String periodo, String serverauth) throws IOException, ParserConfigurationException, SAXException, Exception{
 String[] arrayrutempresa = rutempresa.trim().split("-");
 

 String pathcertificado = login;
  
 Semilla objsemilla = new Semilla();

String valorsemilla =  objsemilla.getSeed(serverauth);
 
 Token objtoken = new Token(serverauth);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,"");

    
             
        URL url = new URL("https://www4.sii.cl/consdcvinternetui/services/data/facadeService/getDetalleVentaExport");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("Content-Type", "application/json"); 
   
         conn.setRequestProperty("Cookie","TOKEN="+valortoken);
        
          
           
           /* cuerpo de la peticion request */
   
 
String stringRequest = 



 "{"+
"\"metaData\":"+
 " { "+
"\"conversationId\"" + ":\"" + valortoken+  "\" ," + 
"\"namespace\""+ ":" + "\"cl.sii.sdi.lob.diii.consdcv.data.api.interfaces.FacadeService/getDetalleVentaExport\"" + ","+ 
 "\"transactionId\"" + ":\""+  "0"+ "\""+
 "} "+
","+"\"data\":"+
" { "+
 "\"rutEmisor\""+ ":\""+  arrayrutempresa[0]   +"\","+ 
 "\"dvEmisor\"" + ":\"" +  arrayrutempresa[1]    +"\","+ 
"\"ptributario\""+ ":\"" + periodo +"\"," +
"\"estadoContab\"" + ":" + "\"REGISTRO\"" +"," +
"\"codTipoDoc\""+":\"" +"0"+"\"," +
"\"operacion\"" + ":" + "\"VENTA\"" +
 "}"+
 "}";
        

System.out.print(stringRequest);


 OutputStream outputStreamToRequestBody = conn.getOutputStream();
        BufferedWriter httpRequestBodyWriter =
                new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
                httpRequestBodyWriter.write(stringRequest);
                httpRequestBodyWriter.flush();
 


     
String targetString = null;
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read())
            targetString +=   (char) c;
  System.out.print(targetString);
        return targetString;
    }
    
    

public ArrayList<DetalleVenta> formatCSV(String stringCSV) throws IOException{
 ArrayList<DetalleVenta>  arraydetalleventa = new ArrayList<>();
  
   String headerCSV=  "null{" + "\"data\"" + ":[\"Nro;"
           + "Tipo Doc;"
           + "Tipo Venta;"
           + "Rut cliente;"
           + "Razon Social;"
           + "Folio;"
           + "Fecha Docto;"
           + "Fecha Recepcion;"
           + "Fecha Acuse Recibo;"
           + "Fecha Reclamo;"
           + "Monto Exento;"
           + "Monto Neto;"
           + "Monto IVA;"
           + "Monto total;"
           + "IVA Retenido Total;"
           + "IVA Retenido Parcial;"
           + "IVA no retenido;"
           + "IVA propio;"
           + "IVA Terceros;"
           + "RUT Emisor Liquid. Factura;"
           + "Neto Comision Liquid. Factura;"
           + "Exento Comision Liquid. Factura;"
           + "IVA Comision Liquid. Factura;"
           + "IVA fuera de plazo;"
           + "Tipo Docto. Referencia;"
           + "Folio Docto. Referencia;"
           + "Num. Ident. Receptor Extranjero;"
           + "Nacionalidad Receptor Extranjero;"
           + "Credito empresa constructora;"
           + "Impto. Zona Franca (Ley 18211);"
           + "Garantia Dep. Envases;"
           + "Indicador Venta sin Costo;"
           + "Indicador Servicio Periodico;"
           + "Monto No facturable;"
           + "Total Monto Periodo;"
           + "Venta Pasajes Transporte Nacional;"
           + "Venta Pasajes Transporte Internacional;"
           + "Numero Interno;"
           + "Codigo Sucursal;"
           + "NCE o NDE sobre Fact. de Compra;"
           + "Codigo Otro Imp.;Valor Otro Imp.;"
           + "Tasa Otro Imp.\"";
    
   
    String headerCSV2=  "{" + "\"data\"" + ":[\"Nro;"
           + "Tipo_Doc;"
           + "Tipo_Venta;"
           + "Rut_cliente;"
           + "Razon_Social;"
           + "Folio;"
           + "Fecha_Docto;"
           + "Fecha_Recepcion;"
           + "Fecha_Acuse_Recibo;"
           + "Fecha_Reclamo;"
           + "Monto_Exento;"
           + "Monto_Neto;"
           + "Monto_IVA;"
           + "Monto_total;"
           + "IVA_Retenido_Total;"
           + "IVA_Retenido_Parcial;"
           + "IVA_no_retenido;"
           + "IVA_propio;"
           + "IVA_Terceros;"
           + "RUT_Emisor_Liquid_Factura;"
           + "Neto_Comision_Liquid_Factura;"
           + "Exento_Comision_Liquid_Factura;"
           + "IVA_Comision_Liquid_Factura;"
           + "IVA_fuera_de_plazo;"
           + "Tipo_Docto_Referencia;"
           + "Folio_Docto_Referencia;"
           + "Num_Ident_Receptor_Extranjero;"
           + "Nacionalidad_Receptor_Extranjero;"
           + "Credito_empresa_constructora;"
           + "Impto_Zona_Franca_Ley_18211;"
           + "Garantia_Dep_Envases;"
            + "Indicador_Venta_sin_Costo;"
           + "Indicador_Servicio_Periodico;"
           + "Monto_No_facturable;"
           + "Total_Monto_Periodo;"
           + "Venta_Pasajes_Transporte_Nacional;"
           + "Venta_Pasajes_Transporte_Internacional;"
           + "Numero_Interno;"
           + "Codigo_Sucursal;"
           + "NCE_o_NDE_sobre_Fact_de_Compra;"
           + "Codigo_Otro_Imp;"
            + "Valor_Otro_Imp;"
           + "Tasa_Otro_Imp\"";
    
            
            
            
  String   stringCSV2 =  stringCSV.replace(headerCSV, headerCSV2);

 System.out.print(stringCSV2);
    InputStream isjson = new ByteArrayInputStream(stringCSV2.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    Venta venta = gson.fromJson(br1, Venta.class);
    
        String[]  stringdetalleventa = venta.getData();
    int i;
   
   
for(i=1; i<stringdetalleventa.length; i++ ){
      
String[] arraylineadetalle =  stringdetalleventa[i].split(";");
System.out.print(arraylineadetalle[0] + " "+arraylineadetalle[1] + " " + arraylineadetalle[2] + " "+arraylineadetalle[3]+" "+arraylineadetalle[4] + " "+arraylineadetalle[5]+ " "+arraylineadetalle[6]+ " "+arraylineadetalle[9]+ " "+arraylineadetalle[10]+ " "+arraylineadetalle[12]+" "+arraylineadetalle[14]);
  DetalleVenta objDetalleVenta = new DetalleVenta();
  
  
  /* obtengo eltipo de documento */
  objDetalleVenta.setTipo_Doc(arraylineadetalle[1]);
  /* obtengo descripcion del tipo de compra  */
 /*
  objDetalleVenta.setTipo_Compra(arraylineadetalle[2]);
 */
  /* obtengo rut del proveedor */
  objDetalleVenta.setRUT_cliente(arraylineadetalle[3]);
   /* obtengo razon social del proveedor */
    objDetalleVenta.setRazon_Social(arraylineadetalle[4]);
  /* OBTENGO FECHA DEL DOCUMENTO */
    objDetalleVenta.setFolio((arraylineadetalle[5]));
  /* OBTENGO FECHA DEL DOCUMENTO */
    objDetalleVenta.setFecha_Docto((arraylineadetalle[6]));
    
  /* monto exento */  
 objDetalleVenta.setMonto_Exento(arraylineadetalle[10]);
    
 /* monto neto */
 objDetalleVenta.setMonto_Neto(arraylineadetalle[11]);
 
 /* monto iva */
 objDetalleVenta.setMonto_IVA(arraylineadetalle[12]);
 
 /* monto total documento */
 objDetalleVenta.setMonto_Total(arraylineadetalle[13]);
    

 /* iva retenido total */
 objDetalleVenta.setIVA_Retenido_Total(arraylineadetalle[14]);

 /* iva retenido parcial */
 objDetalleVenta.setIVA_Retenido_Parcial(arraylineadetalle[15]);
 
 /* iva no retenido */
 objDetalleVenta.setIVA_no_retenido(arraylineadetalle[16]);
 
  /* iva propio */
 objDetalleVenta.setIVA_propio(arraylineadetalle[17]);
 
 /* iva terceros */
 objDetalleVenta.setIVA_Terceros(arraylineadetalle[18]);
 
 
 /* rut emisor liquidacion de factura */
 objDetalleVenta.setRUT_Emisor_Liquid_Factura(arraylineadetalle[19]);
 
 /* neto comision liquidacion de factura */
 objDetalleVenta.setNeto_Comision_Liquid_Factura(arraylineadetalle[20]);
 
 
 
 /* exento comision liquidacion de factura */
 objDetalleVenta.setExento_Comision_Liquid_Factura(arraylineadetalle[21]);
 
 /* iva comision liquidacion de factura */
 objDetalleVenta.setIVA_Comision_Liquid_Factura(arraylineadetalle[22]);
 
 /* iva fuera de plazo */
 objDetalleVenta.setIVA_fuera_de_plazo(arraylineadetalle[23]);
 
 /* tipo docto refereNcia */
 objDetalleVenta.setTipo_Docto_Referencia(arraylineadetalle[24]);

  
  /* FOLIO DOCTO REFERENCIA */
  objDetalleVenta.setFolio_Docto_Referencia(arraylineadetalle[25]);
  
  /* nro idenfitificacion receptor extranjero */
  objDetalleVenta.setNum_Ident_Receptor_Extranjero(arraylineadetalle[26]);
  
  /* nacionalidad receptor extranjero */
  objDetalleVenta.setNacionalidad_Receptor_Extranjero(arraylineadetalle[27]);
  
  /* credito empresa constructora */
  objDetalleVenta.setCredito_empresa_constructora(arraylineadetalle[28]);
  
  /* IMPTO ZONA FRANCA */ 
  objDetalleVenta.setImpto_Zona_Franca_Ley_1821(arraylineadetalle[29]);
  
  /* garanta impto envases */
  objDetalleVenta.setGarantia_Dep_Envases(arraylineadetalle[30]);
  
  /* indicador venta sin costo */
  objDetalleVenta.setIndicador_Venta_sin_Costo(arraylineadetalle[31]);
  
  
  /* indicar servicio periodico */
  objDetalleVenta.setIndicador_Servicio_Periodico(arraylineadetalle[32]);
  
  
  
  /* monto no facturable */
  objDetalleVenta.setMonto_No_facturable(arraylineadetalle[33]);
  
   
  /* TOTAL MONTO PERIODO */
  objDetalleVenta.setTotal_Monto_Periodo(arraylineadetalle[34]);
  
  
  /* venta pasaje trasnporte nacional */
  objDetalleVenta.setVenta_Pasajes_Transporte_Nacional(arraylineadetalle[35]);
  
  
  /* venta pasaje transporte internacional */
  objDetalleVenta.setVenta_Pasajes_Transporte_Nacional(arraylineadetalle[36]);
  
  /* numero interno */ 
  objDetalleVenta.setNumero_Interno(arraylineadetalle[37]);
  
  /* Código Sucursal */
  objDetalleVenta.setCodigo_Sucursal(arraylineadetalle[38]);
  
  
  /* nota de credito sobre factura de compra */
  /*
  objDetalleVenta.setNCE_o_NDE_sobre_Fact_de_Compra(arraylineadetalle[39]);
  */

  /* codigo otro impto */
  /*
  objDetalleVenta.setCodigo_Otro_Imp(arraylineadetalle[40]);
  *
  /* valor otro impto */
  /*
  objDetalleVenta.setCodigo_Otro_Imp(arraylineadetalle[41]);
  */
  
  /* tasa otro impto */
  /*
  objDetalleVenta.setCodigo_Otro_Imp(arraylineadetalle[42]);
  */
    arraydetalleventa.add(objDetalleVenta);
  }      
return arraydetalleventa;
}
    
}
