/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdte.json;

/**
 *
 * @author esteban
 */
public class ReferenciaJson {
    
   
   int codref;
   String razonref;
   int numref; 
   int TpoDocRef;
   int folioref;
   String fecharef;

    public String getFecharef() {
        return fecharef;
    }

    public void setFecharef(String fecharef) {
        this.fecharef = fecharef;
    }
    public int getFolioref() {
        return folioref;
    }

    public void setFolioref(int folioref) {
        this.folioref = folioref;
    }
    public int getTpoDocRef() {
        return TpoDocRef;
    }

    public void setTpoDocRef(int TpoDocRef) {
        this.TpoDocRef = TpoDocRef;
    }


    public void setCodref(int codref) {
        this.codref = codref;
    }
    
    
    public int getCodref() {
        return codref;
    }
    

    public String getRazonref() {
        return razonref;
    }

    public void setRazonref(String razonref) {
        this.razonref = razonref;
    }

    public int getNumref() {
        return numref;
    }

    public void setNumref(int numref) {
        this.numref = numref;
    }

}
