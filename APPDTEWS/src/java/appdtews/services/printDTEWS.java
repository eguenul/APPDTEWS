package appdtews.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author esteban
 */
@WebService(serviceName = "printDTEWS")
public class printDTEWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendDTE")
    public String sendDTE(@WebParam(name = "jsonDTE") String jsonDTE, @WebParam(name = "certificado") String certificado, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return null;
    }
    

}