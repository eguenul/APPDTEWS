/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import com.appdte.json.DteJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.appdte.sii.utilidades.AppDTE;
import com.appdte.sii.utilidades.PrintDTE;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "sendDTEWS")
public class sendDTEWS {

    @WebMethod(operationName = "sendDTE")
    public String sendDTE(@WebParam(name = "jsonDTE") String jsonDTE, @WebParam(name = "loginuser") String loginuser, @WebParam(name = "password") String password, @WebParam(name = "rutenvia") String rutenvia) throws IOException, ParserConfigurationException, SAXException, Exception {



     
     String environment = "maullin.sii.cl";
     
     AppDTE objdte = new AppDTE(loginuser,  environment);
     
     String stringDTE = jsonDTE;
     System.out.print(stringDTE);
     PrintDTE objprint = new PrintDTE();
     
     
      System.out.print(stringDTE);
     
     
   InputStream isjson = new ByteArrayInputStream(stringDTE.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    DteJson objdtejson = gson.fromJson(br1, DteJson.class);
 
    IdDteJson iddoc = objdtejson.getIdDte();
    
        
     EmisorJson objemisor = objdtejson.getEmisor();
   
     String rutemisor = objemisor.getRutemisor();
     String folio = iddoc.getNumDTE();
     
     String codsii = iddoc.getTipoDTE();
     String resultadoenvio = objdte.sendDTE(stringDTE, loginuser, password, rutenvia, true);
   
   
   objprint.printDTE(rutemisor, folio, codsii);


        //TODO write your implementation code here:
        return resultadoenvio;
    }

    
    
    
    
}
