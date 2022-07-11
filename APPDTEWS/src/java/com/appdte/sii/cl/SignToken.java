package com.appdte.sii.cl;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SignToken {

    public StringWriter signToken(StringWriter objectxml, String pathcertificado, String clave) throws Exception {
       
   
        /* CREO LOS ELEMENTOS DE FIRMA */     
        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a Reference to the enveloped document (in this case,
        // you are signing the whole document, so a URI of "" signifies
        // that, and also specify the SHA1 digest algorithm and
        // the ENVELOPED Transform.
            Reference ref = fac.newReference
             ("", fac.newDigestMethod(DigestMethod.SHA1, null),
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
        p12.load(new FileInputStream(pathcertificado), clave.toCharArray());
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
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(objectxml.toString())));
        

// Create a DOMSignContext and specify the RSA PrivateKey and
// location of the resulting XMLSignature's parent element.
        DOMSignContext dsc = new DOMSignContext
        (keyEntry.getPrivateKey(), doc.getDocumentElement());

// Create the XMLSignature, but don't sign it yet.
        XMLSignature signature = fac.newXMLSignature(si, ki);

// Marshal, generate, and sign the enveloped signature.
   signature.sign(dsc);

// Output the resulting document.

StringWriter writer = new StringWriter();
TransformerFactory tf = TransformerFactory.newInstance();
Transformer trans = tf.newTransformer();
trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
trans.transform(new DOMSource(doc), new StreamResult(writer));
      return writer;
    }

}