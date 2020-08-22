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
import transfer.util.ResponseStatus;

/**
 *
 * @author User
 */
public class SaveHighscoreSO extends OpsteIzvrsenjeSO {

    Request request;
    Response response;

    public Response saveHighscore(Request request) {
        this.request = request;
        this.response = new Response();
        opsteIzvrsenjeSO();
        return response;
    }

    @Override
    public boolean izvrsiSO() {
        System.out.println(request.getGame().getAtrValue());
        System.out.println(request.getUser().getAtrValue());
        
        boolean success1 = bbp.insertRecord(request.getGame());
        boolean success2 = bbp.updateRecord(request.getUser());
        if (success1 && success2) {
            response.setStatus(ResponseStatus.OK);
            response.setUser((User) bbp.findRecord(request.getUser()));
            return true;
        }
        
        response.setStatus(ResponseStatus.ERROR);
        return false;
        
    }

}
