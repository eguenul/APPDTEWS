/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 *
 * @author esteban
 */
public class Token {
     String urlenvironment;
    public Token(String environment){
        
        this.urlenvironment = environment;
    }
    
       public String getToken(String valorsemilla,String pathcertificado,String clave,String nombredte) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, Exception{
      
           
        valorsemilla = valorsemilla.replaceFirst ("^0*", "");
        Long cadenaResultadoInt = Long.parseLong(valorsemilla);
           
        String valuesemilla = Long.toString(cadenaResultadoInt);
          
        
       
          
        
       DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.newDocument();
         
         Element gettoken = doc.createElement("getToken");
         Element item = doc.createElement("item");
         
        
         Node semilla = doc.createElement("Semilla");
         semilla.setTextContent(valuesemilla);
         item.appendChild(semilla);
         
         gettoken.appendChild(item);
         doc.appendChild(gettoken);
        
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
	 DOMSource source = new DOMSource(doc);
         
         StringWriter writer = new StringWriter();
         
	 StreamResult result = new StreamResult(writer);
	
         transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
          transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
          transformer.transform(source, result);
	  System.out.println("Done");
           
          
          
        SignToken objfirma = new SignToken();
        StringWriter writer2 =  objfirma.signToken(writer, pathcertificado,clave);
           
           
           
           
        String valortoken = requestToken(writer2, this.urlenvironment);
           
           
           
           
           
           return valortoken;
           
           
 
       } 
    
    
    public String requestToken(StringWriter writer, String urlauth) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException{
        
         String contenidosemilla = "";
           
          
         
       contenidosemilla = contenidosemilla + writer.toString() + "\n" ;
         
         String original1 = "<?xml version="+"\""+"1.0"+"\"" +"?>";
       String reemplazo1 = "";
       
    contenidosemilla = contenidosemilla.replace(original1,reemplazo1); 
       System.out.print(contenidosemilla);
       
      
   String direccion="https://"+urlauth+"/DTEWS/GetTokenFromSeed.jws?WSDL";
   
    URL url = new URL (direccion);
    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
        

                /* comienzo a armar la peticion soap para procesarla con wget */
                String inputsoap =   

        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\""+ "\n"+
        "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\""+"\n"+
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+"\n"+
        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""+"\n"+
        "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"+"\n"+
        "<SOAP-ENV:Body>"+"\n"+
        "<m:getToken xmlns:m=\"https://"+urlauth+"/DTEWS/GetTokenFromSeed.jws\">"+"\n"+
        "<pszXml xsi:type=\"xsd:string\">"+"\n"+
        "<![CDATA["+"\n"+contenidosemilla+"]]>"+
        "</pszXml>"+"\n"+
        "</m:getToken>"+"\n"+
        "</SOAP-ENV:Body>"+"\n"+
        "</SOAP-ENV:Envelope>";
                
                
                
 ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[inputsoap.length()];
 buffer = inputsoap.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
        
  conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
  
conexion.setRequestProperty("Access-Control-Allow-Credentials" ,"true");
conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
conexion.setRequestProperty("SOAPAction", "getToken");

conexion.setDoOutput(true);
conexion.setDoInput(true);
        
//Write the content of the request to the outputstream of the HTTP Connection.
        try (OutputStream out = conexion.getOutputStream()) {
            //Write the content of the request to the outputstream of the HTTP Connection.
            out.write(b);
             }

 String inputLine = new String();
String salida = new String();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
                
            while ((inputLine = in.readLine()) != null)
                salida = salida + inputLine;
        
        }
        
        System.out.print(salida);
        
        salida = salida.replace("&lt;", "<").replace("&quot;","\"").replace("&gt;",">");
        
       String original = "<?xml version="+"\""+"1.0"+"\"" + " encoding="+"\""+ "UTF-8" + "\""+"?>";
       String reemplazo = "";
       
    salida = salida.replace(original,reemplazo); 
 
       String  valortoken = readFileToken(salida);
       return valortoken;       
}
    
public String readFileToken(String cadena) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException{        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        System.out.print(cadena);
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(cadena)));
     
      
        NodeList nl = doc.getElementsByTagName("TOKEN");
        Element el = (Element) nl.item(0);
        String valortoken = el.getFirstChild().getNodeValue();             
        return valortoken;
    }      
}
        
 


    
       
    
    
    

