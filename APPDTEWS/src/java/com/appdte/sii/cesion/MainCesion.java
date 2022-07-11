/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.cesion;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import com.appdte.json.AECjson;
import com.appdte.json.CesionJson;
import com.appdte.sii.cl.Semilla;
import com.appdte.sii.cl.Token;
import com.appdte.sii.cl.UploadCesion;

import com.appdte.sii.utilidades.getBytesAEC;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.io.StringWriter;
import org.xml.sax.SAXException;



/**
 *
 * @author esteban
 */
public class MainCesion {
    
    private final String loginuser;
    private final String clave;
    private final String empresarut;
    private final String nombreaec;
    private final String environment;
    
    public MainCesion(String loginuser, String clave, int correlativo, String empresarut, byte[] arrayCert, String environment) throws FileNotFoundException, IOException{
       
        
        
         OutputStream os = new FileOutputStream(loginuser); 
         os.write(arrayCert);
        
        
        
        this.environment = environment;
        
        this.loginuser = loginuser;
        this.clave = clave;
        this.empresarut = empresarut.trim();
        
        
        
        
        String[]  arrayempresarut = this.empresarut.split("-");
        this.nombreaec = "AEC"+arrayempresarut[0]+"F"+String.valueOf(correlativo);
        
    
}
    public Object[] sendCesion(String stringjson, byte[] arrayAEC,  String rutcedente, String email, String tipocesion) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException, FileNotFoundException, SAXException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, UnrecoverableEntryException, UnrecoverableKeyException, KeyException, MarshalException, XMLSignatureException, Exception{
     /* cargo parametros de configuracion */
 
     String pathupload = this.nombreaec+".xml";
     File file = new File(pathupload); 
     OutputStream os  = new FileOutputStream(file); 
     os.write((byte[]) arrayAEC);
     
 
   
   String certificado = this.loginuser;
   String pathcertificado = certificado;
   /* CARGO LOS PARAMETROS DE CONFIGURACION */
 
   String urlenvironment = this.environment;       
                
        
        
        
        
        
        
        
    InputStream isjson = new ByteArrayInputStream(stringjson.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    AECjson objaeccesionjson = gson.fromJson(br1, AECjson.class);
 
        
        
        
              /* Preparo el DTE a ceder */
              
              
              
             List<CesionJson> arraycesion = objaeccesionjson.getCesiones();
              /* Preparo los datos de la cesion de documento */
              CesionDAO objcesiondao = new CesionDAO();
              
               StringWriter documentocesion = objcesiondao.creaCesion(this.loginuser,this.clave,arraycesion,tipocesion);
              
        
                 
            /* Preparo el Archivo Electronico de Cesi�n */
            
            AECDao objaec = new AECDao();
             
            objaec.crearAEC(objaeccesionjson);
            
            if ("DTE".equals(tipocesion)){
            
              DTECEDIDO dtecedido = new DTECEDIDO();
              StringWriter objdtecedido = dtecedido.creaDoc(this.loginuser,this.clave, pathupload);
          
            objaec.addDTECEDIDO(objdtecedido);
            }
            
            if ("AEC".equals(tipocesion)){
           
            objaec.addCESIONPREVIUS(pathupload,this.loginuser,this.clave );
            }
            
            objaec.addCESION(documentocesion);
            
                 
            objaec.crearXML(this.nombreaec);
            objaec.signAEC(this.loginuser, this.clave, this.nombreaec);
            
             
              
  Semilla objsemilla = new Semilla();
  
  String valorsemilla =  objsemilla.getSeed(urlenvironment);
  
 Token objtoken = new Token(urlenvironment);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,this.clave,"");

 
 /* con el token obtenido inicio la peticion http de upload */
 
UploadCesion objupload = new UploadCesion(urlenvironment);
 


String[] arrayEstadoEnvioAEC = objupload.sendCesion(valortoken, this.nombreaec, rutcedente, email);
Object[] arrayObjetos = new Object[3];

getBytesAEC objByte = new getBytesAEC();



arrayObjetos[0]=arrayEstadoEnvioAEC[0];
arrayObjetos[1]= objByte.getBytesArray(nombreaec);

/* CAPTURO EL ESTADO DEL ENVIO */
arrayObjetos[2]= objByte.getBytesArray(arrayEstadoEnvioAEC[1]);

/* elimino el archivo temporal del disco */
/*
 if (file.delete()){
   System.out.println("El fichero ha sido borrado satisfactoriamente");
 }
 else{
   System.out.println("El fichero no puede ser borrado");
   
   
  }

*/





return arrayObjetos;

 
}
    
      
    
    
    
    
    
    
    
}
