package com.appdte.sii.utilidades;

import com.appdte.models.DteModel;
import java.io.BufferedWriter;
import java.io.File;
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
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;



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
    String pathdte;
    String pathdata;
    String pathcaf;
    String item1;
    
    public Timbre(String pathdte,String nombredte, String pathdata,String pathcaf){
        this.nombredte = nombredte;
        this.pathdte = pathdte;
        this.pathdata = pathdata;
        this.pathcaf = pathcaf;
    }
    
    
    
    public void creaTimbre(DteModel objdte, String auxDescripcion, String parmrut) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException, FileNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException{
    
   
         String filepath = this.pathdte+this.nombredte+".xml";
	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 Document doc = docBuilder.parse(filepath);
        
         Node documento = doc.getElementsByTagName("Documento").item(0);
         Element ted = doc.createElement("TED");
         ted.setAttribute("version", "1.0");
         documento.appendChild(ted);
         
         Element dd = doc.createElement("DD");
         ted.appendChild(dd);
         
         
           Element re = doc.createElement("RE");
           re.setTextContent(objdte.getRutemisor().trim());
           dd.appendChild(re);
         
         
         Element td = doc.createElement("TD");
         td.setTextContent(objdte.getTipodte());
         dd.appendChild(td);
         
         Element f = doc.createElement("F");
         f.setTextContent(objdte.getNumdte());
         dd.appendChild(f);
         
         Element fe = doc.createElement("FE");
         fe.setTextContent(objdte.getFechadte());
         dd.appendChild(fe);
         
         Element rr = doc.createElement("RR");
         rr.setTextContent(objdte.getRutreceptor());
         dd.appendChild(rr);
                  
         
         Element rsr = doc.createElement("RSR");
         rsr.setTextContent(objdte.getRsreceptor());
         dd.appendChild(rsr);
         
         Element mnt = doc.createElement("MNT");
         mnt.setTextContent(Integer.toString(objdte.getMontototal()));
         dd.appendChild(mnt);
         
         Element it1 = doc.createElement("IT1");
         it1.setTextContent(auxDescripcion);
         dd.appendChild(it1);
         
      
         
           leerCaf(parmrut,objdte.getTipodte(),this.pathcaf);
         
           Element da = doc.createElement("DA");
           
           
           Element re2 = doc.createElement("RE");
           re2.setTextContent(this.rutemisor);
           da.appendChild(re2);
         
           Element rs = doc.createElement("RS");
           rs.setTextContent(this.razonsocial);
           da.appendChild(rs);
          
           Element td2 = doc.createElement("TD");
           td2.setTextContent(this.tipodocumento);
           da.appendChild(td2);
         
           Element rng = doc.createElement("RNG");
           da.appendChild(rng);
           
           Element d = doc.createElement("D");
           d.setTextContent(this.desde);
           rng.appendChild(d);
           
           Element h = doc.createElement("H");
           h.setTextContent(this.hasta);
           rng.appendChild(h);
           
           Element fa = doc.createElement("FA");
           fa.setTextContent(this.fecha);
           da.appendChild(fa);
           
           
           
           
           
           Element rsapk = doc.createElement("RSAPK");
           da.appendChild(rsapk);
           
           
           Element m = doc.createElement("M");
           m.setTextContent(textom);
           rsapk.appendChild(m);
           
           
           
           
           
           Element e = doc.createElement("E");
           e.setTextContent(textoe);
           rsapk.appendChild(e);
           
         
           Element idk = doc.createElement("IDK");
           idk.setTextContent(textoidk);
           da.appendChild(idk);
           
           
           Element caf = doc.createElement("CAF");
           caf.setAttribute("version", "1.0");
           
           caf.appendChild(da);
           dd.appendChild(caf);
           
             
           Element frma = doc.createElement("FRMA");
           frma.setTextContent(textofrma);
           frma.setAttribute("algoritmo", "SHA1withRSA");
           caf.appendChild(frma);
           
           
           
           Date date = new Date();
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           
          String stringFecha = dateFormat.format(date);
          DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
          String stringHora = timeFormat.format(date);
          
          Element tsted = doc.createElement("TSTED");
          tsted.setTextContent(stringFecha+"T"+stringHora);
          dd.appendChild(tsted);
           
          /* genero el mensaje a firmar */ 
           StringWriter buf = new StringWriter();
          Transformer xform = TransformerFactory.newInstance().newTransformer();
          
          xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
           xform.setOutputProperty(OutputKeys.INDENT, "no");
          xform.transform(new DOMSource(dd), new StreamResult(buf));
         
          
          String auxmensaje;
          auxmensaje = buf.toString();
          String auxmensaje2 = new String(auxmensaje.getBytes(),"ISO-8859-1"); 
          
            
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
          
          
          
          
          
             
          SignTimbre objSignTimbre = new SignTimbre();
          
          String contenidotimbre = objSignTimbre.signTimbre(auxmensaje2,this.pathdata+this.nombredte+".rsa");
          File fichero = new File(this.pathdata+this.nombredte+".rsa");
          fichero.delete();
          
          
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
	
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
          transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
       
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
       
        
transformer.transform(source, result);
	  System.out.println("Done");
         
    }
    
    
  public void leerCaf(String auxrutemisor, String tipodocumento,String pathcaf) throws ParserConfigurationException, SAXException, IOException{
        
        String filepath2 = pathcaf +"F"+auxrutemisor+"T"+tipodocumento+".xml";
	
        DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
	Document doc2 = docBuilder2.parse(filepath2);
   
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

        String nombreArchivo =this.pathdata+this.nombredte+".rsa";
        FileWriter fw = new FileWriter(nombreArchivo);
        BufferedWriter bw = new BufferedWriter(fw);
        try (PrintWriter printtimbre = new PrintWriter(bw)) {
            printtimbre.print(stringrsask);
            printtimbre.close();
        }
        
      
  }
   
    
                
                
    
   public void setItem1(String item1){
       this.item1 = item1;
       
   }
   
   public String getItem1(){
       return item1;
       
   }
   
                
                
	}  
  
  
  
  
  
  
  
  
  
  
    


