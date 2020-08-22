/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.GeneralDObject;
import domain.User;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author User
 */
public class LoginSO extends OpsteIzvrsenjeSO {

    Request request;
    Response response;

    public Response login(Request request) {
        this.request = request;
        this.response = new Response();
        opsteIzvrsenjeSO();
        return response;
    }

    @Override
    public boolean izvrsiSO() {
        System.out.println(request.getUser().getAtrValue());
        User user = (User) bbp.findRecord(request.getUser());
        response.setUser(user);
        return true;
    }

}
