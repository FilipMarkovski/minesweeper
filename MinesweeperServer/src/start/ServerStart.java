/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import javax.xml.ws.Endpoint;
import server.Server;
import ws.LoginImplementation;

/**
 *
 * @author Filip Markovski
 */
public class ServerStart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:4568/ws/ILogin", new LoginImplementation());
            System.out.println("Done");
            Server server = new Server();
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
