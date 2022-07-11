/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.models.DetalleDteModel;
import com.appdte.models.DteModel;
import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class xmlBOLETA {
    
    private Document doc;
    private Element documento;

public void crearXml(DteModel encabezadodte) throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException{
    	
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		this.doc = docBuilder.newDocument();
		
                Element dte = this.doc.createElement("DTE");
                Attr attrversion = this.doc.createAttribute("version");
	        attrversion.setValue("1.0"); 
                dte.setAttributeNode(attrversion);
                this.doc.appendChild(dte);
                this.documento = this.doc.createElement("Documento");
                documento.setAttribute("ID", "DOC33");
             
                dte.appendChild(this.documento);
                
                
                
                
                
                  //inicio de encabezado de documento
		Element encabezado = this.doc.createElement("Encabezado");
		this.documento.appendChild(encabezado);
               
                Element iddoc = this.doc.createElement("IdDoc");
                encabezado.appendChild(iddoc);
               
                Element tipodte = this.doc.createElement("TipoDTE");
                tipodte.setTextContent(encabezadodte.getTipodte());
                
               iddoc.appendChild(tipodte);
                
                Element folio = this.doc.createElement("Folio");
                folio.setTextContent(encabezadodte.getNumdte());
                iddoc.appendChild(folio);

                Element fechaemis = this.doc.createElement("FchEmis");
                fechaemis.setTextContent(encabezadodte.getFechadte());
                iddoc.appendChild(fechaemis);
                
                if(Integer.parseInt(tipodte.getTextContent())==52){
                     Element tipotraslado = this.doc.createElement("IndTraslado");
                     tipotraslado.setTextContent(encabezadodte.getTipotraslado());
                     iddoc.appendChild(tipotraslado);
                }
                
                
                 if( Integer.parseInt(tipodte.getTextContent())==33 || Integer.parseInt(tipodte.getTextContent())==34    ){
                     Element frmapago = this.doc.createElement("FmaPago");
                     frmapago.setTextContent(encabezadodte.getFrmapago());
                     iddoc.appendChild(frmapago);
                }
                
                
                // agrego los datos del emisor de la fctura
                                               
                Element emisor = this.doc.createElement("Emisor");
                encabezado.appendChild(emisor);
                
                Element rutemisor = this.doc.createElement("RUTEmisor");
                rutemisor.setTextContent(encabezadodte.getRutemisor().trim());
                emisor.appendChild(rutemisor);
                
                Element razonsocial = this.doc.createElement("RznSocEmisor");
                razonsocial.setTextContent(encabezadodte.getRsemisor());
                emisor.appendChild(razonsocial);
                 
                   Element giroemisor = this.doc.createElement("GiroEmisor");
                   giroemisor.setTextContent(encabezadodte.getGiroemisor());
                   emisor.appendChild(giroemisor);
                   
                  /* 
                   Element acteco = this.doc.createElement("Acteco");
                   acteco.setTextContent(encabezadodte.getActecoemisor());
                   emisor.appendChild(acteco);
                    */
                  
                   Element cdgsiisucur = this.doc.createElement("CdgSIISucur");
                   cdgsiisucur.setTextContent(encabezadodte.getCodigosii());
                   emisor.appendChild(cdgsiisucur);
                   
                   
                   Element dirorigen = this.doc.createElement("DirOrigen");
                   dirorigen.setTextContent(encabezadodte.getDiremisor());
                   emisor.appendChild(dirorigen);
                   
                   
                   Element cmnaorigen = this.doc.createElement("CmnaOrigen");
                   cmnaorigen.setTextContent(encabezadodte.getCmnaemisor());
                   emisor.appendChild(cmnaorigen);
                   
                   
                   Element ciudadorigen = this.doc.createElement("CiudadOrigen");
                   ciudadorigen.setTextContent(encabezadodte.getCiuemisor());
                   emisor.appendChild(ciudadorigen);
              
                   
                   // ahora agrego los datos del receptor
                   
                   Element receptor = this.doc.createElement("Receptor");
                   encabezado.appendChild(receptor);
                   
                   Element rutrecep = this.doc.createElement("RUTRecep");
                   rutrecep.setTextContent(encabezadodte.getRutreceptor());
                   receptor.appendChild(rutrecep);
                   
                   Element rznsocrecep = this.doc.createElement("RznSocRecep");
                   rznsocrecep.setTextContent(encabezadodte.getRsreceptor());
                   receptor.appendChild(rznsocrecep);
                   /*
                   Element girorecep = this.doc.createElement("GiroRecep");
                   girorecep.setTextContent(encabezadodte.getGiroreceptor());
                   receptor.appendChild(girorecep);
                   
                   Element dirrecep = this.doc.createElement("DirRecep");
                   dirrecep.setTextContent(encabezadodte.getDirreceptor());
                   receptor.appendChild(dirrecep);
                   
                   
                   Element cmnarecep = this.doc.createElement("CmnaRecep");
                   cmnarecep.setTextContent(encabezadodte.getCmnareceptor());
                   
                   receptor.appendChild(cmnarecep);
                   
                   Element ciudadrecep = this.doc.createElement("CiudadRecep");
                   ciudadrecep.setTextContent(encabezadodte.getCiureceptor());
                   receptor.appendChild(ciudadrecep);
                     */    
              /* EN ESTA ZONA AGREGO LOS TOTALES DEL DOCUMENTO */     
             Element totales;
             Element mntneto;
             Element tasaiva;
             Element iva;
             Element mnttotal;
             Element mntexe; /* monto exento */
            totales = this.doc.createElement("Totales");
            encabezado.appendChild(totales);
            
           if(encabezadodte.getMontoafecto()>0){
            mntneto = this.doc.createElement("MntNeto");
            mntneto.setTextContent(Integer.toString(encabezadodte.getMontoafecto()));
            totales.appendChild(mntneto);
            }
           
           
            if(encabezadodte.getMontoexento()>0){
            mntexe = this.doc.createElement("MntExe");
            mntexe.setTextContent(Integer.toString(encabezadodte.getMontoexento()));
            totales.appendChild(mntexe);
            }
            
           if (encabezadodte.getMontoiva()>0){
            tasaiva = this.doc.createElement("TasaIVA");
            tasaiva.setTextContent(Integer.toString(encabezadodte.getTasaiva()));
            totales.appendChild(tasaiva);
           }
           
            if (encabezadodte.getMontoiva()>0){
            iva = this.doc.createElement("IVA");
            iva.setTextContent(Integer.toString(encabezadodte.getMontoiva()));
            totales.appendChild(iva);
            }
            mnttotal = this.doc.createElement("MntTotal");
            mnttotal.setTextContent(Integer.toString(encabezadodte.getMontototal()));
            totales.appendChild(mnttotal);
      
} 
                
           
public void agregaDetalle(DetalleDteModel detalledte){
        
            // agrega detalles al documentos xml //
             Element detalle;
             Element nrolindet;
             Element cdgitem;
             Element tpocodigo;
             Element vlrcodigo;
             Element nmbitem;
             Element dscitem;
             Element qtyitem;
             Element prcitem;
             Element montoitem;
             
             detalle = this.doc.createElement("Detalle");
    
             nrolindet = this.doc.createElement("NroLinDet");
             nrolindet.setTextContent(Integer.toString(detalledte.getNrolinea()));
             detalle.appendChild(nrolindet);
                    
             cdgitem = this.doc.createElement("CdgItem");
             detalle.appendChild(cdgitem);
      
             tpocodigo = this.doc.createElement("TpoCodigo");
             tpocodigo.setTextContent(detalledte.getTpocodigo());
             cdgitem.appendChild(tpocodigo);
             
             vlrcodigo = this.doc.createElement("VlrCodigo");
             vlrcodigo.setTextContent(detalledte.getVlrcodigo());
             cdgitem.appendChild(vlrcodigo);
             
             nmbitem = this.doc.createElement("NmbItem");
             nmbitem.setTextContent(detalledte.getNmbitem());
             detalle.appendChild(nmbitem);
          
             dscitem = this.doc.createElement("DscItem");
          
             dscitem.setTextContent(detalledte.getDscitem());
             detalle.appendChild(dscitem);
            
             qtyitem = this.doc.createElement("QtyItem");
             qtyitem.setTextContent(Integer.toString(detalledte.getQtyitem()));
             detalle.appendChild(qtyitem);
          
             prcitem = this.doc.createElement("PrcItem");
             prcitem.setTextContent(Integer.toString(detalledte.getPrcitem()));
             detalle.appendChild(prcitem);
             
          
             if(detalledte.getDescuentopct()>0){
             
                Element descuentopct = this.doc.createElement("DescuentoPct");
                Element descuentomonto = this.doc.createElement("DescuentoMonto");
                
                descuentopct.setTextContent(Integer.toString(detalledte.getDescuentopct()));
                detalle.appendChild(descuentopct);
                
                descuentomonto.setTextContent(Integer.toString(detalledte.getDescuentomonto()));
                detalle.appendChild(descuentomonto);
             }         
                         
             montoitem = this.doc.createElement("MontoItem");
             montoitem.setTextContent(Integer.toString(detalledte.getMontoitem()));
             detalle.appendChild(montoitem);

             this.documento.appendChild(detalle);
         
}        
          
       
        
      
    
    
    public void guardarDocumento(String nombredte,String pathdte) throws TransformerException, ParserConfigurationException, SAXException, IOException{
        
        
           //esta seccion se encarga de hacer la identacion
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();     
              
                transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
              
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1"); 
                            //luego guardo el documento    
                DOMSource source = new DOMSource(this.doc);              
                StreamResult result = new StreamResult(new File(pathdte+  nombredte+".xml"));
		transformer.transform(source, result);                
		
        
   }
}
