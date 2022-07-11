
package com.appdte.sii.utilidades;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


 

public class SignDTE {
    
    public void signDTE(String pathdte,String nombredte,String pathcertificado, String clave) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, UnrecoverableEntryException, KeyException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerConfigurationException, TransformerException{
               
        
        
        
       
        
         /* CREO LOS ELEMENTOS DE FIRMA */     
            // Create a DOM XMLSignatureFactory that will be used to
            // generate the enveloped signature.
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case,
            // you are signing the whole document, so a URI of "" signifies
            // that, and also specify the SHA1 digest algorithm and
            // the ENVELOPED Transform.
            Reference ref = fac.newReference
             ("#DOC33", fac.newDigestMethod(DigestMethod.SHA1, null),
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
        p12.load(new FileInputStream(pathcertificado), clave.trim().toCharArray());
        Enumeration e = p12.aliases();
        String alias = (String) e.nextElement();
        System.out.println("Alias certifikata:" + alias);
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12.getEntry(alias, new KeyStore.PasswordProtection(clave.trim().toCharArray()));
       
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
        (new FileInputStream(pathdte+nombredte+".xml"));
        
        Node documento = doc.getElementsByTagName("Documento").item(0);
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
OutputStream os2 = new FileOutputStream(pathdte+nombredte+".xml");
TransformerFactory tf = TransformerFactory.newInstance();
Transformer trans = tf.newTransformer();
trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
trans.transform(new DOMSource(doc), new StreamResult(os2));


        
        
        
    
    }


}

