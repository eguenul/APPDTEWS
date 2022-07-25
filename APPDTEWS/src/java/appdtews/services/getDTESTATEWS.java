/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;


import com.appdte.json.ConsultaDTEjson;
import com.appdte.json.MainConsultaDTE;
import com.appdte.sii.funcionesws.ConsultaDTE;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "getDTESTATEWS")
public class getDTESTATEWS {

    @SuppressWarnings("empty-statement")
    @WebMethod(operationName = "getDTESTATE")
    public String getDTESTATE(@WebParam(name = "jsonSTRING") String jsonSTRING,@WebParam(name = "certificado") String certificado, @WebParam(name = "clave") String clave ) throws SAXException, IOException, Exception {
       String dteSTATE = "";
       
    InputStream isjson = new ByteArrayInputStream(jsonSTRING.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
       
       
       
      
    Gson gson = new Gson(); 
     MainConsultaDTE objMain = gson.fromJson(br1, MainConsultaDTE.class);
 
    ConsultaDTEjson objConsultajson = objMain.getConsultaDTE();
       
    ConsultaDTE objConsulta = new ConsultaDTE(); 
       
      
    objConsulta.setRutCompania(objConsultajson.getRutCompania());
    objConsulta.setDvCompania(objConsultajson.getDvCompania());
    
    objConsulta.setRutConsultante(objConsultajson.getRutConsultante());
    objConsulta.setDvConsultante(objConsultajson.getDvConsultante());
    
    objConsulta.setRutReceptor(objConsultajson.getRutReceptor());
    objConsulta.setDvReceptor(objConsultajson.getDvReceptor());
    
    
    objConsulta.setTipoDte(objConsultajson.getTipoDte());
    objConsulta.setFolioDte(objConsultajson.getFolioDte());
    
    objConsulta.setMontoDte(objConsultajson.getMontoDte());
    objConsulta.setFechaEmisionDte(objConsultajson.getFechaEmisionDte());
    
    
    dteSTATE = objConsulta.getEstDte(certificado, clave, "maullin.sii.cl");

      
       
    return dteSTATE;





    }
    
    
    
}
