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
public class ConsultaCesion {
    
    
    private String RutEmisor;
    private String DVEmisor;
    private String TipoDoc;
    private String FolioDoc;
    private String RutEmpresa;
    private String DVEmpresa;

public String getEstEnvioCesion(byte[] arrayCert, String login, String clave,String trackid, String environment) throws ParserConfigurationException, SAXException, IOException, Exception{
        
       try (OutputStream os = new FileOutputStream(login)) {
            os.write(arrayCert);
        }
 
 String pathcertificado = login;
  
 Semilla objsemilla = new Semilla();
 
String valorsemilla =  objsemilla.getSeed(environment);
 
 Token objtoken = new Token(environment);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,"");

 
        
 String stringconsulta = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\""+ "\n"+
        "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\""+"\n"+
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+"\n"+
        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""+"\n"+
        "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"+"\n"+
        "<SOAP-ENV:Body>"+"\n"+
        "<m:getEstEnvio xmlns:m=\"https://"+environment+"/DTEWS/services/wsRPETCConsulta\">"+"\n"+   
        "<Token>"+
        valortoken+
        "</Token>"+
          "<TrackId>"+
           trackid +
        "</TrackId>"+
        "</m:getEstEnvio>"+"\n"+
        "</SOAP-ENV:Body>"+"\n"+
        "</SOAP-ENV:Envelope>"; 
        
 System.out.print(stringconsulta);
        
String direccion = "https://"+environment+"/DTEWS/services/wsRPETCConsulta?wsdl";
URL url = new URL (direccion);
HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
 
  ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[stringconsulta.length()];
 buffer = stringconsulta.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
        
  conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
  
conexion.setRequestProperty("Access-Control-Allow-Credentials" ,"true");
conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
conexion.setRequestProperty("SOAPAction", "getEstEnvio");

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
    
  Node estadoCESION = doc.getElementsByTagName("ESTADO_ENVIO").item(0);
  return estadoCESION.getTextContent();
 
 
 
 
 
 
    }
    
    
public String getEstCesionRelac(String login, String clave, String environment) throws ParserConfigurationException, SAXException, IOException, Exception{
        

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
        "<m:getEstCesionRelac xmlns:m=\"http://DefaultNamespace\">"+"\n"+   
        "<Token>" + valortoken +  "</Token>"+
         "<RutEmisor>" + getRutEmisor()+ "</RutEmisor>"+
         "<DVEmisor>" + getDVEmisor() +  "</DVEmisor>"+
         "<TipoDoc>"+ getTipoDoc()   + "</TipoDoc>"+
         "<FolioDoc>"+ getFolioDoc() +"</FolioDoc>"+
         "<RutEmpresa>"+ getRutEmpresa()+ "</RutEmpresa>"+
         "<DVEmpresa>"+ getDVEmpresa() +"</DVEmpresa>"+
        "</m:getEstCesionRelac>"+"\n"+
        "</SOAP-ENV:Body>"+"\n"+
        "</SOAP-ENV:Envelope>"; 
        
 System.out.print(stringconsulta);
        
String direccion = "https://"+environment+"/DTEWS/services/wsRPETCConsulta?wsdl";
URL url = new URL (direccion);
HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
 
  ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[stringconsulta.length()];
 buffer = stringconsulta.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
        
  conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
  
conexion.setRequestProperty("Access-Control-Allow-Credentials" ,"true");
conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
conexion.setRequestProperty("SOAPAction", "getEstCesionRelac");

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
    
  Node estadoCESION = doc.getElementsByTagName("SII:ESTADO").item(0);
  return estadoCESION.getTextContent();
    }


    public String getRutEmisor() {
        return RutEmisor;
    }

    public void setRutEmisor(String RutEmisor) {
        this.RutEmisor = RutEmisor;
    }

    public String getDVEmisor() {
        return DVEmisor;
    }

    public void setDVEmisor(String DVEmisor) {
        this.DVEmisor = DVEmisor;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }

    public String getFolioDoc() {
        return FolioDoc;
    }

    public void setFolioDoc(String FolioDoc) {
        this.FolioDoc = FolioDoc;
    }

    public String getRutEmpresa() {
        return RutEmpresa;
    }

    public void setRutEmpresa(String RutEmpresa) {
        this.RutEmpresa = RutEmpresa;
    }

    public String getDVEmpresa() {
        return DVEmpresa;
    }

    public void setDVEmpresa(String DVEmpresa) {
        this.DVEmpresa = DVEmpresa;
    }
    
    
    
}
