/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import minesweeper.GUIListener.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import minesweeper.Controller;

/**
 *
 * @author User
 */
public class LoginListener implements EventHandler {
    LoginController controller;

    public LoginListener(LoginController controller) {
        this.controller = controller;
    }
    
    @Override
    public void handle(Event event) {
        controller.login();
    }
}
