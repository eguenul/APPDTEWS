/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdte;

import com.google.gson.Gson;
import com.appdte.sii.utilidades.DetalleVenta;
import com.appdte.sii.utilidades.ICVVenta;
import com.appdte.sii.utilidades.ICVVentaJSON;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class getVentas {
    
    
    public String getVentasJSON( byte[] arrayCert,String login,String clavecert, String rutempresa, String mes_periodo, String year_periodo, String serverauth) throws ParserConfigurationException, SAXException, Exception{
       
          
      try (OutputStream os = new FileOutputStream(login)) {
            os.write(arrayCert);
        }
        
             
             
             
             
            
                 ICVVenta objICVVenta = new ICVVenta();
                 String periodo = year_periodo+mes_periodo;
                 
                 
                 /* realizo la peticion HTTP PARA OBTENER LAS VENTAS */
                 String stringCSV =  objICVVenta.obtieneVentas(login, clavecert, rutempresa, periodo,serverauth);
                 objICVVenta.formatCSV(stringCSV);
                 /* PROCEDO A FORMATEAR EL RESULTADO CSV */
                 
                 ArrayList<DetalleVenta>  arraydetalleventa = objICVVenta.formatCSV(stringCSV);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
               ICVVentaJSON  objJSON = new ICVVentaJSON();
                objJSON.setDetalledocumento(arraydetalleventa);
                final Gson gson = new Gson();
	        final String stringJSON = gson.toJson(objJSON);  
                 
                  
         
        System.out.print("\n"+stringJSON);
        return stringJSON;
    }
    
}
