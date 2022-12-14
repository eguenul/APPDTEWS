package com.appdte.sii.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import org.xml.sax.InputSource;

public class Timbre{
    String rutemisor;
    String razonsocial;
    String tipodocumento;
    String desde;
    String hasta;
    String fecha;
    String textoe;
    String textom;
    String textoidk;
    String textofrma;
    String nombredte;
    String pathdata;
    String item1;
    
    public Timbre(){
        
    }
    
    
    
    public void creaTimbre( String pathdte,String nombredte, String pathdata,String pathcaf, String parmrut ) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException, FileNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException{
         this.pathdata = pathdata;
   
         String filepath = pathdte+nombredte+".xml";
	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(filepath);
        
         Node documento = doc.getElementsByTagName("Documento").item(0);
         Element ted = doc.createElement("TED");
         ted.setAttribute("version", "1.0");
         documento.appendChild(ted);
    
          Date date = new Date();
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
          String stringFecha = dateFormat.format(date);
          DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
          String stringHora = timeFormat.format(date);
          
           leerCaf(parmrut,doc.getElementsByTagName("TipoDTE").item(0).getTextContent(),pathcaf,pathdata,nombredte);
        
         
  String strDigest = "<DD><RE>"+ doc.getElementsByTagName("RUTEmisor").item(0).getTextContent()+"</RE>" 
                     +"<TD>"+doc.getElementsByTagName("TipoDTE").item(0).getTextContent()+"</TD>"
                     +"<F>"+doc.getElementsByTagName("Folio").item(0).getTextContent()+"</F>"
                     +"<FE>"+doc.getElementsByTagName("FchEmis").item(0).getTextContent()+"</FE>"
                     +"<RR>"+doc.getElementsByTagName("RUTRecep").item(0).getTextContent()+"</RR>"
                     +"<RSR>"+doc.getElementsByTagName("RznSocRecep").item(0).getTextContent()+"</RSR>"
                     +"<MNT>"+doc.getElementsByTagName("MntTotal").item(0).getTextContent()+"</MNT>"
                     +"<IT1>"+doc.getElementsByTagName("NmbItem").item(0).getTextContent()+"</IT1>"      
                     +"<CAF version=\"1.0\">"
                     +"<DA><RE>"+this.rutemisor+"</RE>"
                     +"<RS>"+this.razonsocial+"</RS>" 
                     +"<TD>"+this.tipodocumento+"</TD>" 
                     +"<RNG>"+"<D>"+this.desde+"</D>"+"<H>"+this.hasta+"</H>"+"</RNG>"
                    +"<FA>"+this.fecha +"</FA>"
                    +"<RSAPK><M>"+textom+"</M>"
                    +"<E>"+textoe+"</E></RSAPK>"
                    +"<IDK>"+textoidk+"</IDK></DA>"
                    +"<FRMA algoritmo=\"SHA1withRSA\">"+textofrma+"</FRMA></CAF>"
                    +"<TSTED>"+stringFecha+"T"+stringHora+"</TSTED></DD>"
                     ; 
        
          
            
          Node nodeDD = docBuilder.parse(new InputSource(new StringReader(strDigest))).getDocumentElement();
          nodeDD = doc.importNode(nodeDD, true);
 
          ted.appendChild(nodeDD);
          
          
          
          
       //  String ruta = "/home/esteban/appdte/json/timrbre.json";
       // File archivo = new File(ruta);
       // BufferedWriter bw = null;
       // if(archivo.exists()) {
        //    bw = new BufferedWriter(new FileWriter(archivo));
        //   bw.write(auxmensaje);
       // } else {
       //     bw = new BufferedWriter(new FileWriter(archivo));
       //     bw.write(auxmensaje);
       // }
       // bw.close();
             
            File ficherodd = new File(pathdata+nombredte+".dd");
            FileWriter fw = new FileWriter(ficherodd);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(strDigest);
        }
            
            String cadenadd;
        FileReader f = new FileReader(pathdata+nombredte+".dd"); 
	BufferedReader b = new BufferedReader(f); 
	cadenadd = b.readLine(); 
		System.out.println(cadenadd); 
	 
