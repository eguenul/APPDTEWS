/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class FuncionesCAF {
    
    
   public boolean validaCAF(String pathcaf, String rutempresa, int codsii, int nrofolio) throws ParserConfigurationException, SAXException, IOException{   
        String[] arrayrutempresa = rutempresa.split("-"); 
        String filepath = pathcaf+"F"+arrayrutempresa[0]+"T" + String.valueOf(codsii)+".xml";
	
 
 FileInputStream archivodte =new FileInputStream(filepath);
 InputStreamReader inputcaf = new InputStreamReader(archivodte,"ISO-8859-1");
 InputSource sourcecaf = new InputSource(inputcaf);      


         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
/*	 
 InputStream in = new FileInputStream(filepath);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLStreamReader parser = factory.createXMLStreamReader(in,"ISO-8859-1");
*/



Document doc = docBuilder.parse(sourcecaf);







        
         Element elrutempresa = (Element) doc.getElementsByTagName("RE").item(0);
         
         Element elrangodesde = (Element) doc.getElementsByTagName("D").item(0);
         
         Element elrangohasta = (Element) doc.getElementsByTagName("H").item(0);
         
          Element eltipodte = (Element) doc.getElementsByTagName("TD").item(0);
        
         
         
         String strrangodesde = elrangodesde.getTextContent();
         
         String strrangohasta = elrangohasta.getTextContent();
         
         
         int rangodesde = Integer.parseInt(strrangodesde);
         int rangohasta = Integer.parseInt(strrangohasta);
         
         String strtipodte = eltipodte.getTextContent();
       
         int tipodte = Integer.parseInt(strtipodte);
         
          if (nrofolio<rangodesde){
             
             return false;
         }
         
           if (nrofolio>rangohasta){
             
             return false;
         }
         
           if(tipodte!=codsii){
               
               return false;
           }
        
           
           
           
           
      return (rutempresa.trim().equals(elrutempresa.getTextContent().trim())) != false;   
       
   }    
         
         
         
         
         
     }    
     
       

   
    
    

