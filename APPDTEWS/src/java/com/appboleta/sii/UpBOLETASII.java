/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appboleta.sii;

import com.appdte.sii.utilidades.ConfigAppDTE;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class UpBOLETASII {
    private final String urlenvironment;
    
    public UpBOLETASII(String urlenvironment){
       this.urlenvironment = urlenvironment;
        
    }
    
    public String upBOLETA(String valortoken,String nombredte ,String rutemisor,String rutusuario) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
URL url = new URL("https://pangal.sii.cl/recursos/v1/boleta.electronica.envio");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("accept", "application/json");
		httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 ( compatible; PROG 1.0; Windows NT)");
		httpConn.setRequestProperty("Cookie", "TOKEN=CWJLRIC5FWKBI");
		httpConn.setRequestProperty("Content-Type", "application/json");

		
         
    return null;
    }
}
