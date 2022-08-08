/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.sii.utilidades.ConfigAppDTE;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class UpBOLETASII {
    private final String urlenvironment;
    
    public UpBOLETASII(String urlenvironment){
       this.urlenvironment = urlenvironment;
        
    }
 
    public String upBOLETA(String valortoken,String nombredte ,String rutemisor,String rutusuario) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{

         String[] arrayrutemisor = rutemisor.split("-");
         String[] arrayrutusuario = rutusuario.split("-");
         
         
         
         ConfigAppDTE objconfig = new ConfigAppDTE();
         
        URL url = new URL("https://pangal.sii.cl/recursos/v1/boleta.electronica.envio");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=9022632e1130lc4"); 
        conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; PROG 1.0; Windows NT 5.0; YComp 5.0.2.4)"); 
        conn.setRequestProperty("Cookie","TOKEN="+valortoken);
        
        
        String archivo = objconfig.getPathdte()+nombredte +".xml";
        conn.setUseCaches(false);
        String cadena = "";
        String contenido = "";
        
        FileReader f = new FileReader(archivo);
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                contenido = contenido + cadena + "\r\n";
            }
        }
      
           
           /* cuerpo de la peticion request */
   
  String stringRequest = "";
stringRequest =  "--9022632e1130lc4"+"\r\n"+
        "Content-Disposition: form-data;"+" name=" +"\""+"rutSender"+"\""+"\r\n"+
        "\r\n"+arrayrutusuario[0]+"\r\n"+
        "--9022632e1130lc4"+"\r\n"+
        "Content-Disposition: form-data; name="+ "\"" +"dvSender" + "\""+"\r\n"+
        "\r\n"+arrayrutusuario[1]+"\r\n"+
        "--9022632e1130lc4"+"\r\n"+
        "Content-Disposition: form-data; name="+ "\""+ "rutCompany" + "\"" + 
        "\r\n"+ "\r\n"+ arrayrutemisor[0]+"\r\n"+
        "--9022632e1130lc4" + "\r\n"+
        "Content-Disposition: form-data; name=" + "\""+"dvCompany" + "\"" +      
        "\r\n"+ "\r\n" +
        arrayrutemisor[1]+"\r\n"+ 
        "--9022632e1130lc4"+"\r\n"+
        "Content-Disposition: form-data; name=" + "\r\n"+"archivo" + "\""+ ";filename="+"\""+archivo+"\"" + "\r\n"+
         "Content-Type: application/octet-stream"+"\r\n"+
        "Content-Transfer-Encoding: binary"+"\r\n"+
         "\r\n"+
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+"\r\n"+
         contenido+"\r\n"+"\r\n"+  "--9022632e1130lc4--";
        

System.out.print(stringRequest);


 OutputStream outputStreamToRequestBody = conn.getOutputStream();
        BufferedWriter httpRequestBodyWriter =
                new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
                httpRequestBodyWriter.write(stringRequest);
                httpRequestBodyWriter.flush();
 


     
String targetString = "";
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read())
            targetString +=   (char) c;
   
      /* */
      
      /*
        File fichero = new File(archivo);   
        
if(fichero.delete()){
   System.out.println("El fichero ha sido borrado satisfactoriamente");
}else{
   System.out.println("El fichero no puede ser borrado");
        
}    
        */
      System.out.print(targetString);
      return   targetString;
           }
       
    
    
    
}