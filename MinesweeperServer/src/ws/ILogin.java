/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Filip Markovski
 */
@WebService
public interface ILogin {
    
    @WebMethod
    public Response login(Request request);

}
