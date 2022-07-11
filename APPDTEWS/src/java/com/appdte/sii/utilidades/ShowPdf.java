
package com.appdte.sii.utilidades;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



public class ShowPdf{
    
    
    public ShowPdf(){
        
    }
    

    public void showPdf (String rutemisor,String foliodte,String tipodte) throws ParserConfigurationException, SAXException, IOException {

        
       String[] arrayrutemisor = rutemisor.split("-");
       rutemisor = arrayrutemisor[0];
       String nombredte = "DTE"+rutemisor+"F"+foliodte+"T"+tipodte;
     
        
        
         ConfigClass objconfiguracion = new ConfigClass();
         String pathpdf = objconfiguracion.getPathpdf();
          File pdffile = new File(pathpdf+nombredte+".pdf");
           try {
               Desktop.getDesktop().open(pdffile);
           } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Unable to load the help file", "File Read Error", JOptionPane.ERROR_MESSAGE);
           }

         }
    
    
}