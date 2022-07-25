/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ConfigAppDTE {
    private final String pathcert;
    private final String pathpdf;
    private final String pathcaf;
    private final String pathdata;
    private final String pathdte;
    private final String pathimg;
    private final String pathenvironment;
    private final String pathtemplate;
    
    private final String pathdownload;
    private final String serverauth;
    private final String serveracceptdte;
            
    
    public ConfigAppDTE() throws ParserConfigurationException, SAXException, IOException{
       
        
        Properties prop = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/appdtews/properties/appdtews.properties")) {
            prop.load(in);
       
          this.pathcert = prop.getProperty("path-certificate");
          this.pathpdf = prop.getProperty("path-pdf");
          this.pathcaf = prop.getProperty("path-caf");
          this.pathdata = prop.getProperty("path-data");
          this.pathdte = prop.getProperty("path-DTE");
          this.pathenvironment = prop.getProperty("environment-url");
          this.pathimg = prop.getProperty("path-img");
          this.pathtemplate =  prop.getProperty("path-template");
          this.pathdownload = prop.getProperty("path-download");
           
          this.serverauth = prop.getProperty("server-auth");
          this.serveracceptdte = prop.getProperty("server-acceptdte");
        }
        
      

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

