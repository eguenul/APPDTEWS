/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cesion;
import com.appdte.sii.utilidades.SignCesion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class DTECEDIDO {
   
    
    
    
    
    
    
    

    
public StringWriter creaDoc(String login,String clave, String pathupload) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException, FileNotFoundException, SAXException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, MarshalException, XMLSignatureException{
        
    
      Element documentodtecedido;
     
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	  Document doc = docBuilder.newDocument();
        
          Element   dtecedido = doc.createElement("DTECedido");
          doc.appendChild(dtecedido); 
          
          
         documentodtecedido = doc.createElement("DocumentoDTECedido");
         dtecedido.appendChild(documentodtecedido); 
          
         
         
          
          
          dtecedido.setAttribute("version", "1.0");
          documentodtecedido.setAttribute("ID", "DTECEDIDO");
          
         
           //esta seccion se encarga de hacer la identacion
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                            //luego guardo el documento    
                DOMSource source = new DOMSource(doc);          
                
                StringWriter auxdte = new StringWriter();
                
                
                StreamResult result = new StreamResult(auxdte);
		transformer.transform(source, result); 
               return addDTE(login,clave,auxdte,pathupload);
                
                
         
         
         
          
    }
 
 
 
 private StringWriter addDTE(String login,String clave, StringWriter auxdte, String pathupload) throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, MarshalException, XMLSignatureException{

          DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
	  DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
	  Document doc2 = docBuilder2.parse(new InputSource(new StringReader(auxdte.toString())));
          System.out.append(doc2.getTextContent());
    
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	  Document doc = docBuilder.parse(pathupload);
          Node dte = doc.getElementsByTagName("DTE").item(0);
       
            Source source = new DOMSource(dte);
            
            StringWriter writerdte = new StringWriter();
            
           Result result = new StreamResult(writerdte);
        
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
       
 Node fragmentNode = docBuilder2.parse(new InputSource(new StringReader(writerdte.toString()))).getDocumentElement();
 fragmentNode = doc2.importNode(fragmentNode, true);
  
       
       
       
       Element dtecedido  = (Element) doc2.getElementsByTagName("DocumentoDTECedido").item(0);
       
       dtecedido.appendChild(fragmentNode);
      
          
        
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
        String stringFecha = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String stringHora = timeFormat.format(date);
        
        
          
          
          
          Element tmstfirma = doc2.createElement("TmstFirma");
          tmstfirma.setTextContent(stringFecha+"T"+stringHora);
         dtecedido.appendChild(tmstfirma);
          
          
          
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer2 = transformerFactory.newTransformer();     
              
           transformer2.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
           transformer2.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
           
          StringWriter writerdte2 = new StringWriter();
              
          //luego guardo el documento    
          DOMSource source2 = new DOMSource(doc2);              
          StreamResult result2 = new StreamResult(writerdte2);
          transformer.transform(source2, result2); 
         System.out.print(writerdte2.toString());
      return   signDocumento(writerdte2,login,clave);
}
 

 public StringWriter signDocumento(StringWriter objdtecedido,String login,String clave) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException{
     SignCesion objSignCesion = new SignCesion();
   return  objSignCesion.signCesion(objdtecedido,login,clave,"DTECEDIDO","DocumentoDTECedido");
 }
 
}