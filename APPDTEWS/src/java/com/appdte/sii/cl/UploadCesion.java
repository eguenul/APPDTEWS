/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cl;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class UploadCesion {
    
    String urlenvironment;
    
    
    public UploadCesion(String urlenvironment){
       this.urlenvironment = urlenvironment;
        
    }
    
    public String[] sendCesion(String valortoken,String nombreAEC ,String rutemisor,String email) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{

         String[] arrayrutcedente = rutemisor.split("-");
        
         
       
         
         
        URL url = new URL("https://"+this.urlenvironment+"/cgi_rtc/RTC/RTCAnotEnvio.cgi");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("Accept", "image/gif, image/x -xbitmap, image/jpeg, image/pjpeg, application/vnd.ms -powerpoint, application/ms-excel, application/msword, */*");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=7d23e2a11301c4"); 
        conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; PROG 1.0; Windows NT 5.0; YComp 5.0.2.4)"); 
        conn.setRequestProperty("Cookie","TOKEN="+valortoken);
       String archivo = nombreAEC +".xml";
        conn.setUseCaches(false);
        String cadena = "";
        String contenido = "";
        
        FileReader f = new FileReader(archivo);
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                contenido = contenido + cadena + "\r\n" ;
            }
        }
      
           
           /* cuerpo de la peticion request */
   
String stringRequest = "";
stringRequest =  "--7d23e2a11301c4"+"\r\n"+
        "Content-Disposition: form-data;"+" name=" +"\""+"emailNotif"+"\""+"\r\n"+
        "\r\n"+email+"\r\n"+
        "--7d23e2a11301c4"+"\r\n"+
        "Content-Disposition: form-data; name="+ "\"" +"rutCompany" + "\""+"\r\n"+
        "\r\n"+arrayrutcedente[0]+"\r\n"+
        "--7d23e2a11301c4"+"\r\n"+
        "Content-Disposition: form-data; name="+ "\""+ "dvCompany" + "\"" + 
        "\r\n"+ "\r\n"+ arrayrutcedente[1]+"\r\n"+
        "--7d23e2a11301c4"+ 
        "Content-Disposition: form-data; name=" + "\r\n"+"archivo" + "\""+ ";filename="+"\""+archivo+"\"" + "\r\n"+
         "Content-Type: application/octet-stream"+"\r\n"+
        "Content-Transfer-Encoding: binary"+"\r\n"+
         "\r\n"+
       
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+"\r\n"+
         contenido+"\r\n"+ "\r\n"+"--7d23e2a11301c--";
        

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
            
         System.out.print(targetString);
         return  readTrackId(targetString);
         
        }
         
           
       
  
 public String[] readTrackId(String targetString) throws ParserConfigurationException, SAXException, IOException{
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setNamespaceAware(false);
          DocumentBuilder db = dbf.newDocumentBuilder();
         
          Document doc = db.parse(new InputSource(new StringReader(targetString)));
                        
          NodeList nl = doc.getElementsByTagName("TRACKID");
          Element el = (Element) nl.item(0);
          String valortrackid = el.getFirstChild().getNodeValue();
          
          
          NodeList nl2 = doc.getElementsByTagName("STATUS");
          Element el2 = (Element) nl2.item(0);
          String estadoenvioaec = el2.getFirstChild().getNodeValue();        
          String[] arrayObjetosEstado = null;
          
          arrayObjetosEstado[0] = valortrackid;
          arrayObjetosEstado[1] = estadoenvioaec;
//          File fichero = new File(objconfig.getPathdata()+"track"+nombredte+".xml");
//          fichero.delete();

return arrayObjetosEstado;
    
 }         
        
    
    
    
    
    
    
}
