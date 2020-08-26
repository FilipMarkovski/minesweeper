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
public class SaveGameSO extends OpsteIzvrsenjeSO {

    Request request;
    Response response;

    public Response saveGame(Request request) {
        this.request = request;
        this.response = new Response();
        opsteIzvrsenjeSO();
        return response;
    }

    @Override
    public boolean izvrsiSO() {
        System.out.println(request.getGame().getAtrValue());
        System.out.println(request.getUser().getAtrValue());
        
        boolean success = bbp.insertRecord(request.getGame());
        
        if (success) {
            response.setStatus(ResponseStatus.OK);
            return true;
        }
        
        response.setStatus(ResponseStatus.ERROR);
        return false;
        
    }

}
