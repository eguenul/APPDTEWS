/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import com.appboleta.sii.ConsumoFolio;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ReadConsumo {
    
public ReadConsumo(){
    
    
}


public void readConsumo() throws ParserConfigurationException, SAXException, IOException, TransformerException{
    
ConsumoFolio objConsumo = new ConsumoFolio();
objConsumo.readFile();

}


}
