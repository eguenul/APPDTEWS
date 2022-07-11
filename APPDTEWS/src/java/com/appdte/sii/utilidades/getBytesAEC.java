/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author esteban
 */
public class getBytesAEC {
    
public byte[] getBytesArray(String nombreAEC) throws IOException{

  File file = new File(nombreAEC+".xml");
  byte[] bytes = Files.readAllBytes(file.toPath());
  return bytes;
   
   }
}
