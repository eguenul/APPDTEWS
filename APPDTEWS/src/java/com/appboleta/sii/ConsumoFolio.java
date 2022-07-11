/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.json.CaratulaJson;
import com.appdte.json.ConsumoJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.RangoJson;
import com.appdte.json.ResumenJson;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ConsumoFolio {
    
    DocumentBuilderFactory docFactory;
   private  Document doc;
    
@SuppressWarnings("empty-statement")    
public void readFile() throws ParserConfigurationException, SAXException, IOException, TransformerException{
    
    
    
    
    
    /* cargo la plantilla xml de consumo de folios */
        String filepath = "/home/esteban/appdte/template/InformeConsumoFolios.xml";
	docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	doc = docBuilder.parse(filepath);
    
    
        /* leo el archivo en formato json */
   String stringDTE="";
   String cadena;
   String archivo = "/home/esteban/appdte/consumo.json";
        FileReader f = new FileReader(archivo);
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                stringDTE = stringDTE + cadena + "\r\n";
            }
        }
      
   
   
    /* leo el flujo de datos del archivo json */
   InputStream isjson = new ByteArrayInputStream(stringDTE.getBytes("UTF-8")); 
   BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson)); 
  /* creo el objeto gson para llevar los datos a clases java */
   Gson gson = new Gson(); 
   
   ConsumoJson objgson = gson.fromJson(br1, ConsumoJson.class);
 /* cargo los datos del emisor */
   EmisorJson objEmisorJson  = objgson.getEmisor(); 
   /* cargo los datos de la caratula */
   CaratulaJson objCaratula = objgson.getCaratula();
   setCaratula(objEmisorJson,objCaratula);
    /* cargo los detalles del documento */
}

private void setCaratula( EmisorJson objEmisorJson ,CaratulaJson objCaratula){
        
       Node RutEmisor = doc.getElementsByTagName("RutEmisor").item(0);
       Node RutEnvia = doc.getElementsByTagName("RutEnvia").item(0);
       Node FchResol = doc.getElementsByTagName("FchResol").item(0);
       Node NroResol = doc.getElementsByTagName("NroResol").item(0);
       Node FchInicio =  doc.getElementsByTagName("FchInicio").item(0);
       Node FchFinal = doc.getElementsByTagName("FchFinal").item(0); 
       Node SecEnvio = doc.getElementsByTagName("SecEnvio").item(0);   
       Node TmstFirmaEnv = doc.getElementsByTagName("TmstFirmaEnv").item(0);
      
       RutEmisor.setTextContent(objEmisorJson.getRutemisor());
       RutEnvia.setTextContent(objCaratula.getRutenvia());
       FchResol.setTextContent(objEmisorJson.getFecharesol());
       NroResol.setTextContent(objEmisorJson.getNumresol());
       FchInicio.setTextContent(objCaratula.getFchinicio());
       FchFinal.setTextContent(objCaratula.getFchfinal());
       SecEnvio.setTextContent(objCaratula.getSecenvio());
       
       
           
           Date date = new Date();
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
          String stringFecha = dateFormat.format(date);
          DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
          String stringHora = timeFormat.format(date);
       TmstFirmaEnv.setTextContent(stringFecha+"T"+stringHora);
}


private void addDetalle(ResumenJson i){
                     
    
    Element Resumen =  this.doc.createElement("Resumen");
    Element TipoDocumento = this.doc.createElement("TipoDocumento");
      
    Element MntNeto =  this.doc.createElement("MntNeto");   
    Element MntIva = this.doc.createElement("MntIva");
    Element TasaIVA = this.doc.createElement("TasaIVA");
    Element MntExento = this.doc.createElement("MntExento");
    Element MntTotal = this.doc.createElement("MntTotal");
    Element FoliosEmitidos = this.doc.createElement("FoliosEmitidos");
    Element FoliosAnulados = this.doc.createElement("FoliosAnulados");
    Element FoliosUtilizados =  this.doc.createElement("FoliosUtilizados");
       
    TipoDocumento.setTextContent(i.getTipodocumento());
    Resumen.appendChild(TipoDocumento);
    MntNeto.setTextContent(i.getMntneto());
    Resumen.appendChild(MntNeto);
    MntIva.setTextContent(i.getMntiva());
    Resumen.appendChild(MntIva);
    TasaIVA.setTextContent(i.getTasaiva());
    Resumen.appendChild(TasaIVA);
    MntExento.setTextContent(i.getMntexento());
    Resumen.appendChild(MntExento);
    MntTotal.setTextContent(i.getMnttotal());
    Resumen.appendChild(MntTotal);
    
    FoliosEmitidos.setTextContent(i.getFoliosemitidos());
    Resumen.appendChild(FoliosEmitidos);
    FoliosAnulados.setTextContent(i.getFoliosanulados());
    Resumen.appendChild(FoliosAnulados);
    FoliosUtilizados.setTextContent(i.getFoliosutilizados());
    Resumen.appendChild(FoliosUtilizados);  
  
       

    
    
    ArrayList<RangoJson> arraylistrango = i.getRango();
    /*
    arraylistrango.stream().forEach((RangoJson j)->{
        
    Element RangoUtilizados  = this.doc.createElement("RangoUtilizados");        
    Element Inicial =   this.doc.createElement("Inicial");        
    Element Final = this.doc.createElement("Final");   
    Inicial.setTextContent(j.getRangoinicial());
    Final.setTextContent(j.getRangofinal());
    RangoUtilizados.appendChild(Inicial);
    RangoUtilizados.appendChild(Final);
    Resumen.appendChild(RangoUtilizados);   
   });
    */
    Node DocumentoConsumoFolios = doc.getElementsByTagName("DocumentoConsumoFolios").item(0);
    DocumentoConsumoFolios.appendChild(Resumen);
}




private void guardaInforme() throws TransformerConfigurationException, TransformerException{
    
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("/home/esteban/appdte/template/archivo.xml"));
      transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
   /*
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
     */  
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
       
        
       transformer.transform(source, result);
	  System.out.println("Done");
    
}

    
    
}
