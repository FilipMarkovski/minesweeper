/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.GeneralDObject;
import domain.Level;
import domain.User;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;

/**
 *
 * @author User
 */
public class GetLevelSO extends OpsteIzvrsenjeSO {

    Request request;
    Response response;

    public Response getLevel(Request request) {
        this.request = request;
        this.response = new Response();
        opsteIzvrsenjeSO();
        return response;
    }

    @Override
    public boolean izvrsiSO() {
        System.out.println(request.getLevel().getWhereCondition());
        Level level = (Level) bbp.findRecord(request.getLevel());
        response.setLevel(level);
        return true;
    }

}
