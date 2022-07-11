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
public class ICVCompra {
    
    

    
public String obtieneCompras(String login, String clave,String rutempresa,String periodo, String serverauth) throws IOException, ParserConfigurationException, SAXException, Exception{
 String[] arrayrutempresa = rutempresa.trim().split("-");
 

 String pathcertificado = login;
  
 Semilla objsemilla = new Semilla();
 
 String valorsemilla =  objsemilla.getSeed(serverauth);
 
 Token objtoken = new Token(serverauth);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,"");

    
             
        URL url = new URL("https://www4.sii.cl/consdcvinternetui/services/data/facadeService/getDetalleCompraExport");
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
"\"namespace\""+ ":" + "\"cl.sii.sdi.lob.diii.consdcv.data.api.interfaces.FacadeService/getDetalleCompraExport\"" + ","+ 
 "\"transactionId\"" + ":\""+  "0"+ "\""+
 "} "+
","+"\"data\":"+
" { "+
 "\"rutEmisor\""+ ":\""+  arrayrutempresa[0]   +"\","+ 
 "\"dvEmisor\"" + ":\"" +  arrayrutempresa[1]    +"\","+ 
"\"ptributario\""+ ":\"" + periodo +"\"," +
"\"estadoContab\"" + ":" + "\"REGISTRO\"" +"," +
"\"codTipoDoc\""+":\"" +"0"+"\"," +
"\"operacion\"" + ":" + "\"COMPRA\"" +
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
    
   

public ArrayList<DetalleCompra> formatCSV(String stringCSV) throws IOException{
 ArrayList<DetalleCompra>  arraydetallecompra = new ArrayList<>();
  
   String headerCSV=  "null{" + "\"data\"" + ":[\"Nro;" 
    + "Tipo Doc;"
    + "Tipo Compra;"
    + "RUT Proveedor;"
    + "Razon Social;"
    + "Folio;"
    + "Fecha Docto;"
    + "Fecha Recepcion;Fecha Acuse;Monto Exento;Monto Neto;"
    + "Monto IVA Recuperable;"
    + "Monto Iva No Recuperable;"
    + "Codigo IVA No Rec.;"
    + "Monto Total;"
    + "Monto Neto Activo Fijo;"
    + "IVA Activo Fijo;"
    + "IVA uso Comun;"
    + "Impto. Sin Derecho a Credito;"
    + "IVA No Retenido;"
    + "Tabacos Puros;Tabacos Cigarrillos;"
    + "Tabacos Elaborados;"
    + "NCE o NDE sobre Fact. de Compra;"
    + "Codigo Otro Impuesto;"
    + "Valor Otro Impuesto;"
    + "Tasa Otro Impuesto\"";
    
   
    String headerCSV2=  "{" + "\"data\"" + ":[\"Nro;" 
    + "Tipo_Doc;"
    + "Tipo_Compra;"
    + "RUT_Proveedor;"
    + "Razon_Social;"
    + "Folio;"
    + "Fecha_Docto;"
    + "Fecha_Recepcion;Fecha_Acuse;Monto_Exento;Monto_Neto;"
    + "Monto_IVA_Recuperable;"
    + "Monto_Iva_No_Recuperable;"
    + "Codigo_IVA_No_Rec;"
    + "Monto_Total;"
    + "Monto_Neto_Activo_Fijo;"
    + "IVA_Activo_Fijo;"
    + "IVA_uso_Comun;"
    + "Impto_Sin_Derecho_a_Credito;"
    + "IVA_No_Retenido;"
    + "Tabacos_Puros;Tabacos_Cigarrillos;"
    + "Tabacos_Elaborados;"
    + "NCE_o_NDE_sobre_Fact_de_Compra;"
    + "Codigo_Otro_Impuesto;"
    + "Valor_Otro_Impuesto;"
    + "Tasa_Otro_Impuesto\"";
 String   stringCSV2 =  stringCSV.replace(headerCSV, headerCSV2);
System.out.print(stringCSV2);
 
    InputStream isjson = new ByteArrayInputStream(stringCSV2.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    Compra compra = gson.fromJson(br1, Compra.class);
    
        String[]  stringdetallecompra = compra.getData();
    int i;
   
   
for(i=1; i<stringdetallecompra.length; i++ ){
      
String[] arraylineadetalle =  stringdetallecompra[i].split(";");
System.out.print(arraylineadetalle[0] + " "+arraylineadetalle[1] + " " + arraylineadetalle[2] + " "+arraylineadetalle[3]+" "+arraylineadetalle[4] + " "+arraylineadetalle[5]+ " "+arraylineadetalle[6]+ " "+arraylineadetalle[9]+ " "+arraylineadetalle[10]+ " "+arraylineadetalle[12]+" "+arraylineadetalle[14]);
  DetalleCompra objDetalleCompra = new DetalleCompra();
  
  
  /* obtengo eltipo de documento */
  objDetalleCompra.setTipo_Doc(arraylineadetalle[1]);
  /* obtengo descripcion del tipo de compra  */
  objDetalleCompra.setTipo_Compra(arraylineadetalle[2]);
  
  /* obtengo rut del proveedor */
  objDetalleCompra.setRUT_Proveedor(arraylineadetalle[3]);
  
  
   /* obtengo razon social del proveedor */
  objDetalleCompra.setRazon_Social(arraylineadetalle[4]);
  
  
  
  /* OBTENGO FECHA DEL DOCUMENTO */
    objDetalleCompra.setFolio((arraylineadetalle[5]));
  /* OBTENGO FECHA DEL DOCUMENTO */
    objDetalleCompra.setFecha_Docto((arraylineadetalle[6]));

    /* monto exento del documento */
    
   objDetalleCompra.setMonto_Exento((arraylineadetalle[9]));
    
   /* monto iva neto */
   
   objDetalleCompra.setMonto_Neto(arraylineadetalle[10]);
   
   /* monto iva recuperable    */
   
   if(!"".equals(arraylineadetalle[11])){
       objDetalleCompra.setMonto_IVA_Recuperable(arraylineadetalle[11]);
   }else{
          objDetalleCompra.setMonto_IVA_Recuperable("0");
   }
    /* monto iva no recuperable */
   if(!"".equals(arraylineadetalle[12])){
   objDetalleCompra.setMonto_Iva_No_Recuperable(arraylineadetalle[12]);
   }else{
       
       objDetalleCompra.setMonto_Iva_No_Recuperable("0");
  
   }
   
   
     /* código iva no recuperable */
  if(!"".equals(arraylineadetalle[13])){
  objDetalleCompra.setCodigo_IVA_No_Rec(arraylineadetalle[13]);
  }else{
      objDetalleCompra.setCodigo_IVA_No_Rec("0");
  }
   
   
    
    /* monto total del documento */
    objDetalleCompra.setMonto_Total(arraylineadetalle[14]);

  

  
  /* monto neto activo fijo */
  if(!"".equals(arraylineadetalle[15])){
  objDetalleCompra.setMonto_Neto_Activo_Fijo(arraylineadetalle[15]);
  }else{
      objDetalleCompra.setMonto_Neto_Activo_Fijo("0");
  }
  
  
  /* iva activo fijo */
  if(!"".equals(arraylineadetalle[16])){
  objDetalleCompra.setIVA_Activo_Fijo(arraylineadetalle[16]);
  }else{
      objDetalleCompra.setIVA_Activo_Fijo("0");
  }
  
  
  /* iva activo uso comun */
  if(!"".equals(arraylineadetalle[17])){
  objDetalleCompra.setIVA_uso_Comun(arraylineadetalle[17]);
  }else{
      objDetalleCompra.setIVA_uso_Comun("0");
  }
  
  
  /* impto sin derecho a credito */
  if(!"".equals(arraylineadetalle[18])){
  objDetalleCompra.setImpto_Sin_Derecho_a_Credito(arraylineadetalle[18]);
  }else{
      objDetalleCompra.setImpto_Sin_Derecho_a_Credito("0");
  }
  
  
  
  /* impto sin derecho a credito */
  if(!"".equals(arraylineadetalle[19])){
  objDetalleCompra.setIVA_No_Retenido(arraylineadetalle[19]);
  }else{
      objDetalleCompra.setIVA_No_Retenido("0");
  }
  
  /* tabacos puro */
   if(!"".equals(arraylineadetalle[20])){
  objDetalleCompra.setTabacos_Puros(arraylineadetalle[20]);
  }else{
      objDetalleCompra.setTabacos_Puros("0");
  }


 /* tabacos cigarrillos */
if(!"".equals(arraylineadetalle[21])){
  objDetalleCompra.setTabacos_Cigarrillos(arraylineadetalle[21]);
}else{
      objDetalleCompra.setTabacos_Cigarrillos("0");
}



   
   
 /* tabacos cigarrillos */
if(!"".equals(arraylineadetalle[22])){
  objDetalleCompra.setTabacos_Elaborados(arraylineadetalle[22]);
}else{
      objDetalleCompra.setTabacos_Elaborados("0");
}
   

if(!"".equals(arraylineadetalle[23])){
  objDetalleCompra.setNCE_o_NDE_sobre_Fact_de_Compra(arraylineadetalle[23]);
}else{
      objDetalleCompra.setNCE_o_NDE_sobre_Fact_de_Compra("0");
}
   


try{
    
if(!"".equals(arraylineadetalle[24])){
  objDetalleCompra.setCodigo_Otro_Impuesto(arraylineadetalle[24]);
}else{
      objDetalleCompra.setCodigo_Otro_Impuesto("0");
}


if(!"".equals(arraylineadetalle[25])){
  objDetalleCompra.setValor_Otro_Impuesto(arraylineadetalle[25]);
}else{
      objDetalleCompra.setValor_Otro_Impuesto("0");
}


 

if(!"".equals(arraylineadetalle[26])){
  objDetalleCompra.setTasa_Otro_Impuesto(arraylineadetalle[26]);
}else{
      objDetalleCompra.setTasa_Otro_Impuesto("0");
}
}catch (Exception ex){
    
    
}



arraydetallecompra.add(objDetalleCompra);

}


     
return arraydetallecompra;
}

    
}
