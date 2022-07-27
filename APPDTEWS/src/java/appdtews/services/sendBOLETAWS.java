/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import com.appboleta.sii.seedBOLETA;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */


@WebService(serviceName = "sendBOLETAWS")
public class sendBOLETAWS {
  
    
 @WebMethod(operationName = "sendBOLETA")
 public String sendBOLETA() throws IOException, MalformedURLException, ParserConfigurationException, SAXException, XPathExpressionException{
     
    seedBOLETA  objSemilla = new seedBOLETA();
     
     
      return objSemilla.getSeed();
 }
}
