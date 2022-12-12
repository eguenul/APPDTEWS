package com.appdte.models;

public class DteModel {
    
  private String rutemisor;
  private String rsemisor;
  private String giroemisor;
  private String actecoemisor;
  private String diremisor;
  private String cmnaemisor;
  private String ciuemisor;
   private String indservicio;
   private String indmntneto;
   
 private String rutreceptor;
 private String rsreceptor;
 private String giroreceptor;
 private String dirreceptor;
 private String cmnareceptor;
 private String ciureceptor;

private String tipodte;
private String numdte;
   
private String codigosii; 
   
private int montoneto;
private int montoafecto;
private int montoexento;
private int montoiva;
private int montototal;
private int tasaiva;
private String frmapago;
private String fechadte;
  
private String fecharesol;
   
private String numresol;
private String tipodespacho;

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

    public String getIndservicio() {
        return indservicio;
    }

    public void setIndservicio(String indservicio) {
        this.indservicio = indservicio;
    }

    public int getMontoneto() {
        return montoneto;
    }

    public void setMontoneto(int montoneto) {
        this.montoneto = montoneto;
    }

    /**
     * @return the indmntneto
     */
    public String getIndmntneto() {
        return indmntneto;
    }

    /**
     * @param indmntneto the indmntneto to set
     */
    public void setIndmntneto(String indmntneto) {
        this.indmntneto = indmntneto;
    }

    
   
    
}
