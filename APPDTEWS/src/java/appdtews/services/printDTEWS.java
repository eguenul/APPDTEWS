/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "printDTEWS")
public class printDTEWS {

    
    
  @WebMethod(operationName = "getPDF")
  public String getPDF(@WebParam(name = "RUT") String rut, @WebParam(name = "CODSII") String codsii, @WebParam(name = "FOLIO") String folio) throws IOException{
    
      
      
  File file = new File("sample.txt");    
  byte[] fileContent = Files.readAllBytes(file.toPath());
  return "hello";
    
 }
    
    
    
    
    
}
