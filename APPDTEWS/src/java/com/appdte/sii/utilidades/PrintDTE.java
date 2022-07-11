package com.appdte.sii.utilidades;
import com.itextpdf.text.pdf.BarcodePDF417;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

 


public class PrintDTE{
 
public PrintDTE(){
   
    
}
    
    public void printDTE(String rutemisor,String foliodte, String codsii) throws Exception {

        ConfigClass objConfig = new ConfigClass();
      
       String auxrutemisor = rutemisor;
       
       String[] arrayrutemisor = rutemisor.split("-");
       rutemisor = arrayrutemisor[0];
       String nombredte = objConfig.getPathdte()+"ENVDTE"+rutemisor.trim()+"F"+foliodte+"T"+codsii;
       
 
         
         
         
         
       
       
       
       
        
        /* apunto donde está el xml */
        String filepath = nombredte.trim()+".xml";
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
System.out.print(filepath);
        Document doc = docBuilder.parse(filepath.trim());
        
        
        /* datos del emisor */
        Node giroemisor = doc.getElementsByTagName("GiroEmis").item(0);
        Node DirOrigen  = doc.getElementsByTagName("DirOrigen").item(0);    
        Node ted = doc.getElementsByTagName("TED").item(0); 
        Node ciuemisor = doc.getElementsByTagName("CiudadOrigen").item(0); 
        Node CmnaOrigen = doc.getElementsByTagName("CmnaOrigen").item(0); 
        
        
        Node NroResol = doc.getElementsByTagName("NroResol").item(0); 
        
        Node FchResol = doc.getElementsByTagName("FchResol").item(0);             
        
        String[] arrayfechresol = FchResol.getTextContent().split("-");
       
        String anoresol = arrayfechresol[0];
        
        
        
        
        
        /* texto resolucion */

        String textores = "Resolución Ex. SII N° " + NroResol.getTextContent() + " de "+ anoresol;




        
        /* datos del receptor */
        Node rutreceptor = doc.getElementsByTagName("RUTRecep").item(0);
        Node razreceptor = doc.getElementsByTagName("RznSocRecep").item(0);
        Node giroreceptor = doc.getElementsByTagName("GiroRecep").item(0);
        Node dirreceptor = doc.getElementsByTagName("DirRecep").item(0);
        Node cmnareceptor = doc.getElementsByTagName("CmnaRecep").item(0);
        Node ciureceptor = doc.getElementsByTagName("CiudadRecep").item(0);
        Node fechaemis = doc.getElementsByTagName("FchEmis").item(0);
        
        String auxmntneto;
        if(doc.getElementsByTagName("MntNeto").item(0)==null){
            auxmntneto ="0";
            
        }else{
            
             Node mntneto = doc.getElementsByTagName("MntNeto").item(0);
             auxmntneto = mntneto.getTextContent();
        }
        
        String auxiva;
        if(doc.getElementsByTagName("IVA").item(0)==null){
          auxiva="0";
        }else{
             Node iva = doc.getElementsByTagName("IVA").item(0);
             auxiva = iva.getTextContent();
        }
        
        String auxmntexe;
        if(doc.getElementsByTagName("MntExe").item(0)==null){
            auxmntexe ="0";
            
        }else{
            
             Node mntexe = doc.getElementsByTagName("MntExe").item(0);
             auxmntexe = mntexe.getTextContent();
        }
        
        
        
        
        
        
        
        
        
        Node mnttotal =doc.getElementsByTagName("MntTotal").item(0);
        
        
        
        
        Node RznSoc = doc.getElementsByTagName("RznSoc").item(0);
        /* cargo el template pdf */
        PdfReader reader = new PdfReader(objConfig.getPathtemplate()+"template.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(objConfig.getPathpdf()+"ENVDTE"+rutemisor.trim()+"F"+foliodte+"T"+codsii+".pdf"));
        PdfContentByte content = stamper.getOverContent(1);
        BaseFont bf=BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
        BaseFont bf2=BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
        content.setFontAndSize(bf2, 14);
         /* ahora imprimo el rut y el folio del recuadro */
        content.setTextMatrix(470, 739);
        content.showText(auxrutemisor);
         
       int y1 = 720;
        content.setTextMatrix(410, y1);
      
         switch(codsii){
            
            case "33":
                       content.showText("FACTURA ELECTRONICA");
                       break;
             /*          
            case "39":
                       content.showText("BOLETA ELECTRONICA");
                       break;
             */       
            case "52":
                       content.showText("GUIA DE DESPACHO");
                       y1 = y1-20;
                       content.setTextMatrix(420, y1);
                       content.showText("ELECTRONICA");
                       break;
                              
            case "61":
                      content.showText("NOTA DE CREDITO");
                      y1 = y1 - 20;
                      content.setTextMatrix(420, y1); 
                      content.showText("ELECTRONICA");
                      break;
            
                      
              case "34":
                      content.showText("FACTURA EXENTA");
                      y1 = y1 - 20;
                      content.setTextMatrix(420, y1); 
                      content.showText("ELECTRONICA");
                      break;
                       
                      
            
        }
        
        y1 = y1 - 20;
        
        
        content.setTextMatrix(445, y1);
        content.showText("Nº ");
        
        
        content.setTextMatrix(465, y1);
        content.showText(foliodte);
        
        content.setFontAndSize(bf2, 13);
        content.setTextMatrix(457,654); 
        content.showText(CmnaOrigen.getTextContent());
        
        
        /* DATOS DEL EMISOR Y EMISION  */
        content.setFontAndSize(bf, 8);
        content.setTextMatrix(24,641); 
        content.showText(RznSoc.getTextContent());
        
        content.setTextMatrix(54,624); 
        content.showText(giroemisor.getTextContent());
        
        content.setTextMatrix(92,604); 
        content.showText(DirOrigen.getTextContent());
        
        content.setTextMatrix(262,604); 
        content.showText(ciuemisor.getTextContent());
        
        /* DATOS DEL RECEPTOR */
        
        content.setTextMatrix(429,576); 
        content.showText(rutreceptor.getTextContent());
        content.setTextMatrix(429,556); 
        content.showText(ciureceptor.getTextContent());
        content.setTextMatrix(429,536); 
        content.showText("-");
        content.setTextMatrix(429,516); 
      
        String dateString = fechaemis.getTextContent();
        Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        content.showText(df.format(parsed));
        
        content.setTextMatrix(109,576); 
        content.showText(razreceptor.getTextContent());
        
        content.setTextMatrix(109,556); 
        content.showText(dirreceptor.getTextContent());
        
        content.setTextMatrix(109,536); 
        content.showText(cmnareceptor.getTextContent());
        
        content.setTextMatrix(109,516); 
        content.showText(giroreceptor.getTextContent());
     
        /* cargo DETALLE DEL DOCUMENTO */
        
        
        
      
        
        
        NodeList nodedetalle = doc.getElementsByTagName("Detalle");
       
        
        
        int y = 450;
              for (int temp = 0; temp < nodedetalle.getLength(); temp++) {
                Node nodo = nodedetalle.item(temp);
                System.out.println("Elemento:" + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo; 
                    
                    NodeList nodedetalle2 = element.getElementsByTagName("CdgItem");
                    
                    
                    for(int temp2 = 0; temp2 < nodedetalle2.getLength(); temp2++){
                        Node nodo2 = nodedetalle2.item(temp2);
                        if(nodo2.getNodeType() == Node.ELEMENT_NODE){
                          Element element2 = (Element) nodo2;
                            System.out.println("Codigo: " + element2.getElementsByTagName("VlrCodigo").item(0).getTextContent());            
                                content.setFontAndSize(bf,10);
                                content.setTextMatrix(25,y); 
                                content.showText( element2.getElementsByTagName("VlrCodigo").item(0).getTextContent());
                                
                        }
                        
                        
                    }
                    
                    content.setFontAndSize(bf,10);
                    content.setTextMatrix(100,y); 
                    content.showText(element.getElementsByTagName("NmbItem").item(0).getTextContent());
                    
                    content.setFontAndSize(bf,10);
                    content.setTextMatrix(250,y); 
                    content.showText(element.getElementsByTagName("QtyItem").item(0).getTextContent());
                    
                    
        
                    content.setFontAndSize(bf,10);
                    content.setTextMatrix(340,y); 
                    content.showText(element.getElementsByTagName("PrcItem").item(0).getTextContent());
                    
                   if(element.getElementsByTagName("DescuentoPct").item(0)==null){
                         content.setFontAndSize(bf,10);
                         content.setTextMatrix(430,y); 
                         content.showText("0");
                   
                       
                   }else{
                       
                         content.setFontAndSize(bf,10);
                         content.setTextMatrix(430,y); 
                         content.showText(element.getElementsByTagName("DescuentoPct").item(0).getTextContent());                
                   }
                   
                   
                    
                    
                    
                    
                    
                    content.setFontAndSize(bf,10);
                    content.setTextMatrix(490,y); 
                    content.showText(element.getElementsByTagName("MontoItem").item(0).getTextContent()); 
                    y = y - 20;
                    
                
                }
            }
                     
/* campo para imprimir referencia */
y = y-20;
content.setTextMatrix(25,y); 
content.showText("DATOS ADICIONALES");


y = y - 20;             
content.setTextMatrix(25,y); 
Node nodereferencia =  doc.getElementsByTagName("RazonRef").item(0);
content.showText("REFERENCIA GENERAL: "+nodereferencia.getTextContent());
/* si la referencia es una nota de credito */ 


Node nodotipodocref = doc.getElementsByTagName("TpoDocRef").item(0);
Node folioref = doc.getElementsByTagName("FolioRef").item(0);
Node fecharef = doc.getElementsByTagName("FchRef").item(0);


if ("0".equals(folioref.getTextContent().trim())==false){
             y = y - 20;             
            content.setTextMatrix(25,y); 
            content.showText("DOCUMENTO REFERENCIA: "+ getNombredoc(nodotipodocref.getTextContent())); 
           
            
            y = y - 20;             
            content.setTextMatrix(25,y); 
            content.showText("FOLIO: "+folioref.getTextContent()); 
        
            
            
            y = y - 20;             
            content.setTextMatrix(25,y); 
            content.showText("FECHA: "+formatFecha(fecharef.getTextContent())); 
}               
     /*
       y = y - 20;
        content.setFontAndSize(bf,10);
        content.setTextMatrix(20,y); 
        content.showText(observacion);
     */
        
        /* Imprimo totales del documento */
       content.setFontAndSize(bf,10);
       content.setTextMatrix(480,90); 
       content.showText(auxmntneto);
       
       content.setFontAndSize(bf,10);
       content.setTextMatrix(480,70); 
       content.showText(auxmntexe);
     
     
       content.setFontAndSize(bf,10);
       content.setTextMatrix(480,50); 
       content.showText(auxiva);
      
       
       content.setFontAndSize(bf,10);
       content.setTextMatrix(480,30); 
       content.showText(mnttotal.getTextContent());
        
       
       content.setFontAndSize(bf,10);
       content.setTextMatrix(189,51);
       content.showText(textores); 
       
       
      
        /* tomo el nodo ted ya firmado e imprimo el timbre electrónico */ 
        StringWriter buf = new StringWriter();
        Transformer xform = TransformerFactory.newInstance().newTransformer();
        xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                  
        xform.transform(new DOMSource(ted), new StreamResult(buf));
        String timbre;
        timbre = buf.toString();
     
        System.out.print(timbre);
        
        BarcodePDF417 barcode = new BarcodePDF417();
	barcode.setCodeRows(5);
	barcode.setCodeColumns(18);
	barcode.setErrorLevel(5);
	barcode.setLenCodewords(999);	        
	barcode.setText(timbre.getBytes("ISO-8859-1"));
        barcode.setOptions(BarcodePDF417.PDF417_FORCE_BINARY);
       	com.itextpdf.text.Image image = barcode.getImage();
        /* tamaño de la imagen */
        image.scaleAbsolute(184, 72);
        /* ahora establezco las coordenadas del documento */
	image.setAbsolutePosition(60, 73);		
	content.addImage(image);
        stamper.close();
        
        
        
     
        
        
        
    
        
        
    }
    
    
    private String getNombredoc(String codsii){
        String nombredoc="";
        switch (codsii) {
            
            case "33":
                     nombredoc = "FACTURA ELECTRONICA";   
                     break;     
                     
                     
             case "34":
                     nombredoc = "FACTURA EXENTA ELECTRONICA";   
                     break;     
            
            case "52":
                     nombredoc = "GUIA DE DESPACHO ELECTRONICA";   
                     break;        
            
            case "61":
                     nombredoc = "NOTA DE CREDITO ELECTRONICA";   
                     break;                 
            
            case "801":
                     nombredoc = "ORDEN DE COMPRA";   
                     break;                 
            
                     
        }
        return nombredoc;
    }
    
    
    private String formatFecha(String fechainput) throws ParseException{
        
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = input.parse(fechainput);
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        String fecha= output.format(dateValue);
        return fecha;
    }
       
 }

