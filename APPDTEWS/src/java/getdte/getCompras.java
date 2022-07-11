/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getdte;

import com.google.gson.Gson;
import com.appdte.sii.utilidades.DetalleCompra;
import com.appdte.sii.utilidades.ICVCompra;
import com.appdte.sii.utilidades.ICVCompraJSON;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class getCompras {
    
    
    public String getComprasJSON(byte[] arrayCert,String login,String clavecert, String rutempresa, String mes_periodo, String year_periodo, String serverauth) throws ParserConfigurationException, SAXException, Exception{
        
        
        
        try (OutputStream os = new FileOutputStream(login)) {
            os.write(arrayCert);
        }
        
        
           ICVCompra objICVCompra = new ICVCompra();
                    String periodo = year_periodo+mes_periodo;
                   
                   /* realizo la peticion HTTP PARA OBTENER LAS COMPRAS */ 
                     String stringCSV =  objICVCompra.obtieneCompras(login, clavecert, rutempresa, periodo,serverauth);
                  
                    /* PROCEDO A FORMATEAR EL RESULTADO CSV */
                    ArrayList<DetalleCompra>  arraydetallecompra =    objICVCompra.formatCSV(stringCSV); 
                 
        
        
                ICVCompraJSON  objJSON = new ICVCompraJSON();
                objJSON.setDetalledocumento(arraydetallecompra);
                final Gson gson = new Gson();
	        final String stringJSON = gson.toJson(objJSON);  
                 
        
    System.out.print("\n"+stringJSON);
        return stringJSON;
    }
    
    
}
