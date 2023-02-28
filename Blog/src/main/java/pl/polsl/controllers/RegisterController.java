/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pl.polsl.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.polsl.model.BlogModel;
import pl.polsl.model.LoginException;
import pl.polsl.table.App;

/**
 * FXML Controller class
 * @author Jakub Hoś
 * @version 1.3
 */
public class RegisterController implements Initializable {
    /**
     * Model object
     */
    private final BlogModel model;
    /**
     * FXML field
     */
    @FXML
    private TextField userLogin;
    /**
     * FXML field
     */
    @FXML
    private PasswordField userPassword;
    /**
     * FXML field
     */
    @FXML
    private PasswordField userPassword2;
    /**
     * FXML field
     */
    @FXML
    private Label errorMessage;
    /**
     * FXML field
     */
    @FXML
    private CheckBox statute;
    /**
    * Constructor
    * @param bModel Model object
    */
    public RegisterController(BlogModel bModel) 
    {
        this.model = bModel;
    }
    
    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setVisible(false);
    }
    /**
     * Checks if data given by user is proper
     * If it is creates new user account and moves to table view
     */
    @FXML
    private void register() {
        if(statute.isSelected())
        {
            String login = userLogin.getText();
            String password = userPassword.getText();
            String password2 = userPassword2.getText();
            if(password.equals(password2))
            {
                try
                {
                    model.addUser(login, password);
                    int userId = model.validLogin(login, password);
                    App.setCurrentUser(model.getUsers().get(userId));
                    try
                    {
                        App.setRoot("/pl/polsl/views/table.fxml");
                    }
                    catch(IOException e)
                    {
                        errorMessage.setVisible(true);
                        errorMessage.setText(e.getMessage());
                    }
                }
                catch(LoginException e)
                {
                    errorMessage.setVisible(true);
                    errorMessage.setText(e.getMessage());
                }

            }
            else
            {
                errorMessage.setVisible(true);
                errorMessage.setText("Passwords are not equal");
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to agree to Terms of Service!");
            alert.show();
        }
    }    
    /**
     * Moves back to login view
     */
    @FXML
    private void login() {
        try
            {
                App.setRoot("/pl/polsl/views/login.fxml");
            }
            catch(IOException e)
            {
                errorMessage.setVisible(true);
                errorMessage.setText(e.getMessage());
            }
    }
    
}
