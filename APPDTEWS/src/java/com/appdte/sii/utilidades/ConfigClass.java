/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ConfigClass {
    private final String pathcert;
    private final String pathpdf;
     private final String pathcaf;
    private final String pathdata;
    private final String pathdte;
    private final String pathimg;
    private final String pathenvironment;
    private final String pathtemplate;
    private final String filepath;
    private final String pathdownload;
    private final String serverauth;
    private final String serveracceptdte;
            
    
    public ConfigClass() throws ParserConfigurationException, SAXException, IOException{
       
        
       
       filepath = "/home/esteban/appdte/appdteconf.xml";
	
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(filepath);
         
        NodeList nlpathcert = doc.getElementsByTagName("path-certificate");
        Element elpathcert = (Element) nlpathcert.item(0);
        this.pathcert = elpathcert.getFirstChild().getNodeValue();
         
         
         
         
         
         NodeList nodepathpdf = doc.getElementsByTagName("path-pdf");
         Element elpathpdf = (Element) nodepathpdf.item(0);
         this.pathpdf = elpathpdf.getFirstChild().getNodeValue();
        
         
         NodeList nodepathcaf = doc.getElementsByTagName("path-caf");
         Element elpathcaf = (Element) nodepathcaf.item(0);
         this.pathcaf = elpathcaf.getFirstChild().getNodeValue();
         
         NodeList nodepathdata =  doc.getElementsByTagName("path-data");
         Element elpathdata = (Element) nodepathdata.item(0);
         this.pathdata = elpathdata.getFirstChild().getNodeValue(); 
         
          NodeList nodepathdte =  doc.getElementsByTagName("path-DTE");
          Element elpathdte = (Element) nodepathdte.item(0);
          this.pathdte = elpathdte.getFirstChild().getNodeValue();
         
          NodeList nodepathenvironment = doc.getElementsByTagName("environment-url");
          Element elpathenvironment =  (Element) nodepathenvironment.item(0);
          this.pathenvironment = elpathenvironment.getFirstChild().getNodeValue();
          
          
          NodeList nodepathimg =  doc.getElementsByTagName("path-img");
          Element elpathimg =  (Element) nodepathimg.item(0);  
          this.pathimg = elpathimg.getFirstChild().getNodeValue();
          

          NodeList nodepathtemplate =  doc.getElementsByTagName("path-template");
          Element elpathtemplate =  (Element) nodepathtemplate.item(0);  
          this.pathtemplate = elpathtemplate.getFirstChild().getNodeValue();  


          
          NodeList nodepathdownload =  doc.getElementsByTagName("path-download");
          Element elpathdownload =  (Element) nodepathdownload.item(0);  
          this.pathdownload = elpathdownload.getFirstChild().getNodeValue();  

          
          
          
          
         
         NodeList nodeserverauth =  doc.getElementsByTagName("server-auth");
         Element elserverauth =  (Element) nodeserverauth.item(0);  
         this.serverauth = elserverauth.getFirstChild().getNodeValue();
         
         
         NodeList nodeserveracceptdte =  doc.getElementsByTagName("server-acceptdte");
         Element elserveracceptdte =  (Element) nodeserveracceptdte.item(0);  
         this.serveracceptdte = elserveracceptdte.getFirstChild().getNodeValue();
         

}

    public String getPathcert() {
        return pathcert;
    }

    public String getPathpdf() {
        return pathpdf;
    }


    public String getPathcaf() {
        return pathcaf;
    }

    public String getPathdata() {
        return pathdata;
    }

    public String getPathdte() {
        return pathdte;
    }

    public String getPathimg() {
        return pathimg;
    }

    public String getPathenvironment() {
        return pathenvironment;
    }
    
    
    public String getPathtemplate(){
        
        return pathtemplate;
    }
    
    
    
    public String getPathdownload(){
        
        return pathdownload;
    }

    public String getServerauth() {
        return serverauth;
    }

    public String getServeracceptdte() {
        return serveracceptdte;
    }
    
    
    
}

