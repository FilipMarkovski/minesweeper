/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.GeneralDObject;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import minesweeper.FXMLDocumentController;

/**
 *
 * @author User
 */
public class KonverterGUIDK {

    public static boolean konvertujGUIUDK(FXMLDocumentController fxcon, GeneralDObject gdo) {
        for (Field f : fxcon.getClass().getDeclaredFields()) {
            for (Field dk : gdo.getClass().getDeclaredFields()) {
                if (dk.getName().equals(f.getName())) {

                    if (f.getType().getName().equals("javafx.scene.control.TextField") && dk.getType().getName().equals("int")) {
                        try {
                            Integer broj = Integer.valueOf(((javafx.scene.control.TextField) f.get(fxcon)).getText());
                            dk.set(gdo, broj);

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(KonverterGUIDK.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    }

                }
            }
        }
        return true;
    }

    public static boolean konvertujDKUGUI(GeneralDObject gdo, FXMLDocumentController fxcon) {
        for (Field f : fxcon.getClass().getDeclaredFields()) {
            for (Field dk : gdo.getClass().getDeclaredFields()) {
                if (dk.getName().equals(f.getName())) {
                    if (f.getType().getName().equals("javafx.scene.control.TextField") && dk.getType().getName().equals("int")) {
                        try {
                            Integer broj = (Integer) dk.get(gdo);
                            ((javafx.scene.control.TextField) f.get(fxcon)).setText(String.valueOf(broj));

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(KonverterGUIDK.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    }

                }
            }
        }
        return true;

    }
}
