package com.appdte.sii.cl;



import javax.xml.parsers.DocumentBuilder;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.net.*;

public class Semilla {
    
   
    
public  String getSeed(String urlenvironment) throws  Exception {
 
    try{
    String direccion = "https://"+urlenvironment+"/DTEWS/CrSeed.jws?WSDL";
  
   
   
    URL url = new URL (direccion);
    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
   
    
        
  String inputsoap = "<soapenv:Envelope xmlns:soapenv=" + "\""+ "http://schemas.xmlsoap.org/soap/envelope/" + "\""+">"+
               "<soapenv:Header/>"+
               "<soapenv:Body>"+
               "<getSeed></getSeed>"+
              "</soapenv:Body>"+
              "</soapenv:Envelope>";
 ByteArrayOutputStream bout = new ByteArrayOutputStream();
 byte[] buffer = new byte[inputsoap.length()];
 buffer = inputsoap.getBytes();
 bout.write(buffer);
 byte[] b = bout.toByteArray();
        
  conexion.setRequestProperty("Content-Length",String.valueOf(b.length));
  
conexion.setRequestProperty("Access-Control-Allow-Credentials" ,"true");
conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
conexion.setRequestProperty("SOAPAction", "getSeed");

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
        
        salida = salida.replace("&lt;", "<").replace("&quot;","\"").replace("&gt;",">");
        
       String original = "<?xml version="+"\""+"1.0"+"\"" + " encoding="+"\""+ "UTF-8" + "\""+"?>";
       String reemplazo = "";
       
    salida = salida.replace(original,reemplazo); 
    System.out.print(salida);
    String valorsemilla;
    valorsemilla =  readSeed(salida);
    return valorsemilla;          
    
    
    }catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException EX){
        
        return "0";
    }
}
    
      

public  String readSeed(String request) throws ParserConfigurationException, SAXException, IOException,XPathExpressionException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(request)));                       
        NodeList nl = doc.getElementsByTagName("SEMILLA");
        Element el = (Element) nl.item(0);
        String valorsemilla = el.getFirstChild().getNodeValue();         
        return valorsemilla;      
}
    
    
    


}
























     
        
        
       
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 