/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.GUIListener;

import javafx.event.Event;
import javafx.event.EventHandler;
import minesweeper.Controller;
import minesweeper.FXMLDocumentController;

/**
 *
 * @author User
 */
public class GUIListenerGameAuthor implements EventHandler {
    Controller controller;

    public GUIListenerGameAuthor(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void handle(Event event) {
        controller.gameAuthor();
    }
}
