package com.appdte.sii.utilidades;
import com.appdte.models.DteModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EnvioDTE {

    private Document doc;
    private final String environment;

    public EnvioDTE(String environment){
       this.environment = environment;
       
    }
           
    

   public void generaEnvio(DteModel objdte,String nombredte,String pathdte,String rutusuario) throws ParserConfigurationException, FileNotFoundException, IOException, SAXException, TransformerConfigurationException, TransformerException{
       ConfigAppDTE objconfig = new ConfigAppDTE();
       	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        this.doc = docBuilder.newDocument();
		
        
        /*
        this.doc.createComment("eeee");
        */
        Element setdte = this.doc.createElement("SetDTE");
        
        Attr attrversion = this.doc.createAttribute("ID");
	attrversion.setValue("SetDoc"); 
        setdte.setAttributeNode(attrversion);
        
        
        Element caratula = this.doc.createElement("Caratula");
        caratula.setAttribute("version", "1.0");
       
        Element rutemisor = this.doc.createElement("RutEmisor");
        rutemisor.setTextContent(objdte.getRutemisor().trim());
        
        
        Element rutenvia = this.doc.createElement("RutEnvia");
        rutenvia.setTextContent(rutusuario);
        
        
        
        
        
        Element rutreceptor = this.doc.createElement("RutReceptor");
        
        
      
        rutreceptor.setTextContent("60803000-K");
  /*    
  }else{
          rutreceptor.setTextContent(objdte.getRutreceptor().trim());
      }
    */  
        caratula.appendChild(rutemisor);
        caratula.appendChild(rutenvia);
        caratula.appendChild(rutreceptor);
        
        Element fecharesol = this.doc.createElement("FchResol");
        fecharesol.setTextContent(objdte.getFecharesol());
        caratula.appendChild(fecharesol);
        
        Element nroresol = this.doc.createElement("NroResol");
        nroresol.setTextContent(objdte.getNumresol());
        caratula.appendChild(nroresol);
        
        
           
           
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
        String stringFecha = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String stringHora = timeFormat.format(date);
        
        
        
        
        Element tmstfirmaenv = this.doc.createElement("TmstFirmaEnv");
        tmstfirmaenv.setTextContent(stringFecha+"T"+stringHora);
        caratula.appendChild(tmstfirmaenv);
        
        
        Element subtotdte = this.doc.createElement("SubTotDTE");
        
        Element tpodte = this.doc.createElement("TpoDTE");
        tpodte.setTextContent(objdte.getTipodte());
        subtotdte.appendChild(tpodte);
        
        Element nrodte = this.doc.createElement("NroDTE");
        nrodte.setTextContent("1");
        subtotdte.appendChild(nrodte);
        
        caratula.appendChild(subtotdte);
       
       Element enviodte = this.doc.createElement("EnvioDTE");
       enviodte.appendChild(setdte);
       setdte.appendChild(caratula);
       
       
       
       /*
       
        xmlns="http://www.sii.cl/SiiDte" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0" xsi:schemaLocation="http://www.sii.cl/SiiDte EnvioDTE_v10.xsd"
      
        */
        Attr attr1 = this.doc.createAttribute("xmlns");
	attr1.setValue("http://www.sii.cl/SiiDte"); 
        enviodte.setAttributeNode(attr1);        
                
        
        Attr attr2 = this.doc.createAttribute("xmlns:xsi");
	attr2.setValue("http://www.w3.org/2001/XMLSchema-instance"); 
        enviodte.setAttributeNode(attr2);   
        
        
        Attr attr3 = this.doc.createAttribute("version");
	attr3.setValue("1.0"); 
        enviodte.setAttributeNode(attr3);        
                
       
        
        Attr attr4 = this.doc.createAttribute("xsi:schemaLocation");
	attr4.setValue("http://www.sii.cl/SiiDte EnvioDTE_v10.xsd"); 
        enviodte.setAttributeNode(attr4);       
        
        
        
     

         this.doc.appendChild(enviodte);
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
       
         transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   
         transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0"); 
              
         DOMSource source = new DOMSource(this.doc);
        
	 StreamResult result;
         result = new StreamResult(new File(objconfig.getPathdte()+"ENV"+nombredte+".xml"));
	
   
         
         transformer.transform(source, result);
         System.out.println("Done");
      
        addDTE(nombredte);
}      
   
  
   private void addDTE(String nombredte) throws ParserConfigurationException, SAXException, IOException, TransformerException{
     ConfigAppDTE objconfig = new ConfigAppDTE();
      String archivo = objconfig.getPathdte()+ nombredte+".xml";

 FileInputStream archivodte = new FileInputStream(archivo);
 InputStreamReader inputdte = new InputStreamReader(archivodte,"ISO-8859-1");
 InputSource sourcedte = new InputSource(inputdte);      
        

	 DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
	 Document doc2 = docBuilder2.parse(sourcedte);
         Node dte = doc2.getElementsByTagName("DTE").item(0);
    
          StringWriter buf = new StringWriter();
          Transformer xform = TransformerFactory.newInstance().newTransformer();
          
          xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        
          xform.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
         
         xform.setOutputProperty(OutputKeys.INDENT, "no");
          xform.transform(new DOMSource(dte), new StreamResult(buf));   
         
          
          String stringnode = buf.toString();
          


       
       
       
       
       
       
       
       
       
       
       
       
       
        String filepath = objconfig.getPathdte()+"ENV"+nombredte.trim()+".xml";
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        System.out.print(filepath);     
        Document docENV = docBuilder.parse(filepath.trim());
        
        
        /* datos del emisor */
        Node setdte = docENV.getElementsByTagName("SetDTE").item(0);
     
      
       
    Node fragmentNode = docBuilder.parse(new InputSource(new StringReader(stringnode))).getDocumentElement();
    fragmentNode = docENV.importNode(fragmentNode, true);
    setdte.appendChild(fragmentNode);
       
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
       
         transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   
         transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
         transformer.setOutputProperty(OutputKeys.INDENT, "no");
        
         DOMSource source = new DOMSource(docENV);
        
	 StreamResult result;
         result = new StreamResult(new File(objconfig.getPathdte()+"ENV"+nombredte+".xml"));
	
   
         
         transformer.transform(source, result);
         System.out.println("Done");
   }
   
   
}
   
   
   
   

