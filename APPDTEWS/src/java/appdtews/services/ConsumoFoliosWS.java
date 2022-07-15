/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;


import com.appboleta.xml.xmlConsumoFolio;
import java.io.UnsupportedEncodingException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "ConsumoFolioWS")
public class ConsumoFoliosWS {

    
    
@WebMethod(operationName = "sendRCOF")
public String sendRCOF(@WebParam(name = "jsonDTE") String jsonRCOF, @WebParam(name = "loginuser") String loginuser, @WebParam(name = "password") String password, @WebParam(name = "rutenvia") String rutenvia) throws UnsupportedEncodingException, ParserConfigurationException, TransformerException {
    
      
   
    xmlConsumoFolio objxml = new xmlConsumoFolio();
    objxml.crearXml(jsonRCOF);
    
    
    return "Hello ";
}
    
    
    
}
