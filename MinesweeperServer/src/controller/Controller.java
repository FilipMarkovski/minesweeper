/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.GeneralDObject;
import domain.User;
import java.net.Socket;
import java.util.List;
import so.GetLevelSO;
import so.LoginSO;
import so.OpsteIzvrsenjeSO;
import so.RegistrationSO;
import so.SaveHighscoreSO;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Filip Markovski
 */
public class Controller {

    private static Controller instance;
    private OpsteIzvrsenjeSO so;
    private List<Socket> clients;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Socket> getClients() {
        return clients;
    }

    public void setClients(List<Socket> clients) {
        this.clients = clients;
    }

    public void addClient(Socket socket) {
        clients.add(socket);
    }

    public void removeClient(Socket socket) {
        clients.remove(socket);
    }
    
    public Response login(Request request) throws Exception {
        so = new LoginSO();
        return ((LoginSO) so).login(request);
    }
        
    public Response registration(Request request) throws Exception {
        so = new RegistrationSO();
        return ((RegistrationSO) so).registration(request);
    }

    public Response getLevel(Request request) {
        so = new GetLevelSO();
        return ((GetLevelSO) so).getLevel(request);
    }
    
    public Response saveHighscore(Request request) throws Exception {
        so = new SaveHighscoreSO();
        return ((SaveHighscoreSO) so).saveHighscore(request);
    }
}
