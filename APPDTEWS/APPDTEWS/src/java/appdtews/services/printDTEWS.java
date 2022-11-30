/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import com.appdte.sii.utilidades.ConfigAppDTE;
import com.appdte.sii.utilidades.PrintDTE;
import com.appdte.sii.utilidades.getTED;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "printDTEWS")
public class printDTEWS {

    
    
  @WebMethod(operationName = "getPDF")
  public String getPDF(@WebParam(name = "RUT") String rut, @WebParam(name = "CODSII") String codsii, @WebParam(name = "FOLIO") String folio) throws IOException{
    
      
      
      
      
      try {
            
          ConfigAppDTE objConfig = new ConfigAppDTE();       
          PrintDTE objPrintDTE = new PrintDTE();
          objPrintDTE.printDTE(rut, folio, codsii);
          
   
       String[] arrayrutemisor = rut.split("-");
       rut = arrayrutemisor[0];
       String nombredte = objConfig.getPathpdf()+"DTE"+rut.trim()+"F"+folio+"T"+codsii+".pdf";
       
          
          
          
          String filePath = nombredte;
          byte[] bytesContent;
  
          
          
          bytesContent = Files.readAllBytes(Paths.get(filePath));
          
          
          String s =  Base64.getEncoder().encodeToString(bytesContent);
          return s;
      } catch (Exception ex) {
          Logger.getLogger(printDTEWS.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
  
  
    
 }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTED")
    public String getTED(@WebParam(name = "RUT") String rut, @WebParam(name = "CODSII") String codsii, @WebParam(name = "FOLIO") String folio){
      
      try {
          getTED objget = new getTED();
          
          
          return  objget.getTED(rut, folio, codsii);
      } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
          Logger.getLogger(printDTEWS.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
        
    }
    
            
}
