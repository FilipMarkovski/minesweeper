/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class Message {
    
    public static void gameAuthor() {
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Game author:";
        String message = "Engineer of Organizational Sciences, Filip Markovski.";

        alertTemplate(title, message, errorAlert);
    }

    public static void gameStartedAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Game started!";
        String message = "Your game has started!";

        alertTemplate(title, message, errorAlert);
    }

    public static void gameFailedToStartAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Your game cannot start.";

        alertTemplate(title, message, errorAlert);
    }

    public static void failedToOpenCellAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Error while opening the chosen field.";

        alertTemplate(title, message, errorAlert);
    }

    public static void winErrorAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Error while showing the result of your game.";

        alertTemplate(title, message, errorAlert);
    }

    public static void gameSavedAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Attention";
        String message = "Your game record has been saved.";

        alertTemplate(title, message, errorAlert);
    }

    public static void highscoreSavedAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Attention";
        String message = "Your highscore has been saved.";

        alertTemplate(title, message, errorAlert);
    }

    public static void gameSaveErrorAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error";
        String message = "System cannot save the game record.";

        alertTemplate(title, message, errorAlert);
    }

    public static void saveHighscoreErrorAlert() {
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error";
        String message = "System cannot save your highscore.";

        alertTemplate(title, message, errorAlert);
    }

    public static void gameInfo() {
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Informacije o programu:";
        String message = "Minesweeper je video igra za jednog igrača. Cilj igre je da se otvore sva polja na tabli, bez detoniranja skrivenih mina. Polja sadrže broj, koji označa koliko ima mina oko njega.";

        alertTemplate(title, message, errorAlert);
    }

    public static void alertTemplate(String title, String message, Alert.AlertType alertType) {
        Alert infoAlert = new Alert(alertType);
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }
}
