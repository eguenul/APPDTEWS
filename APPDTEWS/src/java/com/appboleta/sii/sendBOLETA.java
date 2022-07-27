/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class sendBOLETA {
    
    
    public String sendBOLETA() throws IOException, MalformedURLException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, TransformerConfigurationException, Exception{
        
        
    seedBOLETA  objSemilla = new seedBOLETA();
    TokenBOLETA objToken = new TokenBOLETA();
    String valorsemilla = objSemilla.getSeed();
    String  stringToken = objToken.getToken(valorsemilla, "eguenul", "amulen1956");
    return stringToken;
        
    }
            
}
