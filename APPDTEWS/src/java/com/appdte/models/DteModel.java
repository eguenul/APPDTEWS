package com.appdte.models;

public class DteModel {
    
   String rutemisor;
   String rsemisor;
   String giroemisor;
   String actecoemisor;
   String diremisor;
   String cmnaemisor;
   String ciuemisor;
   
   
   
   String rutreceptor;
   String rsreceptor;
   String giroreceptor;
   String dirreceptor;
   String cmnareceptor;
   String ciureceptor;

   String tipodte;
   String numdte;
   
   String codigosii; 
   
   
   int montoafecto;
   int montoexento;
   int montoiva;
   int montototal;
   int tasaiva;
   private String frmapago;
   String fechadte;
   
   String fecharesol;
   
   String numresol;
   String tipodespacho;

    public String getTipodespacho() {
        return tipodespacho;
    }

    public void setTipodespacho(String tipodespacho) {
        this.tipodespacho = tipodespacho;
    }

    public String getTipotraslado() {
        return tipotraslado;
    }

    public void setTipotraslado(String tipotraslado) {
        this.tipotraslado = tipotraslado;
    }

    String tipotraslado;
   
   
    public DteModel(){
        
    } 
   

    public String getRutemisor() {
        return rutemisor;
    }

    public void setRutemisor(String rutemisor) {
        this.rutemisor = rutemisor;
    }

    public String getRsemisor() {
        return rsemisor;
    }

    public void setRsemisor(String rsemisor) {
        this.rsemisor = rsemisor;
    }

    public String getGiroemisor() {
        return giroemisor;
    }

    public void setGiroemisor(String giroemisor) {
        this.giroemisor = giroemisor;
    }

    public String getActecoemisor() {
        return actecoemisor;
    }

    public void setActecoemisor(String actecoemisor) {
        this.actecoemisor = actecoemisor;
    }

    public String getDiremisor() {
        return diremisor;
    }

    public void setDiremisor(String diremisor) {
        this.diremisor = diremisor;
    }

    public String getCmnaemisor() {
        return cmnaemisor;
    }

    public void setCmnaemisor(String cmnaemisor) {
        this.cmnaemisor = cmnaemisor;
    }

    public String getCiuemisor() {
        return ciuemisor;
    }

    public void setCiuemisor(String ciuemisor) {
        this.ciuemisor = ciuemisor;
    }

    public String getRutreceptor() {
        return rutreceptor;
    }

    public void setRutreceptor(String rutreceptor) {
        this.rutreceptor = rutreceptor;
    }

    public String getRsreceptor() {
        return rsreceptor;
    }

    public void setRsreceptor(String rsreceptor) {
        this.rsreceptor = rsreceptor;
    }

    public String getGiroreceptor() {
        return giroreceptor;
    }

    public void setGiroreceptor(String giroreceptor) {
        this.giroreceptor = giroreceptor;
    }

    public String getDirreceptor() {
        return dirreceptor;
    }

    public void setDirreceptor(String dirreceptor) {
        this.dirreceptor = dirreceptor;
    }

    public String getCmnareceptor() {
        return cmnareceptor;
    }

    public void setCmnareceptor(String cmnareceptor) {
        this.cmnareceptor = cmnareceptor;
    }

    public String getCiureceptor() {
        return ciureceptor;
    }

    public void setCiureceptor(String ciureceptor) {
        this.ciureceptor = ciureceptor;
    }
    
    public void setTipodte(String tipodte){
        this.tipodte = tipodte;
        
    }
    
    public String getTipodte(){
        return tipodte;
        
    }
    
    
    
    public void setNumdte(String numdte){
        this.numdte = numdte;
        
    }
    
    public String getNumdte(){
        return  numdte;
        
    }
    
    public void setCodigosii(String codigosii){
        this.codigosii = codigosii;
    }
    
    public String getCodigosii(){
         return codigosii;
    }
    
    public void setMontofecto(int montoafecto){
     this.montoafecto = montoafecto;
    
    }
    
    public int getMontoafecto(){
        return montoafecto;
        
    }
      
    public void setMontexento(int montoexento){
     this.montoexento = montoexento;
    
    }
    
    public int getMontoexento(){
        return montoexento;
        
    }
    
    
    public void setMontoiva(int montoiva){
        this.montoiva = montoiva;
        
    }
    
    public int getMontoiva(){
        return montoiva;
        
    }
    
    public void setMontototal(int montototal){
        this.montototal = montototal;
        
    }
    
    public int getMontototal(){
        return montototal;
        
    }
    
    public void setTasaiva(int tasaiva){
        this.tasaiva = tasaiva;
        
    }
    
    public int getTasaiva(){
        
        return tasaiva;
    }
    
    
    public void setFechadte(String fechadte){
        
        this.fechadte = fechadte;
    }
    
    public String getFechadte(){
        return fechadte;
    }
    
    
   public void setFecharesol(String fecharesol){
            this.fecharesol = fecharesol;  
   }
   
   public String getFecharesol(){
          return fecharesol;
   }
   
   public void setNumresol(String numresol){
       this.numresol = numresol;
       
   }
   
   public String getNumresol(){
       return numresol;
   }

    public String getFrmapago() {
        return frmapago;
    }

    public void setFrmapago(String frmapago) {
        this.frmapago = frmapago;
    }
   
    
}
