/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import com.appboleta.sii.sendBOLETA;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */


@WebService(serviceName = "sendBOLETAWS")
public class sendBOLETAWS {
  
    
 @WebMethod(operationName = "sendBOLETA")
 public String sendBOLETA(@WebParam(name = "jsonDTE") String jsonDTE, @WebParam(name = "loginuser") String loginuser, @WebParam(name = "password") String password, @WebParam(name = "rutenvia") String rutenvia){
     try {
         sendBOLETA objsendBOLETA = new sendBOLETA();
         return objsendBOLETA.sendBOLETA(jsonDTE,loginuser,password,rutenvia);
     } catch (ParserConfigurationException ex) {
         Logger.getLogger(sendBOLETAWS.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SAXException | XPathExpressionException | TransformerException ex) {
         Logger.getLogger(sendBOLETAWS.class.getName()).log(Level.SEVERE, null, ex);
     } catch (Exception ex) {
         Logger.getLogger(sendBOLETAWS.class.getName()).log(Level.SEVERE, null, ex);
     }
     return null;
 }
}
