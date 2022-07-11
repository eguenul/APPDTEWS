/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cesion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.appdte.json.AECjson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class AECDao {
     private final Document doc;
     private final DocumentBuilder docBuilder;
     private Element cesiones;
     
     public AECDao() throws ParserConfigurationException{
         
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      this.docBuilder = docFactory.newDocumentBuilder();

     this.doc = this.docBuilder.newDocument();
	
     }
     
    public void crearAEC(AECjson objaecjson) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, FileNotFoundException, IOException, SAXException{
     	   
     
                Element aec = this.doc.createElement("AEC");
                
                
                this.doc.appendChild(aec);
                Element documentoaec = this.doc.createElement("DocumentoAEC");
                documentoaec.setAttribute("ID", "AECDOC");
             
                aec.appendChild(documentoaec);
                
              
     
        Element caratula = this.doc.createElement("Caratula");
        caratula.setAttribute("version", "1.0");
       
        Element rutcedente = this.doc.createElement("RutCedente");
        rutcedente.setTextContent(objaecjson.getRutcedente());
        
        
        Element rutcesionario = this.doc.createElement("RutCesionario");
        rutcesionario.setTextContent(objaecjson.getRutcesionario());
        
        
        /*
        Element nmbcontacto = this.doc.createElement("NmbContacto");
       nmbcontacto.setTextContent(objaecjson.getNmbcontacto());
        
        Element fonocontacto = this.doc.createElement("FonoContacto");
        fonocontacto.setTextContent(objaecjson.getFonocontacto());
        
        Element mailcontacto = this.doc.createElement("MailContacto");
        mailcontacto.setTextContent(objaecjson.getMailcontacto());
      */
        
        caratula.appendChild(rutcedente);
        caratula.appendChild(rutcesionario);
        /*
        caratula.appendChild(nmbcontacto);
        caratula.appendChild(fonocontacto);
        caratula.appendChild(mailcontacto);
      
       */
        documentoaec.appendChild(caratula);
        aec.appendChild(documentoaec);
        
        
         
           
       
       
       
       
       
      
        
        
        
      
        
        
        
        
        
        
        
        
        
        
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
        String stringFecha = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String stringHora = timeFormat.format(date);
        
        
        
        
        Element tmstfirmaenv = this.doc.createElement("TmstFirmaEnvio");
        tmstfirmaenv.setTextContent(stringFecha+"T"+stringHora);
        caratula.appendChild(tmstfirmaenv);
        
     
        this.cesiones = this.doc.createElement("Cesiones");
        /*
        this.cesiones.appendChild(fragmentNode1);
        
        this.cesiones.appendChild(fragmentNode2);
        */
        
        
        documentoaec.appendChild(this.cesiones);
        // creo los atributos  //
        
        
        
         Attr attr1 = this.doc.createAttribute("xmlns");
	attr1.setValue("http://www.sii.cl/SiiDte"); 
        aec.setAttributeNode(attr1);        
                
        
        Attr attr2 = this.doc.createAttribute("xmlns:xsi");
	attr2.setValue("http://www.w3.org/2001/XMLSchema-instance"); 
        aec.setAttributeNode(attr2);   
        
        
        Attr attr3 = this.doc.createAttribute("version");
	attr3.setValue("1.0"); 
        aec.setAttributeNode(attr3);        
                
       
        
        Attr attr4 = this.doc.createAttribute("xsi:schemaLocation");
	attr4.setValue("http://www.sii.cl/SiiDte AEC_v10.xsd"); 
       aec.setAttributeNode(attr4);       
        
        
        doc.setXmlStandalone(true);
        
        

     

        //esta seccion se encarga de hacer la identacion
      
		    
    }
    
    public void addDTECEDIDO(StringWriter objdtecedido) throws SAXException, IOException{
       
        
        System.out.print("DOCUMENTO CEDIDO ES: "+ objdtecedido.toString());
        Node fragmentNode1 = this.docBuilder.parse(new InputSource(new StringReader(objdtecedido.toString()))).getDocumentElement();
        fragmentNode1 = this.doc.importNode(fragmentNode1, true);
       this.cesiones.appendChild(fragmentNode1);
       
        
    }
    
    
    
    
    
    public void addCESIONPREVIUS(String pathupload, String login, String Clave) throws TransformerException, SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, FileNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, MarshalException, XMLSignatureException{
       String patharchivo = pathupload;
        DocumentBuilderFactory auxdocFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder auxdocBuilder = auxdocFactory.newDocumentBuilder();
        Document auxdoc = auxdocBuilder.parse(patharchivo);
    
        /* DEBO AGREGAR EL DTECEDIDO DE LA CESION ANTERIOR */
        
        if(auxdoc.getElementsByTagName("DTECedido").item(0)==null  ){
           DTECEDIDO dtecedido = new DTECEDIDO();
           StringWriter objdtecedido = dtecedido.creaDoc(login, Clave, pathupload);
           addDTECEDIDO(objdtecedido);
            
        }else{
            
        
        Node dtecesionanterior = auxdoc.getElementsByTagName("DTECedido").item(0);    
      
     TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer.setOutputProperty(OutputKeys.INDENT, "no"); 
                           //luego guardo el documento    
               
                DOMSource source = new DOMSource(dtecesionanterior);              
                 StringWriter auxdtecesionanterior = new StringWriter();
                StreamResult result = new StreamResult(auxdtecesionanterior);
		transformer.transform(source, result); 
             
               System.out.print( auxdtecesionanterior.toString());
               
               
               
               
               
    Node fragmentNodedtecesionanterior = docBuilder.parse(new InputSource(new StringReader(auxdtecesionanterior.toString()))).getDocumentElement();
    fragmentNodedtecesionanterior = this.doc.importNode(fragmentNodedtecesionanterior, true);
    this.cesiones.appendChild(fragmentNodedtecesionanterior);
      
        
        
        }     
        
 
        
    /* ADJUNTO EL HISTORIAL DE CESIONES */    
        
        
    
  if(auxdoc.getElementsByTagName("Cesion").getLength()==0){
    
  }else{
    
     int numcesiones = auxdoc.getElementsByTagName("Cesion").getLength();
     int i;
     
     
     
     for(i=0; i < numcesiones; i++){        
      Node nodecesion = auxdoc.getElementsByTagName("Cesion").item(i);    
      
     TransformerFactory transformerFactory2 = TransformerFactory.newInstance();
	        Transformer transformer2 = transformerFactory2.newTransformer();     
              
                transformer2.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
                transformer2.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer2.setOutputProperty(OutputKeys.INDENT, "no"); 
                           //luego guardo el documento    
               
                DOMSource source2 = new DOMSource(nodecesion);              
                 StringWriter auxcesion = new StringWriter();
                StreamResult result2 = new StreamResult(auxcesion);
		transformer2.transform(source2, result2); 
             
               System.out.print( auxcesion.toString());
               
               
               
               
               
    Node fragmentNode = docBuilder.parse(new InputSource(new StringReader(auxcesion.toString()))).getDocumentElement();
    fragmentNode = this.doc.importNode(fragmentNode, true);
    this.cesiones.appendChild(fragmentNode);
      
     }
     }
     
     
     
         
    }
    
    
    
    
      public void addCESION(StringWriter objetocesion) throws SAXException, IOException{
        Node fragmentNode2 = this.docBuilder.parse(new InputSource(new StringReader(objetocesion.toString()))).getDocumentElement();
        fragmentNode2 = this.doc.importNode(fragmentNode2, true);
       this.cesiones.appendChild(fragmentNode2);
       
       
       
        
    }
        
    
    
    
    public void crearXML(String nombreaec) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException{
 
        
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();     
        transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    
        transformer.setOutputProperty(OutputKeys.INDENT, "no"); 
                                   //luego guardo el documento    
        DOMSource source = new DOMSource(this.doc);              
        StreamResult result = new StreamResult(nombreaec+".xml");
        transformer.transform(source, result);                
    }
    
    
    public void signAEC(String login, String clave, String nombreaec) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, TransformerConfigurationException, TransformerException, KeyException, KeyStoreException, CertificateException, UnrecoverableEntryException{
    
      
     String  pathcertificado = login;
                  
         /* CREO LOS ELEMENTOS DE FIRMA */     
            // Create a DOM XMLSignatureFactory that will be used to
            // generate the enveloped signature.
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case,
            // you are signing the whole document, so a URI of "" signifies
            // that, and also specify the SHA1 digest algorithm and
            // the ENVELOPED Transform.
            Reference ref = fac.newReference
             ("#AECDOC", fac.newDigestMethod(DigestMethod.SHA1, null),
              Collections.singletonList
               (fac.newTransform
                (Transform.ENVELOPED, (TransformParameterSpec) null)),
                 null, null);

            // Create the SignedInfo.
            SignedInfo si = fac.newSignedInfo
             (fac.newCanonicalizationMethod
              (CanonicalizationMethod.INCLUSIVE,
               (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                 Collections.singletonList(ref));


        /* instancio el certificado digital */
        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(login), clave.toCharArray());
        Enumeration e = p12.aliases();
        String alias = (String) e.nextElement();
        System.out.println("Alias certifikata:" + alias);
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12.getEntry(alias, new KeyStore.PasswordProtection(clave.toCharArray()));
       
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
    
        // Create the KeyInfo containing the X509Data.
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
    
        KeyValue keyValue = kif.newKeyValue(cert.getPublicKey());
        ArrayList item = new ArrayList();
        item.add(keyValue);
        item.add(xd);
        /*
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
*/
        KeyInfo ki = kif.newKeyInfo(item);
        
        
         /* INSTANCIO EL DOCUMENTO A FIRMAR */
// Instantiate the document to be signed.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse
        (new FileInputStream(nombreaec+".xml"));
        
        Node documento = doc.getElementsByTagName("DocumentoAEC").item(0);
        Element eldocumento =(Element) documento;
        eldocumento.setIdAttribute("ID", true);
        

// Create a DOMSignContext and specify the RSA PrivateKey and
// location of the resulting XMLSignature's parent element.
        DOMSignContext dsc = new DOMSignContext
        (keyEntry.getPrivateKey(), doc.getDocumentElement());

// Create the XMLSignature, but don't sign it yet.
        XMLSignature signature = fac.newXMLSignature(si, ki);

// Marshal, generate, and sign the enveloped signature.
        signature.sign(dsc);

// Output the resulting document.
OutputStream os = new FileOutputStream(nombreaec+".xml");
TransformerFactory tf = TransformerFactory.newInstance();
Transformer trans = tf.newTransformer();

trans.setOutputProperty(OutputKeys.ENCODING, "Windows-1252");

trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
trans.transform(new DOMSource(doc), new StreamResult(os));

    }
    
    
}
