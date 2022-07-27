/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author esteban
 */
public class seedBOLETA {
    
 public String getSeed() throws MalformedURLException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
/* URL url = new URL("https://apicert.sii.cl/recursos/v1/boleta.electronica.semilla");
 */
   StringBuilder result = new StringBuilder();

 URL url = new URL("https://apicert.sii.cl/recursos/v1/boleta.electronica.semilla");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      try (BufferedReader reader = new BufferedReader(
                  new InputStreamReader(conn.getInputStream()))) {
          for (String line; (line = reader.readLine()) != null; ) {
              result.append(line);
          }
        return readSeed(result.toString());
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
    
    

