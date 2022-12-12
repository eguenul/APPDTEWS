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
public class ConsultaAceptacion {
    
 private String rutEmisor;
 private String dvEmisor;
 private String tipoDoc;
 private String folio;
     
public String  consultarDocDteCedible(byte[] arrayCert, String login, String clave, String environment, String environmentacceptdte) throws ParserConfigurationException, SAXException, IOException, Exception{    


String ambiente = "https://"+ environmentacceptdte +"/WSREGISTRORECLAMODTECERT/registroreclamodteservice";

try (OutputStream os = new FileOutputStream(login)) {
            os.write(arrayCert);
        }
        



 String pathcertificado = login;
  
 Semilla objsemilla = new Semilla();
 String valorsemilla = objsemilla.getSeed(environment);
 
 Token objtoken = new Token(environment);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,"");

 
        
 String stringconsulta = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"+ "\n"+
        "<SOAP-ENV:Body>"+"\n"+
        "<ws:consultarDocDteCedible xmlns:ws=\"http://ws.registroreclamodte.diii.sdi.sii.cl\">"+ "\n"+
          "<rutEmisor>"+getRutEmisor()+"</rutEmisor>"+ "\n"+
         "<dvEmisor>"+getDvEmisor()+"</dvEmisor>"+ "\n"+
         "<tipoDoc>"+getTipoDoc()+"</tipoDoc>"+ "\n"+
        "<folio>"+getFolio()+"</folio>"+ "\n"+
          "</ws:consultarDocDteCedible>"+"\n"+
        "</SOAP-ENV:Body>"+"\n"+
        "</SOAP-ENV:Envelope>"; 
        
 System.out.print(stringconsulta);
        

URL url = new URL (ambiente);
HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
 
  ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[stringconsulta.length()];
 buffer = stringconsulta.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
conexion.setRequestMethod("POST");
conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
conexion.setRequestProperty("Content-Type", "application/xml");
conexion.setRequestProperty("Cookie","TOKEN="+valortoken);

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
 
 
 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder  docBuilder = docFactory.newDocumentBuilder();
  
  
   InputStream targetStream =  new ByteArrayInputStream(salida.getBytes());
   Document doc = docBuilder.parse(targetStream);
    
  Node estado = doc.getElementsByTagName("codResp").item(0);
  return estado.getTextContent();
 
 
 
 
 
 
 
    }

    public String getRutEmisor() {
        return rutEmisor;
    }

    public void setRutEmisor(String rutEmisor) {
        this.rutEmisor = rutEmisor;
    }

    public String getDvEmisor() {
        return dvEmisor;
    }

    public void setDvEmisor(String rvEmisor) {
        this.dvEmisor = rvEmisor;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }




    
}   
    

