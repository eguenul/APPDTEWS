/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.sii.cl.SignToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
public class TokenBOLETA {
    
       public String getToken(String valorsemilla,String pathcertificado,String clave) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, Exception{
      
           
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
           
           
           
           
           
           
           
         return requestToken(writer2);
           
           
 
       } 
       
       
       
   public String requestToken(StringWriter writer) throws MalformedURLException, IOException, FileNotFoundException, ParserConfigurationException, SAXException {
        
       
        URL url = new URL("https://apicert.sii.cl/recursos/v1/boleta.electronica.token");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("accept", "application/xml"); 
        conn.setRequestProperty("Content-Type", "application/xml"); 
    
       OutputStream outputStreamToRequestBody = conn.getOutputStream();
        BufferedWriter httpRequestBodyWriter =
                new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
                httpRequestBodyWriter.write(writer.toString());
                httpRequestBodyWriter.flush();
 


     
String targetString = "";
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read())
            targetString +=   (char) c;
       
       
       
       
              
      
    return readFileToken(targetString);       
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