	b.close();
        
        
        
          
           SignTimbre objSignTimbre = new SignTimbre();
        
          
          String contenidotimbre = objSignTimbre.signTimbre(cadenadd,pathdata+nombredte+".rsa");
      /*
          File fichero = new File(pathdata+nombredte+".rsa");
        */
          
          /*
          fichero.delete();
          */
          
          Element frmt = doc.createElement("FRMT");
          frmt.setAttribute("algoritmo", "SHA1withRSA");
          frmt.setTextContent(contenidotimbre);
        
        
        
        ted.appendChild(frmt);
          
        Element tmstfirma = doc.createElement("TmstFirma");
        tmstfirma.setTextContent(stringFecha+"T"+stringHora);
        documento.appendChild(tmstfirma);
        
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
	 DOMSource source = new DOMSource(doc);
	 StreamResult result = new StreamResult(new File(pathdte+nombredte+".xml"));
        
         transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
       	      
         transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
         transformer.setOutputProperty(OutputKeys.INDENT, "no");
      
        
          transformer.transform(source, result);
	  System.out.println("Done");
         
    }
    
    
  public void leerCaf(String auxrutemisor, String tipodocumento,String pathcaf, String pathdata, String nombredte) throws ParserConfigurationException, SAXException, IOException{
        
     String filepath2 = pathcaf +"F"+auxrutemisor+"T"+tipodocumento+".xml";
        
      
      FileInputStream archivocaf = new FileInputStream(filepath2);
      InputStreamReader inputcaf = new InputStreamReader(archivocaf,"ISO-8859-1");
      InputSource sourcecaf = new InputSource(inputcaf);      
       

        
        
        
        
	
        DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
	Document doc2 = docBuilder2.parse(sourcecaf);
   
        NodeList nl = doc2.getElementsByTagName("RE");
        Element el = (Element) nl.item(0);
        this.rutemisor = el.getFirstChild().getNodeValue();
        
        NodeList noders = doc2.getElementsByTagName("RS");
        Element elrs = (Element) noders.item(0);
        this.razonsocial = elrs.getFirstChild().getNodeValue();
        
        NodeList nodotd = doc2.getElementsByTagName("TD");
        Element eltd = (Element) nodotd.item(0);
        this.tipodocumento = eltd.getFirstChild().getNodeValue();
        
        NodeList nodod = doc2.getElementsByTagName("D");
        Element eld = (Element) nodod.item(0);
        this.desde = eld.getFirstChild().getNodeValue();
        
        
        NodeList nodoh = doc2.getElementsByTagName("H");
        Element elh = (Element) nodoh.item(0);
        this.hasta = elh.getFirstChild().getNodeValue();
        
        
        
        NodeList nodofa = doc2.getElementsByTagName("FA");
        Element elfa = (Element) nodofa.item(0);
        this.fecha = elfa.getFirstChild().getNodeValue();
        
        
        NodeList nodoe = doc2.getElementsByTagName("E");
        Element ele = (Element) nodoe.item(0);
        this.textoe = ele.getFirstChild().getNodeValue();
        
        
        
        NodeList nodom = doc2.getElementsByTagName("M");
        Element elm = (Element) nodom.item(0);
        this.textom = elm.getFirstChild().getNodeValue();
        
        NodeList nodoidk = doc2.getElementsByTagName("IDK");
        Element elidk = (Element) nodoidk.item(0);
        this.textoidk = elidk.getFirstChild().getNodeValue();
        
        NodeList nodofrma = doc2.getElementsByTagName("FRMA");
        Element elfrma = (Element) nodofrma.item(0);
        this.textofrma = elfrma.getFirstChild().getNodeValue();
        
        NodeList nodorsask = doc2.getElementsByTagName("RSASK");
        Element elrsask = (Element) nodorsask.item(0);
        String stringrsask = elrsask.getFirstChild().getNodeValue();

        String nombreArchivo = pathdata+nombredte+".rsa";
        FileWriter fw = new FileWriter(nombreArchivo);
        BufferedWriter bw = new BufferedWriter(fw);
        byte ptext[] = stringrsask.getBytes();
        
        String auxtext = new String(ptext,"ISO-8859-1");
        
        try (PrintWriter printtimbre = new PrintWriter(bw)) {
            printtimbre.print(auxtext);
            printtimbre.close();
        }
        
      
  }
   
    
                
                
}