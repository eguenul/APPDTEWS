/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.sii.utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
 
public class PemFile {
 
    PemFile(){
        
    }
	private PemObject pemObject;
 
	public PemFile(String filename) throws FileNotFoundException, IOException {
		try (PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)))) {
			this.pemObject = pemReader.readPemObject();
		}
	}
 
	public PemObject getPemObject() {
		return pemObject;
	}
}