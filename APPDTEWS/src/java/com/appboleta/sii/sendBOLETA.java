/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appboleta.xml.AppBoleta;
import com.appdte.json.DteJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.appdte.json.ReceptorJson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class sendBOLETA {
    
    
 public String sendBOLETA(String jsonDTE,  String loginuser,  String password,  String rutenvia) throws IOException, MalformedURLException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, TransformerConfigurationException, Exception{
        
        
    seedBOLETA  objSemilla = new seedBOLETA();
    TokenBOLETA objToken = new TokenBOLETA();
    String valorsemilla = objSemilla.getSeed();
    String  stringToken = objToken.getToken(valorsemilla, loginuser, password);
    
    
    AppBoleta objBoleta = new AppBoleta("","");
    
    
    
    objBoleta.generaBoleta(jsonDTE,loginuser,password,rutenvia,true);
    
    
     InputStream isjson = new ByteArrayInputStream(jsonDTE.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    DteJson objdtejson = gson.fromJson(br1, DteJson.class);
 
        
     
     /* DATOS DEL EMISOR EN JSON */
     EmisorJson objemisor = objdtejson.getEmisor();
     
     
     
     
     
    /* DATOS DEL RECEPTOR EN JSON */
    ReceptorJson objreceptor = objdtejson.getReceptor();
    IdDteJson iddoc = objdtejson.getIdDte();
    
    /*
    
    String valortoken,String pathdte,String nombredte ,String rutemisor,String rutusuario
    */
    
    
    
    UpBOLETASII objupload = new UpBOLETASII("");
    
    
    
    String rutemisor = objemisor.getRutemisor();
   String[] arrayrutemisor = rutemisor.split("-");
    
    String rutusuario = rutenvia;
    String codsii = iddoc.getTipoDTE();
    
    String nombredte ="ENVDTE"+ arrayrutemisor[0]+"F"+iddoc.getNumDTE()+"T"+codsii;
            
            
    
    return objupload.upBOLETA(stringToken,nombredte,rutemisor,rutusuario);
    
    
    
    
        
    }
            
}
