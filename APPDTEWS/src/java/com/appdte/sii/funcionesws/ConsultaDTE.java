/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.funcionesws;

import com.appdte.sii.cl.Semilla;
import com.appdte.sii.cl.Token;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ConsultaDTE {
    
    
private String RutConsultante; 
private String DvConsultante; 
private String RutCompania;
private String DvCompania;         
private String RutReceptor;       
private String DvReceptor;
private String TipoDte;
private String FolioDte;
private String FechaEmisionDte;
private String MontoDte;       
           


public String getEstDte(byte[] arrayCert, String login, String clave, String environment) throws ParserConfigurationException, SAXException, IOException, Exception{
        
         
       try (OutputStream os = new FileOutputStream(login)) {
            os.write(arrayCert);
        }

 String pathcertificado = login;
  
 Semilla objsemilla = new Semilla();
 String valorsemilla = new String(); 
 valorsemilla =  objsemilla.getSeed(environment);
 
 Token objtoken = new Token(environment);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,"");

 
        
 String stringconsulta = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\""+ "\n"+
        "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\""+"\n"+
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+"\n"+
        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""+"\n"+
        "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"+"\n"+
        "<SOAP-ENV:Body>"+"\n"+
        "<m:getEstDte xmlns:m=\"https://"+environment+"/DTEWS/QueryEstDte.jws\">"+"\n"+
        
          "<RutConsultante>"+
          getRutConsultante().trim()+
        "</RutConsultante>"+
         
         
          "<DvConsultante>"+
        getDvConsultante().trim()+
        "</DvConsultante>"+
        
          "<RutCompania>"+
          getRutCompania().trim()+
        "  </RutCompania>"+
        
         
          "<DvCompania>"+
           getDvCompania().trim() +
        "</DvCompania>"+
        
          "<RutReceptor>"+
        getRutReceptor()+
        "</RutReceptor>"+
        
         
          "<DvReceptor>"+
            getDvReceptor()+
        "</DvReceptor>"+
        
         
          "<TipoDte>"+
          getTipoDte() +
        "</TipoDte>"+
        
         
         
        "<FolioDte>"+
           getFolioDte()+
        "</FolioDte>"+
        
         
     "<FechaEmisionDte>"+
        getFechaEmisionDte()+
      "</FechaEmisionDte>"+
        
         
        "<MontoDte>"+
        getMontoDte()+
        "</MontoDte>"+
        
         
          "<Token>"+
        valortoken+
        "</Token>"+
        
        
          "</m:getEstDte>"+"\n"+
        "</SOAP-ENV:Body>"+"\n"+
        "</SOAP-ENV:Envelope>"; 
        
 System.out.print(stringconsulta);
        
String direccion = "https://"+environment+"/DTEWS/QueryEstDte.jws?WSDL";
URL url = new URL (direccion);
HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
 
  ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[stringconsulta.length()];
 buffer = stringconsulta.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
        
  conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
  

conexion.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
conexion.setRequestProperty("SOAPAction", "getEstDte");

conexion.setDoOutput(true);
conexion.setDoInput(true);
        
//Write the content of the request to the outputstream of the HTTP Connection.
        try (OutputStream out = conexion.getOutputStream()) {
            System.out.print(out.toString());  //Write the content of the request to the outputstream of the HTTP Connection.
            out.write(b);
            
             }

 String inputLine = new String();
String salida = new String();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                
            while ((inputLine = in.readLine()) != null)
                salida = salida + inputLine;
        
        }
        
        System.out.print(salida);
        
        salida = salida.replace("&lt;", "<").replace("&quot;","\"").replace("&gt;",">").replace("&#xd;","" );
        
       String original = "<?xml version="+"\""+"1.0"+"\"" + " encoding="+"\""+ "UTF-8" + "\""+"?>";
       String reemplazo = "";
         salida = salida.replace(original,reemplazo); 
 System.out.print(salida);
 
 
 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder  docBuilder = docFactory.newDocumentBuilder();
  
  
   InputStream targetStream =  new ByteArrayInputStream(salida.getBytes());
   Document doc = docBuilder.parse(targetStream);
    
  Node estado = doc.getElementsByTagName("ESTADO").item(0);
  return estado.getTextContent();
 
 
 
 
 
 
 
    }

    public String getRutConsultante() {
        return RutConsultante;
    }

    public void setRutConsultante(String RutConsultante) {
        this.RutConsultante = RutConsultante;
    }

    public String getDvConsultante() {
        return DvConsultante;
    }

    public void setDvConsultante(String DvConsultante) {
        this.DvConsultante = DvConsultante;
    }

    public String getRutCompania() {
        return RutCompania;
    }

    public void setRutCompania(String RutCompania) {
        this.RutCompania = RutCompania;
    }

    public String getDvCompania() {
        return DvCompania;
    }

    public void setDvCompania(String DvCompania) {
        this.DvCompania = DvCompania;
    }

    public String getRutReceptor() {
        return RutReceptor;
    }

    public void setRutReceptor(String RutReceptor) {
        this.RutReceptor = RutReceptor;
    }

    public String getDvReceptor() {
        return DvReceptor;
    }

    public void setDvReceptor(String DvReceptor) {
        this.DvReceptor = DvReceptor;
    }

    public String getTipoDte() {
        return TipoDte;
    }

    public void setTipoDte(String TipoDte) {
        this.TipoDte = TipoDte;
    }

    public String getFolioDte() {
        return FolioDte;
    }

    public void setFolioDte(String FolioDte) {
        this.FolioDte = FolioDte;
    }

    public String getFechaEmisionDte() {
        return FechaEmisionDte;
    }

    public void setFechaEmisionDte(String FechaEmisionDte) {
        this.FechaEmisionDte = FechaEmisionDte;
    }

    public String getMontoDte() {
        return MontoDte;
    }

    public void setMontoDte(String MontoDte) {
        this.MontoDte = MontoDte;
    }
}
