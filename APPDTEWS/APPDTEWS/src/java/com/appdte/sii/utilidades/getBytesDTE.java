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
public class getBytesDTE {
    
   public byte[] getBytesArray(String nombredte) throws IOException{
  // file to byte[], File -> Path
  File file = new File("ENV"+nombredte+".xml");
  byte[] bytes = Files.readAllBytes(file.toPath());
   return bytes;
   }
    
}
