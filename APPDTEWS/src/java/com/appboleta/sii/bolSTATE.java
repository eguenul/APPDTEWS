/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appboleta.sii.TokenBOLETA;
import com.appboleta.sii.seedBOLETA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class bolSTATE {
 
public String getESTATE(String certificado, String password, String rutemisor,String dvemisor,String trackid) throws IOException, MalformedURLException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, TransformerConfigurationException, Exception{
        
    seedBOLETA  objSemilla = new seedBOLETA();
    TokenBOLETA objToken = new TokenBOLETA();
    String valorsemilla = objSemilla.getSeed();
    String  stringToken = objToken.getToken(valorsemilla, certificado, password);
   
    return getSIIESTATE(stringToken,  rutemisor,  dvemisor,  trackid);
}


public String getSIIESTATE(String valortoken, String rutemisor, String dvemisor,String trackid) throws IOException{
    
      try {
          
          StringBuilder result = new StringBuilder();
          
          URL url = new URL("https://apicert.sii.cl/recursos/v1/boleta.electronica.envio/"+rutemisor+"-"+dvemisor+"-"+trackid);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         
          conn.setRequestMethod("GET");
          
           conn.setRequestProperty("Cookie","TOKEN="+valortoken);
       try (BufferedReader reader = new BufferedReader(
                  new InputStreamReader(conn.getInputStream()))) {
              for (String line; (line = reader.readLine()) != null; ) {
                  result.append(line);
              }
              return result.toString();
          }
          
          
          
      }   catch (MalformedURLException ex) {
        Logger.getLogger(bolSTATE.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
     
    
    
}
    
}
