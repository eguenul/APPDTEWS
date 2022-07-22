/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdtews.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author esteban
 */@WebService(serviceName = "consultaDTEWS")
public class consultaDTEWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "consultaDTE")
    public String consultaDTE(@WebParam(name = "stringJSON") String stringJSON, @WebParam(name = "certificado") String certificado, @WebParam(name = "clave") String clave) {
        //TODO write your implementation code here:
        return null;
    }
    
}
