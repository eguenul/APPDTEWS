package appdtews.services;
import com.appboleta.sii.seedBOLETA;
import com.appboleta.xml.SignRCOF;
import com.appboleta.xml.xmlConsumoFolio;
import com.appdte.sii.cl.Semilla;
import com.appdte.sii.cl.Token;
import com.appdte.sii.cl.UploadSii;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "sendRCOFWS")

public class sendRCOFWS {
    
    @WebMethod(operationName = "sendRCOF")
 public String sendRCOF(@WebParam(name = "jsonSTRING") String jsonSTRING, @WebParam(name = "certificado") String certificado ,@WebParam(name = "clave") String clave) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, SAXException, MarshalException, XMLSignatureException, Exception   {
     String urlenvironment = "maullin.sii.cl"; 
     xmlConsumoFolio objConsumo = new xmlConsumoFolio();
        try {
            objConsumo.crearXml(jsonSTRING);
             SignRCOF objSign = new SignRCOF();
             objSign.signRCOF("reporteconsumo", certificado, clave);
            
            Semilla objsemilla = new Semilla();

  
 
String valorsemilla =  objsemilla.getSeed(urlenvironment);
 
 Token objtoken = new Token(urlenvironment);
 String valortoken =  objtoken.getToken(valorsemilla,certificado,clave,"reporteconsumo");


 UploadSii objupload = new UploadSii(urlenvironment);


 String resultadoenvio = objupload.uploadSii(valortoken,"","reporteconsumo","76040308-3","13968481-8");
 return resultadoenvio;
 
        
        } catch (ParserConfigurationException | UnsupportedEncodingException | TransformerException ex) {
            Logger.getLogger(sendRCOFWS.class.getName()).log(Level.SEVERE, null, ex);
        return "error";
        }
     
     
   
    }
}
