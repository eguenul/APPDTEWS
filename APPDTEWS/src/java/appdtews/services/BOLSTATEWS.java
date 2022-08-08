/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import com.appboleta.sii.bolSTATE;
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
@WebService(serviceName = "BOLSTATEWS")
public class BOLSTATEWS {

    
    @WebMethod(operationName = "getENVIOSTATE")
    public String getENVIOSTATE(@WebParam(name = "certificado") String certificado, @WebParam(name = "password") String password, @WebParam(name = "rutemisor") String rutemisor, @WebParam(name = "dvemisor") String dvemisor, @WebParam(name = "trackid") String trackid)  {
        try {
            //TODO write your implementation code here:
            bolSTATE objestado = new bolSTATE();
            return objestado.getESTATE(certificado, password, rutemisor, dvemisor, trackid);
        } catch (ParserConfigurationException ex) {
          
            Logger.getLogger(BOLSTATEWS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SAXException | XPathExpressionException | TransformerException ex) {
            Logger.getLogger(BOLSTATEWS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(BOLSTATEWS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}
}