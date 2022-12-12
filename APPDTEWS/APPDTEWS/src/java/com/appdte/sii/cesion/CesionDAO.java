/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cesion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.appdte.json.CedenteJson;
import com.appdte.json.CesionJson;
import com.appdte.json.CesionarioJson;
import com.appdte.json.IdDteCesionjson;
import com.appdte.json.RutAutorizadojson;
import com.appdte.sii.utilidades.SignCesion;
import java.io.IOException;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class CesionDAO {
     private Document doc;
     
    
      
      
      
      
    
      public StringWriter creaCesion(String login, String clave, List<CesionJson> arraycesion, String tipocesion) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, MarshalException, XMLSignatureException{
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	  this.doc = docBuilder.newDocument();
           String secuencia = null;
          for (CesionJson objcesion:arraycesion){
              
              
          
          
          Element   cesion = this.doc.createElement("Cesion");
	  
          this.doc.appendChild(cesion);    
          
          Element documentocesion = this.doc.createElement("DocumentoCesion");
          cesion.appendChild(documentocesion);
          Element seqcesion = this.doc.createElement("SeqCesion");
          seqcesion.setTextContent(objcesion.getSeqcesion());
          documentocesion.appendChild(seqcesion);
          /* identificacion del documento */
          
          
          IdDteCesionjson objiddte = objcesion.getIddte();
          
          
          Element iddte = this.doc.createElement("IdDTE");
          documentocesion.appendChild(iddte);
          
          Element tipodte = this.doc.createElement("TipoDTE");
          tipodte.setTextContent(objiddte.getTipodte());
          iddte.appendChild(tipodte);
          
          Element rutemisor = this.doc.createElement("RUTEmisor");
          rutemisor.setTextContent(objiddte.getRutemisor());
          iddte.appendChild(rutemisor);
          
          Element rutreceptor = this.doc.createElement("RUTReceptor");
          rutreceptor.setTextContent(objiddte.getRutreceptor());
          iddte.appendChild(rutreceptor);
          
          Element folio = this.doc.createElement("Folio");
          folio.setTextContent(objiddte.getFolio());
          iddte.appendChild(folio);
          
          Element fchemis = this.doc.createElement("FchEmis");
          fchemis.setTextContent(objiddte.getFchemis());
          iddte.appendChild(fchemis);
          
          
          Element mnttotal = this.doc.createElement("MntTotal");
          mnttotal.setTextContent(objiddte.getMnttotal());
          iddte.appendChild(mnttotal);
          
          /* datos del cedente */
          
          CedenteJson objcedente = objcesion.getCedente();
          
          Element cedente = this.doc.createElement("Cedente");
        
          
          Element rut = this.doc.createElement("RUT");
          rut.setTextContent(objcedente.getRut());
          cedente.appendChild(rut);
          
          Element razonsocial = this.doc.createElement("RazonSocial");
          razonsocial.setTextContent(objcedente.getRazonsocial());
          cedente.appendChild(razonsocial);
          
          Element direccion = this.doc.createElement("Direccion");
          direccion.setTextContent(objcedente.getDireccion());
          cedente.appendChild(direccion);
          
          Element email = this.doc.createElement("eMail");
          email.setTextContent(objcedente.getEmail());
          cedente.appendChild(email);
          
          
          
          RutAutorizadojson objrutautorizado = objcedente.getRutautorizado();
          
          
          Element rutautorizado = this.doc.createElement("RUTAutorizado");
          cedente.appendChild(rutautorizado);
          documentocesion.appendChild(cedente);
          
           Element rutautorizado2 = this.doc.createElement("RUT");
           rutautorizado2.setTextContent(objrutautorizado.getRut());
           rutautorizado.appendChild(rutautorizado2);
           
           Element nombreautorizado = this.doc.createElement("Nombre");
            nombreautorizado.setTextContent(objrutautorizado.getNombre());
           rutautorizado.appendChild(nombreautorizado);
       
         
           CesionarioJson objcesionario = objcesion.getCesionario();
           
           Element cesionario = this.doc.createElement("Cesionario");
           documentocesion.appendChild(cesionario);
           
           
           Element rutcesionario = this.doc.createElement("RUT");
           rutcesionario.setTextContent(objcesionario.getRut());
           cesionario.appendChild(rutcesionario);
           
           Element rsccesionario = this.doc.createElement("RazonSocial");
           rsccesionario.setTextContent(objcesionario.getRazonsocial());
           cesionario.appendChild(rsccesionario);
           
          Element dircesionario = this.doc.createElement("Direccion");
          dircesionario.setTextContent(objcesionario.getDireccion());
          cesionario.appendChild(dircesionario);
          
          Element emailcesionario = this.doc.createElement("eMail");
          emailcesionario.setTextContent(objcesionario.getEmail());
          cesionario.appendChild(emailcesionario);
          
          Element montocesion = this.doc.createElement("MontoCesion");
          montocesion.setTextContent(objcesion.getMontocesion());
          documentocesion.appendChild(montocesion);
          
          Element ultimovencimiento = this.doc.createElement("UltimoVencimiento");
          ultimovencimiento.setTextContent(objcesion.getUltimovencimiento());
          documentocesion.appendChild(ultimovencimiento);
          
          
          
          
          
          
            /* ACTUALIZACION DE DECLARACION JURADA */
            
           
           Element declaracionjurada = this.doc.createElement("DeclaracionJurada");
           
           
           /*
       
    if("AEC".equals(tipocesion)){
          textodeclaracion =  "Se declara bajo juramento que "+ objcedente.getRazonsocial() + " , RUT "+ objcedente.getRut() +" "
               + "ha puesto a disposición del cesionario "
               +  objcesionario.getRazonsocial() + " , RUT "+ objcesionario.getRut() + ", "
               + "el o los documentos donde constan los recibos "
               + "de las mercaderías entregadas o servicios prestados, entregados "
               + "por parte del deudor de la factura "
               +  objcesion.getRsreceptor()+ ", " + rutreceptor.getTextContent()  +   ", "
               + " de acuerdo a lo establecido en la Ley N°19.983.";
      
       
       declaracionjurada.setTextContent(textodeclaracion);
       cedente.appendChild(declaracionjurada);
    }
*/


           
String textodeclaracion = "DOCUMENTO CEDIDO";
    
  /*
  textodeclaracion = "Yo " + objrutautorizado.getNombre()+ ",RUT "+ objrutautorizado.getRut().trim() + ","+
" en representacion de "+ objcedente.getRazonsocial()+ ",RUT "+objcedente.getRut()+ ", "+
"declaro bajo juramento que se ha puesto a "+
"disposicion del cesionario "+
objrutautorizado.getNombre()+
", RUT "+ objrutautorizado.getRut().trim() +", los "+
"documentos donde constan los recibos de la recepcion de "+
"las mercaderias entregadas o servicios prestados, entregados por "+
"parte del deudor de la factura " + objcesionario.getRazonsocial() + ", "+
"Rut "+ objcesionario.getRut() +", de acuerdo a lo establecido en la Ley Nro 19.983";
 
 */   
  
     declaracionjurada.setTextContent(textodeclaracion);
       cedente.appendChild(declaracionjurada);
     
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
        String stringFecha = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String stringHora = timeFormat.format(date);
        
        
          
          
          
          Element tmstcesion = this.doc.createElement("TmstCesion");
          tmstcesion.setTextContent(stringFecha+"T"+stringHora);
          documentocesion.appendChild(tmstcesion);
          
        
          cesion.setAttribute("version", "1.0");
          documentocesion.setAttribute("ID", "DOCCESION"+objcesion.getSeqcesion());
          
           secuencia = objcesion.getSeqcesion();
             
          
          
          }
        
           //esta seccion se encarga de hacer la identacion
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1"); 
                            //luego guardo el documento    
               
                DOMSource source = new DOMSource(this.doc);              
                StringWriter auxcesion = new StringWriter();
                StreamResult result = new StreamResult(auxcesion);
		transformer.transform(source, result); 
             
                
              return firmaCesion(auxcesion,secuencia,login,clave);
      }
     
private StringWriter firmaCesion(StringWriter objdocumentocesion,String secuencia, String login,String clave) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException{
          
   SignCesion objSignCesion = new SignCesion();
   return  objSignCesion.signCesion(objdocumentocesion,login,clave,"DOCCESION"+secuencia,"DocumentoCesion");

}
   
}
