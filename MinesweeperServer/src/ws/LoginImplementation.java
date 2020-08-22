/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import so.LoginSO;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Filip Markovski
 */
@WebService(endpointInterface = "ws.ILogin")
public class LoginImplementation implements ILogin {

    @Override
    public Response login(Request request) {
        LoginSO so = new LoginSO();
        System.out.println("login in process...");
        return so.login(request);
    }
    
}
