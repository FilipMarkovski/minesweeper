/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import communication.Communication;
import minesweeper.MainMenuFX;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import meta.ILogin;
import meta.User;
import meta.LoginImplementationService;
import meta.Request;
import meta.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Filip Markovski
 */
public class LoginController {

    FXMLDocumentController fxdc;

    public LoginController(FXMLDocumentController doc) {
        this.fxdc = doc;
        this.fxdc.loginButton.setOnAction(new LoginListener(this));
        this.fxdc.registrationButton.setOnAction(new RegistrationListener(this));
    }

    void login() {
        User user = new User();
        user.setUsername(fxdc.usernameField.getText());
        user.setPassword(fxdc.passwordField.getText());

        LoginImplementationService service = new LoginImplementationService();
        ILogin i = service.getLoginImplementationPort();

        Request request = new Request();
        request.setUser(user);

        Response resp = i.login(request);
        if (resp != null) {
            if (resp.getUser() == null) {
                alertMessage("Login unsuccessful!", Alert.AlertType.ERROR);
                return;
            } else {
                alertMessage("Login successful!", Alert.AlertType.INFORMATION);
            }
            
            MainMenuFX main = new MainMenuFX();
            Stage s = new Stage();

            try {
                domain.User newUser = new domain.User();
                User metaUser = resp.getUser();
                
                newUser.setID(metaUser.getUserId());
                newUser.setUsername(metaUser.getUsername());
                newUser.setPassword(metaUser.getPassword());
                newUser.setEmail(metaUser.getEmail());
                newUser.setHighscore(metaUser.getHighscore());
                
                
                if (newUser.getHighscore() == 0) {
                    newUser.setHighscore(10000);
                }
//                main.setUser(((User) resp.getUser()).getUsername(), ((User) resp.getUser()).getPassword());
                main.setUser(newUser);
                main.start(s);

                fxdc.closeForm();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            alertMessage("Login unsuccessful!", Alert.AlertType.ERROR);
        }
    }

    void register() {
        String username = fxdc.usernameField.getText();
        String password = fxdc.passwordField.getText();
        String email = fxdc.emailField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
        } else {
            transfer.Request request = new transfer.Request();
            request.setOperation(Operation.OPERATION_REGISTER);

            domain.User user = new domain.User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setHighscore(10000);

            request.setUser(user);
            Communication.getInstance().sendRequest(request);

           
//            System.out.println("resp: " + );
            transfer.Response response = Communication.getInstance().readResponse();
            
            if (response != null) {
                if (response.getStatus() == ResponseStatus.ERROR) {
                    alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
                    return;
                } else {
                    alertMessage("Registration successful!", Alert.AlertType.INFORMATION);
                }
                
                MainMenuFX main = new MainMenuFX();
                Stage s = new Stage();

                try {
                    main.setUser(response.getUser());
                    main.start(s);

                    fxdc.closeForm();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
            }
        }
    }

    public void alertMessage(String message, Alert.AlertType alertType) {
        Alert infoAlert = new Alert(alertType);
        
        switch (alertType) {
            case INFORMATION:
                infoAlert.setTitle("Success");
                break;
            case ERROR:
                infoAlert.setTitle("Error:");
                break;
            default:
                infoAlert.setTitle("Information");
                break;
        }
        
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }

}
