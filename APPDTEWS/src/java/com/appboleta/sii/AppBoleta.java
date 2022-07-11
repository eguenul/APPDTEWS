/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.json.DetalleDteJson;
import com.appdte.json.DteJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.appdte.json.ReceptorJson;
import com.appdte.json.TotalesJson;
import com.appdte.models.DetalleDteModel;
import com.appdte.models.DteModel;
import com.appdte.sii.utilidades.ConfigClass;
import com.appdte.sii.utilidades.FuncionesCAF;
import com.appdte.sii.utilidades.SignDTE;
import com.appdte.sii.utilidades.Timbre;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class AppBoleta {
    
    String environment;
    
    
    public AppBoleta(String loginuser, String environment) throws FileNotFoundException, IOException{
        
     this.environment = environment;
     
     
     
    }
    

 @SuppressWarnings("empty-statement")
    
    public void generaBoleta(String stringDTE,String certificado,String clave,String rutEnvia, boolean blReferencia) throws TransformerException, ParserConfigurationException, SAXException, IOException, Exception{

   ConfigClass objconfig = new ConfigClass();
   
  
   String pathdata = objconfig.getPathdata();
   String pathcaf = objconfig.getPathcaf();
  
   /* CARGO LOS PARAMETROS DE CONFIGURACION */
   String pathdte = objconfig.getPathdte();
   String urlenvironment = this.environment;
   /* ingreso el DTE en formato JSON */
             
             
   System.out.println("Reading JSON from a file");
   System.out.println("----------------------------");
  
   
  
    InputStream isjson = new ByteArrayInputStream(stringDTE.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    DteJson objdtejson = gson.fromJson(br1, DteJson.class);
 
        
     
     /* DATOS DEL EMISOR EN JSON */
     EmisorJson objemisor = objdtejson.getEmisor();
     
     
     
     
     
    /* DATOS DEL RECEPTOR EN JSON */
    ReceptorJson objreceptor = objdtejson.getReceptor();
    IdDteJson iddoc = objdtejson.getIdDte();
    
    TotalesJson totales = objdtejson.getTotales(); 
   /* inicializar el xml */        
    DteModel objdte = new DteModel();
    xmlBOLETA obj = new xmlBOLETA();
    DetalleDteModel objdetalledte = new DetalleDteModel();
    /* DEFINO DATOS DEL EMISOR Y RECEPTOR 
   */
    objdte.setRutemisor(objemisor.getRutemisor());
    objdte.setTipodte(iddoc.getTipoDTE());
    objdte.setNumdte(iddoc.getNumDTE());
  
    FuncionesCAF objFuncionCAF = new FuncionesCAF();
    
    /* VALIDAMOS CAF */
    if(objFuncionCAF.validaCAF(objconfig.getPathcaf(), objemisor.getRutemisor(),Integer.parseInt(iddoc.getTipoDTE()), Integer.parseInt(iddoc.getNumDTE()))==false){
    /*
        return null;
      */
    }
    
    
    objdte.setFechadte(iddoc.getFechaEmision());
    
    if(Integer.parseInt(iddoc.getTipoDTE())==52){
        objdte.setTipotraslado(iddoc.getTipotraslado());
    }
    
    
    if(Integer.parseInt(iddoc.getTipoDTE())==33 || Integer.parseInt(iddoc.getTipoDTE())==34 ){
        objdte.setFrmapago(iddoc.getFrmapago());
    }
    
    
    String[] arrayrutemisor = objdte.getRutemisor().split("-");
    
    String rutemisor = arrayrutemisor[0];
    String nombredte = "DTE"+rutemisor+"F"+iddoc.getNumDTE()+"T"+iddoc.getTipoDTE();
        
    
    
    
    
    objdte.setFecharesol(objemisor.getFecharesol());
    objdte.setNumresol(objemisor.getNumresol());
    
    /* DATOS DEL EMISOR EN EL XML */
  
   objdte.setRsemisor(objemisor.getRsemisor());
   objdte.setGiroemisor(objemisor.getGiroemisor());
   objdte.setActecoemisor(objemisor.getActecoemisor());
  
    
   objdte.setCodigosii(objemisor.getCodsiisucur());
   objdte.setDiremisor(objemisor.getDiremisor());
   objdte.setCmnaemisor(objemisor.getCmnaemisor());
   objdte.setCiuemisor(objemisor.getCiuemisor());
    
    
    /* DATOS DEL RECEPTOR EN EL XML */
    objdte.setRutreceptor(objreceptor.getRutreceptor());
    objdte.setRsreceptor(objreceptor.getRsreceptor());
    objdte.setGiroreceptor(objreceptor.getGiroreceptor());
    objdte.setCmnareceptor(objreceptor.getCmnareceptor());
    objdte.setCiureceptor(objreceptor.getCiureceptor());
    objdte.setDirreceptor(objreceptor.getDirreceptor());
    objdte.setNumdte(iddoc.getNumDTE());
    
    
    /* DEFINO EL TOTAL Y TASA DE IMPUESTO */
     objdte.setMontofecto(totales.getMontoafecto());
     objdte.setMontexento(totales.getMontoexento());
     objdte.setMontoiva(totales.getMontoiva());
    objdte.setTasaiva(totales.getTasaiva());
    objdte.setMontototal(totales.getMontototal());
      
    /* INICIALIZO EL OBJETO DE DOCUMENTO */
    obj.crearXml(objdte);
    
      /* cargo los detalles */
     List<DetalleDteJson> detalle = objdtejson.getDetalleDteJson();
  
    
   Timbre objTimbre = new Timbre(objconfig.getPathdte(),nombredte,pathdata,pathcaf);
   String auxDescripcion;
for (DetalleDteJson i :  detalle){     
  if(i.getNrolinea()==1){  
       objTimbre.setItem1(i.getNmbitem());
       auxDescripcion = i.getNmbitem();
 }
   
    
    objdetalledte.setNrolinea(i.getNrolinea());
    objdetalledte.setTpocodigo(i.getTpocodigo());
    objdetalledte.setVlrcodigo(i.getVlrcodigo());
    objdetalledte.setNmbitem(i.getNmbitem());
    objdetalledte.setDscitem(i.getNmbitem());
    objdetalledte.setQtyitem(i.getQtyitem());
    objdetalledte.setPrcitem(i.getPrcitem());
    objdetalledte.setDescuentopct(i.getDescuentopct());
    objdetalledte.setDescuentomonto(i.getDescuentomonto());
    objdetalledte.setMontoitem(i.getMontoitem());
    obj.agregaDetalle(objdetalledte);
    
    }
    
  
  
auxDescripcion = objTimbre.getItem1();
obj.guardarDocumento(nombredte,objconfig.getPathdte());
objTimbre.creaTimbre(objdte, auxDescripcion,rutemisor);
  
    
/* preparo el DTE para firmar */
SignDTE objFirma = new SignDTE();
objFirma.signDTE(objconfig.getPathdte(),nombredte,certificado,clave);
   
    /* ahora envuelvo el DTE en un sobre electrónico */
   
EnvioBOLETA objenvio = new EnvioBOLETA(this.environment);
objenvio.generaEnvio(objdte,nombredte,objconfig.getPathdte(),rutEnvia);
  
SignENVBOLETA objFirmaENV = new SignENVBOLETA();
objFirmaENV.signENVBOLETA(objconfig.getPathdte(),nombredte,certificado,clave);
    

  
 
}


    
}
