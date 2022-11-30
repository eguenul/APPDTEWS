/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;

import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
 
public class SignTimbre {
 
	protected final static Logger LOGGER = Logger.getLogger(SignTimbre.class);
	
	public String signTimbre(String mensaje,String pathrsa) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		Security.addProvider(new BouncyCastleProvider());
		LOGGER.info("BouncyCastle provider added.");
 
		KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
		try {
		    PrivateKey priv = generatePrivateKey(factory, pathrsa );
		
                        
                        
                    String DD = mensaje;
                       
                    byte[] data = DD.getBytes("ISO-8859-1");
                         
                        
                    Signature signature = Signature.getInstance("SHA1withRSA", "BC");
                    signature.initSign(priv);
                    signature.update(data);
                        
                    byte[]resultado = signature.sign();
                    String encoded = Base64.getEncoder().encodeToString(resultado);
               //   String encoded = DatatypeConverter.printBase64Binary(resultado);
                  //System.out.println(encoded);
                  // LOGGER.info(String.format("Instantiated private key: %s", priv));
                
                 return encoded;
                      
                        

                  
			
		} catch (InvalidKeySpecException e) {
		}
	    
                return "";
        }

 
	private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
		PemFile pemFile = new PemFile(filename);
		byte[] content = pemFile.getPemObject().getContent();
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
		return factory.generatePrivate(privKeySpec);
	}
}
           
           
           
          
       
    
    

