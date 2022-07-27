/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.xml;

import com.appboleta.json.CaratulaConsumoJson;
import com.appboleta.json.ConsumoFolioJson;
import com.appboleta.json.DocumentoConsumoFoliosJson;
import com.appboleta.json.MainConsumoJson;
import com.appboleta.json.RangoAnuladosJson;
import com.appboleta.json.RangoUtilizadosJson;
import com.appboleta.json.ResumenConsumoJson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author esteban
 */
public class xmlConsumoFolio {
    
    private Document doc;
     
  
  public void crearXml(String jsonConsumo) throws ParserConfigurationException, UnsupportedEncodingException, TransformerException{
    System.out.print(jsonConsumo);
    InputStream isjson = new ByteArrayInputStream(jsonConsumo.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    MainConsumoJson objMain = gson.fromJson(br1, MainConsumoJson.class);
    
    ConsumoFolioJson objConsumo = objMain.getConsumoFolio();
    DocumentoConsumoFoliosJson objdocumento = objConsumo.getDocumentoConsumoFolios();
    
    CaratulaConsumoJson objCaratula = objdocumento.getCaratula();
    
    
    
    
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    this.doc = docBuilder.newDocument();
    Element consumofolios = this.doc.createElement("ConsumoFolios");
    
    Attr attr1 = this.doc.createAttribute("xmlns");
    attr1.setValue("http://www.sii.cl/SiiDte"); 
    consumofolios.setAttributeNode(attr1);
    
    
    
    Attr attrversion = this.doc.createAttribute("version");
    attrversion.setValue("1.0"); 
    consumofolios.setAttributeNode(attrversion);                        
    

    Attr attr2 = this.doc.createAttribute("xsi:schemaLocation");
    attr2.setValue("http://www.sii.cl/SiiDte ConsumoFolio_v10.xsd"); 
    consumofolios.setAttributeNode(attr2);     


    Attr attr3 = this.doc.createAttribute("xmlns:xsi");
    attr3.setValue("http://www.w3.org/2001/XMLSchema-instance"); 
    consumofolios.setAttributeNode(attr3);   
        
    this.doc.appendChild(consumofolios);

    
    Element documentoconsumofolios = this.doc.createElement("DocumentoConsumoFolios");
    Attr attrid2 = this.doc.createAttribute("ID");
    attrid2.setValue("CONSUMOFOLIO"); 
    documentoconsumofolios.setAttributeNode(attrid2);
    
    consumofolios.appendChild(documentoconsumofolios);
    
    
    Element caratula = this.doc.createElement("Caratula");
    Attr attrver2 = this.doc.createAttribute("version");
    attrver2.setValue("1.0"); 
    caratula.setAttributeNode(attrver2);
    
    documentoconsumofolios.appendChild(caratula);
    
    
   Element rutemisor = this.doc.createElement("RutEmisor");
   rutemisor.setTextContent(objCaratula.getRutEmisor());
   caratula.appendChild(rutemisor);
   Element rutenvia = this.doc.createElement("RutEnvia");
   rutenvia.setTextContent(objCaratula.getRutEnvia());
    
    
    caratula.appendChild(rutenvia);
    Element fchresol = this.doc.createElement("FchResol");
    fchresol.setTextContent(objCaratula.getFchResol());
    caratula.appendChild(fchresol);
    Element nroresol = this.doc.createElement("NroResol");
    nroresol.setTextContent(objCaratula.getNroResol());
    caratula.appendChild(nroresol);
    Element fchinicio = this.doc.createElement("FchInicio");
    fchinicio.setTextContent(objCaratula.getFchInicio());
    caratula.appendChild(fchinicio);
    Element fchfinal = this.doc.createElement("FchFinal");
    fchfinal.setTextContent(objCaratula.getFchFinal());
    
    caratula.appendChild(fchfinal);
    Element secenvio = this.doc.createElement("SecEnvio");
    secenvio.setTextContent(objCaratula.getSecEnvio());
    caratula.appendChild(secenvio);
    
    Element tmstfirmaenv = this.doc.createElement("TmstFirmaEnv");
    
    tmstfirmaenv.setTextContent(objCaratula.getTmstFirmaEnv());
    
    caratula.appendChild(tmstfirmaenv);
    
    
   List<ResumenConsumoJson> listresumen = objdocumento.getResumen();
       
   
   listresumen.forEach((objetoresumen) -> {
       addResumen(documentoconsumofolios, objetoresumen );
        });
   
   
   guardarDocumento();
} 


private void addResumen(Element documentoconsumofolios, ResumenConsumoJson objResumen){
    
    Element resumen = this.doc.createElement("Resumen");
    documentoconsumofolios.appendChild(resumen);

    Element tipodocumento = this.doc.createElement("TipoDocumento");
    tipodocumento.setTextContent(objResumen.getTipoDocumento());
    resumen.appendChild(tipodocumento);
    
    
    
    Element mntneto = this.doc.createElement("MntNeto");
    mntneto.setTextContent(objResumen.getMntNeto());
    resumen.appendChild(mntneto);
    
    
    
    Element mntiva = this.doc.createElement("MntIva");
    mntiva.setTextContent(objResumen.getMntIva());
    resumen.appendChild(mntiva);
    
    
    
    
    Element tasaiva = this.doc.createElement("TasaIVA");
    tasaiva.setTextContent(objResumen.getTasaIva());
    resumen.appendChild(tasaiva);
    
    
    
    Element mntexento = this.doc.createElement("MntExento");
    mntexento.setTextContent(objResumen.getMntExento());
    resumen.appendChild(mntexento);
    
    
    Element mnttotal = this.doc.createElement("MntTotal");
    mnttotal.setTextContent(objResumen.getMntTotal());
    resumen.appendChild(mnttotal);
    
    Element foliosemitidos = this.doc.createElement("FoliosEmitidos");
    foliosemitidos.setTextContent(objResumen.getFoliosEmitidos());
    resumen.appendChild(foliosemitidos);
    
    
    
    Element foliosanulados = this.doc.createElement("FoliosAnulados");
    foliosanulados.setTextContent(objResumen.getFoliosAnulados());
    resumen.appendChild(foliosanulados);
    
    
    
    Element foliosutilizados = this.doc.createElement("FoliosUtilizados");
    foliosutilizados.setTextContent(objResumen.getFoliosUtilizados());
    resumen.appendChild(foliosutilizados);
    
    
    
    addRango(resumen,objResumen);
    

}


private void addRango(Element resumen, ResumenConsumoJson objResumen){

    
   List<RangoUtilizadosJson> listrangoutilizado = objResumen.getRangoUtilizados();
   /*
   listrangoutilizado.stream().map((i) -> {
       Element inicial = this.doc.createElement("Inicial");
       inicial.setTextContent(i.getInicial());
       rangoutilizados.appendChild(inicial);
            return i;
        }).forEachOrdered((i) -> {
            Element rfinal = this.doc.createElement("Final");
            rfinal.setTextContent(i.getFinal());
            rangoutilizados.appendChild(rfinal);
        });
   

*/
   listrangoutilizado.forEach((p) -> {
       Element rangoutilizados = this.doc.createElement("RangoUtilizados");
       resumen.appendChild(rangoutilizados);
       
       Element inicial = this.doc.createElement("Inicial");
       inicial.setTextContent(p.getInicial());
       rangoutilizados.appendChild(inicial);
  
       Element rfinal = this.doc.createElement("Final");
       rfinal.setTextContent(p.getFinal());
       rangoutilizados.appendChild(rfinal);
        });
  
  
  
  
  
    List<RangoAnuladosJson> listrangoanulado = objResumen.getRangoAnulados();
  
   listrangoanulado.forEach((x) -> {
       
         Element rangoanulados = this.doc.createElement("RangoAnulados");
         resumen.appendChild(rangoanulados);
 
       Element inicial2 = this.doc.createElement("Inicial");
       inicial2.setTextContent(x.getInicial());
       rangoanulados.appendChild(inicial2);
       
            Element rfinal2 = this.doc.createElement("Final");
            rfinal2.setTextContent(x.getFinal());
            rangoanulados.appendChild(rfinal2);
        });
}


private void guardarDocumento() throws TransformerConfigurationException, TransformerException{
        
           //esta seccion se encarga de hacer la identacion
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1"); 
                            //luego guardo el documento    
                DOMSource source = new DOMSource(this.doc);              
                StreamResult result = new StreamResult(new File("/home/esteban/appdte/DTE/reporteconsumo.xml"));
		transformer.transform(source, result);                
		
        
   }
    
    
}



