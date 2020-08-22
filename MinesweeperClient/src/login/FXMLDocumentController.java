/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Bojana
 */
public class FXMLDocumentController implements Initializable {

    LoginController con;
    Stage stage;

    @FXML
    public AnchorPane loginPane;
    
    @FXML
    public Label windowLabel;
    
    @FXML
    public Label dontHaveAnAccountText;
    
    @FXML
    public Label haveAnAccountText;
    
    @FXML
    public Button loginButton;
    
    @FXML
    public Button registrationButton;
    
    @FXML
    public Button showRegistrationButton;
    
    @FXML
    public Button showLoginButton;
    
    @FXML
    public TextField usernameField;
    
    @FXML
    public PasswordField passwordField;
    
    @FXML
    public TextField emailField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = new LoginController(this);
    }

    @FXML
    private void handlePressHereForRegistrationButton(ActionEvent event) {
        emailField.setVisible(true);
        registrationButton.setVisible(true);
        haveAnAccountText.setVisible(true);
        showLoginButton.setVisible(true);
        
        windowLabel.setText("Register");
        
        loginButton.setVisible(false);
        dontHaveAnAccountText.setVisible(false);
        showRegistrationButton.setVisible(false);
    }
    
    @FXML
    private void handlePressHereForLoginButton(ActionEvent event) {
        emailField.setVisible(false);
        registrationButton.setVisible(false);
        haveAnAccountText.setVisible(false);
        showLoginButton.setVisible(false);
        
        windowLabel.setText("Login");
        
        loginButton.setVisible(true);
        dontHaveAnAccountText.setVisible(true);
        showRegistrationButton.setVisible(true);
    }
    
    void setStage(Stage stage) {
        this.stage = stage;
    }

    void closeForm() {
        this.stage.close();
    }

}
