/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.IOException;
import java.io.StringWriter;
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
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class getTED {
    
 public String  getTED(String rutemisor,String foliodte, String codsii) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException{
        
       
       ConfigAppDTE objConfig = new ConfigAppDTE();
       String nombredte = objConfig.getPathdte()+"DTE"+rutemisor.trim()+"F"+foliodte+"T"+codsii;
      
           String filepath = nombredte.trim()+".xml";
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        System.out.print(filepath);
        Document doc = docBuilder.parse(filepath.trim());
            Node ted = doc.getElementsByTagName("TED").item(0); 
    
         
        
         StringWriter buf = new StringWriter();
        Transformer xform = TransformerFactory.newInstance().newTransformer();
        xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                  
        xform.transform(new DOMSource(ted), new StreamResult(buf));
        String timbre;
        timbre = buf.toString();
        return timbre;
   }
    
}
