/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.xml;

import com.appboleta.json.EnvioLibroJson;
import com.appboleta.json.CaratulaJson;
import com.appboleta.json.DetalleJson;
import com.appboleta.json.LibroBoletaJson;
import com.appboleta.json.MainLibroJson;
import com.appboleta.json.ResumenPeriodoJson;
import com.appboleta.json.ResumenSegmentoJson;
import com.appboleta.json.TotalPeriodoJson;
import com.appboleta.json.TotalesSegmentoJson;
import com.appboleta.json.TotalesServicio1Json;
import com.appboleta.json.TotalesServicio2Json;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class LibroBoletaXML {
 
     private Document doc;
  
    
    
public void generaXML(String stringLibroJson ) throws ParserConfigurationException, TransformerException, SAXException, IOException{
System.out.print(stringLibroJson);
    
    InputStream isjson = new ByteArrayInputStream(stringLibroJson.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    MainLibroJson objmainLibroJson = gson.fromJson(br1, MainLibroJson.class);
    LibroBoletaJson objLibroBoletaJson = objmainLibroJson.getLibroBoleta();
    EnvioLibroJson objEnvioLibroJson = objLibroBoletaJson.getEnvioLibro();
    CaratulaJson objCaratulaJson = objEnvioLibroJson.getCaratula();
    DetalleJson objDetalleJson = objEnvioLibroJson.getDetalle();
    
    
    ResumenSegmentoJson objResumenSegmentoJson = objEnvioLibroJson.getResumenSegmento();
    ResumenPeriodoJson objResumenPeriodoJson = objEnvioLibroJson.getResumenPeriodo();
    TotalesSegmentoJson objTotalesSegmentoJson =  objResumenSegmentoJson.getTotalesSegmento();
    
    
    
                
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                
		this.doc = docBuilder.newDocument();
                this.doc.setXmlStandalone(true);
		/* incio la seccion de caratula */
                Element libroboleta = this.doc.createElement("LibroBoleta");
                Attr attrversion = this.doc.createAttribute("version");
	        attrversion.setValue("1.0"); 
                libroboleta.setAttributeNode(attrversion);
                
                this.doc.appendChild(libroboleta);
                
                Element EnvioLibro  = this.doc.createElement("EnvioLibro");
                Attr attrid = this.doc.createAttribute("ID");
	        attrid.setValue("EnvioLibro"); 
                EnvioLibro.setAttributeNode(attrid);
             
                libroboleta.appendChild(EnvioLibro);
               
                
             
                
                
                  //inicio de encabezado de documento
		Element caratula = this.doc.createElement("Caratula");
		
                
                Element RutEmisorLibro = this.doc.createElement("RutEmisorLibro");
                RutEmisorLibro.setTextContent(objCaratulaJson.getRutEmisorLibro());               
                caratula.appendChild(RutEmisorLibro);
                
                
                Element RutEnvia = this.doc.createElement("RutEnvia");
                RutEnvia.setTextContent(objCaratulaJson.getRutEnvia());
                caratula.appendChild(RutEnvia);
                
                                
                
                Element PeriodoTributario = this.doc.createElement("PeriodoTributario");
                PeriodoTributario.setTextContent(objCaratulaJson.getPeriodoTributario());
                caratula.appendChild(PeriodoTributario);
                
                Element FchResol = this.doc.createElement("FchResol");
                FchResol.setTextContent(objCaratulaJson.getFchResol());
                caratula.appendChild(FchResol);
                
                
                
                Element NroResol = this.doc.createElement("NroResol");
                NroResol.setTextContent(objCaratulaJson.getNroResol());
                caratula.appendChild(NroResol);
                
                
                Element TipoLibro = this.doc.createElement("TipoLibro");
                TipoLibro.setTextContent(objCaratulaJson.getTipoLibro());
                caratula.appendChild(TipoLibro);
                
                
                
                Element TipoEnvio = this.doc.createElement("TipoEnvio");
                TipoEnvio.setTextContent(objCaratulaJson.getTipoEnvio());
                caratula.appendChild(TipoEnvio);
                
                
                
                Element NroSegmento = this.doc.createElement("NroSegmento");
                NroSegmento.setTextContent(objCaratulaJson.getNroSegmento());
                caratula.appendChild(NroSegmento);
                
                
                Element FolioNotificacion = this.doc.createElement("FolioNotificacion");
                FolioNotificacion.setTextContent(objCaratulaJson.getFolioNotificacion());
                caratula.appendChild(FolioNotificacion);
                libroboleta.appendChild(caratula);             
             
                EnvioLibro.appendChild(caratula);
               
                
                
                /* resumen de segmento */
                
                                                
                
                Element ResumenSegmento = this.doc.createElement("ResumenSegmento");
                Element TotalesSegmento = this.doc.createElement("TotalesSegmento");
       
                EnvioLibro.appendChild(ResumenSegmento);
                ResumenSegmento.appendChild(TotalesSegmento);
                
                
                
                
                
                 
                
                
                
               Element TpoDoc = this.doc.createElement("TpoDoc");
               TpoDoc.setTextContent(objTotalesSegmentoJson.getTpoDoc());
               TotalesSegmento.appendChild(TpoDoc);
               
               Element TotAnulado = this.doc.createElement("TotAnulado"); 
               TotAnulado.setTextContent(objTotalesSegmentoJson.getTotAnulado());
                             
               TotalesSegmento.appendChild(TotAnulado);
               
               TotalesServicio1Json objTotalesServicio1Json = objTotalesSegmentoJson.getTotalesServicio();
       
               
               
               
                Element TotalesServicio = this.doc.createElement("TotalesServicio"); 
               TotalesSegmento.appendChild(TotalesServicio);
               
               Element TpoServ = this.doc.createElement("TpoServ");
               TpoServ.setTextContent(objTotalesServicio1Json.getTpoServ());
               TotalesServicio.appendChild(TpoServ);
               
               Element PeriodoDevengo = this.doc.createElement("PeriodoDevengo");
               PeriodoDevengo.setTextContent(objTotalesServicio1Json.getPeriodoDevengo());
               
               TotalesServicio.appendChild(PeriodoDevengo);
               
               Element TotDoc = this.doc.createElement("TotDoc");
               TotDoc.setTextContent(objTotalesServicio1Json.getTotDoc());
               TotalesServicio.appendChild(TotDoc);
               
               Element TotMntExe = this.doc.createElement("TotMntExe");
               TotMntExe.setTextContent(objTotalesServicio1Json.getTotMntExe());
               TotalesServicio.appendChild(TotMntExe);
               
               Element TotMntTotal = this.doc.createElement("TotMntTotal");
               TotMntTotal.setTextContent(objTotalesServicio1Json.getTotMntTotal());
               TotalesServicio.appendChild(TotMntTotal);
               
               Element TotMntNoFact = this.doc.createElement("TotMntNoFact");
               TotMntNoFact.setTextContent(objTotalesServicio1Json.getTotMntNoFact());
               TotalesServicio.appendChild(TotMntNoFact);
               
               Element TotMntPeriodo = this.doc.createElement("TotMntPeriodo");
               TotMntPeriodo.setTextContent(objTotalesServicio1Json.getTotMntPeriodo());
               TotalesServicio.appendChild(TotMntPeriodo);
               
               
               Element TotSaldoAnt = this.doc.createElement("TotSaldoAnt");
               TotSaldoAnt.setTextContent(objTotalesServicio1Json.getTotSaldoAnt());
               TotalesServicio.appendChild(TotSaldoAnt);
               
               Element TotVlrPagar = this.doc.createElement("TotVlrPagar");
               TotVlrPagar.setTextContent(objTotalesServicio1Json.getTotVlrPagar());
               TotalesServicio.appendChild(TotVlrPagar);
               
               
               Element TotTicket = this.doc.createElement("TotTicket");
               TotTicket.setTextContent(objTotalesServicio1Json.getTotTicket());
               TotalesServicio.appendChild(TotTicket);
       
              resumenPeriodo(EnvioLibro, objResumenPeriodoJson);
               
              
              addDetalle(EnvioLibro, objDetalleJson);
               
               
               
                guardarDocumento();
}               
    
private void resumenPeriodo(Element EnvioLibro, ResumenPeriodoJson objResumenPeriodoJson){
     
    TotalPeriodoJson objTotalPeriodoJson = objResumenPeriodoJson.getTotalesPeriodo();
    
    Element ResumenPeriodo = this.doc.createElement("ResumenPeriodo");
    
     EnvioLibro.appendChild(ResumenPeriodo);
    
    Element TotalesPeriodo = this.doc.createElement("TotalesPeriodo");
    ResumenPeriodo.appendChild(TotalesPeriodo);
    
    Element TpoDoc = this.doc.createElement("TpoDoc");
    TpoDoc.setTextContent(objTotalPeriodoJson.getTpoDoc());
    TotalesPeriodo.appendChild(TpoDoc);
    
    Element TotAnulado = this.doc.createElement("TotAnulado");
    TotAnulado.setTextContent(objTotalPeriodoJson.getTotAnulado());
    TotalesPeriodo.appendChild(TotAnulado);
    
    
    TotalesServicio2Json objTotalesServicio2Json = objTotalPeriodoJson.getTotalesServicio();
    
     
     Element TotalesServicio = this.doc.createElement("TotalesServicio");
     TotalesPeriodo.appendChild(TotalesServicio);
     
    Element TpoServ = this.doc.createElement("TpoServ");
    TpoServ.setTextContent(objTotalesServicio2Json.getTpoServ());
    TotalesServicio.appendChild(TpoServ);
    
    
    Element PeriodoDevengado = this.doc.createElement("PeriodoDevengado");
    PeriodoDevengado.setTextContent(objTotalesServicio2Json.getPeriodoDevengado());
    TotalesServicio.appendChild(PeriodoDevengado);
    
    
    Element TotDoc = this.doc.createElement("TotDoc");
    TotDoc.setTextContent(objTotalesServicio2Json.getTotDoc());
    TotalesServicio.appendChild(TotDoc);
    
    Element TotMntExe = this.doc.createElement("TotMntExe");
    TotMntExe.setTextContent(objTotalesServicio2Json.getTotMntExe());
    TotalesServicio.appendChild(TotMntExe);
    
    Element TotMntNeto = this.doc.createElement("TotMntNeto");
    TotMntNeto.setTextContent(objTotalesServicio2Json.getTotMntNeto());
    TotalesServicio.appendChild(TotMntNeto);
    
    
    Element TasaIVA = this.doc.createElement("TasaIVA");
    TasaIVA.setTextContent(objTotalesServicio2Json.getTasaIVA());
    TotalesServicio.appendChild(TasaIVA);
    
    
    Element TotMntIVA = this.doc.createElement("TotMntIVA");
    TotMntIVA.setTextContent(objTotalesServicio2Json.getTotMntIVA());
    TotalesServicio.appendChild(TotMntIVA);
    
    
    Element TotMntTotal = this.doc.createElement("TotMntTotal");
    TotMntTotal.setTextContent(objTotalesServicio2Json.getTotMntTotal());
    TotalesServicio.appendChild(TotMntTotal);
    
    Element TotMntNoFact = this.doc.createElement("TotMntNoFact");
    TotMntNoFact.setTextContent(objTotalesServicio2Json.getTotMntNoFact());
    TotalesServicio.appendChild(TotMntNoFact);
   
   Element TotMntPeriodo = this.doc.createElement("TotMntPeriodo");
   TotMntPeriodo.setTextContent(objTotalesServicio2Json.getTotMntPeriodo());
   TotalesServicio.appendChild(TotMntPeriodo);
   
   
   Element TotSaldoAnt = this.doc.createElement("TotSaldoAnt");
   TotSaldoAnt.setTextContent(objTotalesServicio2Json.getTotSaldoAnt());
   TotalesServicio.appendChild(TotSaldoAnt);
   
   
   Element TotVlrPagar = this.doc.createElement("TotVlrPagar");
   TotVlrPagar.setTextContent(objTotalesServicio2Json.getTotVlrPagar());
   TotalesServicio.appendChild(TotVlrPagar);
   
   Element TotTicket = this.doc.createElement("TotTicket");
   TotTicket.setTextContent(objTotalesServicio2Json.getTotTicket());
   TotalesServicio.appendChild(TotTicket);
   
}



private void addDetalle(Element EnvioLibro, DetalleJson objDetalleJson){
    
      Element Detalle = this.doc.createElement("Detalle");
      EnvioLibro.appendChild(Detalle);
      
      Element TpoDoc = this.doc.createElement("TpoDoc");
      TpoDoc.setTextContent(objDetalleJson.getTpoDoc());
      Detalle.appendChild(TpoDoc);
      
      Element FolioDoc = this.doc.createElement("FolioDoc");
      FolioDoc.setTextContent(objDetalleJson.getFolioDoc());
      Detalle.appendChild(FolioDoc);
      
      Element Anulado = this.doc.createElement("Anulado");
      Anulado.setTextContent(objDetalleJson.getAnulado());
      Detalle.appendChild(Anulado);
      
      Element TpoServ = this.doc.createElement("TpoServ");
      TpoServ.setTextContent(objDetalleJson.getTpoServ());
      Detalle.appendChild(TpoServ);
      
      Element FchEmiDoc = this.doc.createElement("FchEmiDoc");
      FchEmiDoc.setTextContent(objDetalleJson.getFchEmiDoc());
      Detalle.appendChild(FchEmiDoc);
      
      Element FchVencDoc = this.doc.createElement("FchVencDoc");
      FchVencDoc.setTextContent(objDetalleJson.getFchVencDoc());
      Detalle.appendChild(FchVencDoc);
      
      Element PeriodoDesde = this.doc.createElement("PeriodoDesde");
      PeriodoDesde.setTextContent(objDetalleJson.getPeriodoDesde());
      Detalle.appendChild(PeriodoDesde);
      
      
      Element PeriodoHasta = this.doc.createElement("PeriodoHasta");
      PeriodoHasta.setTextContent(objDetalleJson.getPeriodoHasta());
      Detalle.appendChild(PeriodoHasta);
      
      
      Element CdgSIISucur = this.doc.createElement("CdgSIISucur");
      CdgSIISucur.setTextContent(objDetalleJson.getCdgSIISucur());
      Detalle.appendChild(CdgSIISucur);
      
      Element RUTCliente = this.doc.createElement("RUTCliente");
      RUTCliente.setTextContent(objDetalleJson.getRUTCliente());
      Detalle.appendChild(RUTCliente);
      
      
      Element CodIntCli = this.doc.createElement("CodIntCli");
      CodIntCli.setTextContent(objDetalleJson.getCodIntCli());
      Detalle.appendChild(CodIntCli);
      
      Element MntExe = this.doc.createElement("MntExe");
      MntExe.setTextContent(objDetalleJson.getMntExe());
      Detalle.appendChild(MntExe);
      
      
      Element MntTotal = this.doc.createElement("MntTotal");
      MntTotal.setTextContent(objDetalleJson.getMntTotal());
      Detalle.appendChild(MntTotal);
     
      
      Element MntNoFact = this.doc.createElement("MntNoFact");
      MntNoFact.setTextContent(objDetalleJson.getMntNoFact());
      Detalle.appendChild(MntNoFact);
      
      Element MntPeriodo = this.doc.createElement("MntPeriodo");
      MntPeriodo.setTextContent(objDetalleJson.getMntPeriodo());
      Detalle.appendChild(MntPeriodo);
      
      
      Element SaldoAnt = this.doc.createElement("SaldoAnt");
      SaldoAnt.setTextContent(objDetalleJson.getSaldoAnt());
      Detalle.appendChild(SaldoAnt);
      
      Element VlrPagar = this.doc.createElement("VlrPagar");
      VlrPagar.setTextContent(objDetalleJson.getVlrPagar());
      Detalle.appendChild(VlrPagar);
      
      Element TotTicketBoleta = this.doc.createElement("TotTicketBoleta");
      TotTicketBoleta.setTextContent(objDetalleJson.getTotTicketBoleta());
      Detalle.appendChild(TotTicketBoleta);
      
     
      
      
      
      
      
      
    
}







    public void guardarDocumento() throws TransformerException, ParserConfigurationException, SAXException, IOException{
        
        
           //esta seccion se encarga de hacer la identacion
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
               
                 
                
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                                          //luego guardo el documento    
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
                       
               
                            
                DOMSource source = new DOMSource(this.doc);              
                StreamResult result = new StreamResult(new File("/home/esteban/appdte/DTE/LIBROBOLETA"+".xml"));
		transformer.transform(source, result);                
		
        
   }
            
    
    
}
