/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.appboleta.xml.LibroBoletaXML;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class mainLibroBoleta {
    
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException{
         LibroBoletaXML objlibro = new LibroBoletaXML();
     
         
         objlibro.generaXML(cargaJSON());
         
     }
    
    
  public static String cargaJSON() throws FileNotFoundException, IOException{
   String cadena="";
   String cadena2 = "";
        FileReader f = new FileReader("/home/esteban/appdte/JSON/libroboleta.json");
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
              cadena2 = cadena2+cadena; 
            }
        }
     return cadena2;
}
    
    
    
    
    
    
    
    
}
