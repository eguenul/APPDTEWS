/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.appboleta.sii.AppBoleta;
import com.appdte.json.DteJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class mainBoleta {
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, Exception{
         /* EJEMPLO DE CARGA DE  DTE */
     
     /* CARGO EL CONTENIDO DESDE UN ARCHIVO JSON */
      String stringCaf="F76040308T33.xml";
      String loginuser="eguenul";
      String environment="maullin.sii.cl";
      String clave="amulen1956";
      String rutenvia="13968481-8";
          
      
      
     
     
     
     AppBoleta objdte = new AppBoleta(loginuser,  environment);
     
     String stringDTE = cargaJSON();
    
     
     
     /* System.out.print(stringDTE);
     */
     
   InputStream isjson = new ByteArrayInputStream(stringDTE.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    DteJson objdtejson = gson.fromJson(br1, DteJson.class);
 
       IdDteJson iddoc = objdtejson.getIdDte();
    
        
     EmisorJson objemisor = objdtejson.getEmisor();
   
     String rutemisor = objemisor.getRutemisor();
     String folio = iddoc.getNumDTE();
     
     String codsii = iddoc.getTipoDTE();
   objdte.generaBoleta(stringDTE, loginuser, clave, rutenvia, true);
   
        
    }
    
    
    public static String cargaJSON() throws FileNotFoundException, IOException{
   String cadena="";
   String cadena2 = "";
        FileReader f = new FileReader("/home/esteban/appdte/JSON/DTEBOLETA.json");
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
              cadena2 = cadena2+cadena; 
            }
        }
     return cadena2;
}
    
    
    
    
    
    
}
