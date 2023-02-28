/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pl.polsl.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.polsl.model.BlogModel;
import pl.polsl.model.User;
import pl.polsl.table.App;
/**
 * FXML Controller class
 * @author Jakub Hoś
 * @version 1.3
 */
public class LoginController implements Initializable {

    /**
     * Model object
     */
    private final BlogModel model;
    /**
     * Current user object
     */
    private User currentUser;
   /**
    * Constructor
    * @param bModel Model object
    */
    public LoginController(BlogModel bModel) 
    {
        this.model = bModel;
    }
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
    private Label errorMessage;
    /**
     * FXML field
     */
    @FXML
    private CheckBox remember;
    /**
     * Class intialize method
     * Sets visibility to error provider
     * Manages serialized user
     * @param url URL
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setVisible(false);
        
        try {
            checkSerializedUser();
        } catch (IOException | ClassNotFoundException ex) {
        }
        
        
    }    
    /**
     * Check login and password given by user
     * If it is correct, switches to table view
     * In other cases show error message
     */
    @FXML
    private void login() {
        String login = userLogin.getText();
        String password = userPassword.getText();
        int userId = model.validLogin(login, password);
        if(userId!=-1)
        {
            currentUser = model.getUsers().get(userId);
            App.setCurrentUser(currentUser);
            try
            {
                if(remember.isSelected())
                serializeUser();
                else
                {
                    try  
                    {         
                    File f= new File("currentUser.txt"); 
                    f.delete(); 
                    }  
                    catch(Exception e)  
                    {  
                        errorMessage.setVisible(true);
                        errorMessage.setText(e.getMessage());  
                    }  
                }
                App.setRoot("/pl/polsl/views/table.fxml");
            }
            catch(IOException e)
            {
                errorMessage.setVisible(true);
                errorMessage.setText(e.getMessage());
            }
        }
        else
        {
            errorMessage.setVisible(true);
            errorMessage.setText("Incorrect login or password");
        }
    }
    /**
     * Moves to register view
     */
    @FXML
    private void register() {
        try
        {
            App.setRoot("/pl/polsl/views/register.fxml");
        }
        catch(IOException e)
        {
            errorMessage.setVisible(true);
            errorMessage.setText(e.getMessage());
        }
    }
    /**
     * Serializes currentUser object to .txt file
     * @throws FileNotFoundException Thrown when file does not exists
     * @throws IOException Thrown when file is not accessible
     */
    private void serializeUser() throws FileNotFoundException, IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("currentUser.txt");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(currentUser);
            objectOutputStream.flush();
        }
    }
    /**
     * Checks if file with serialzied user is accessible, then read it
     * @throws FileNotFoundException Thrown when file does not exists
     * @throws IOException Thrown when file is not accessible
     * @throws ClassNotFoundException Thrown when class was not found
     */
    private void checkSerializedUser() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream("currentUser.txt");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            currentUser = (User) objectInputStream.readObject();
            userLogin.setText(currentUser.getLogin());
            userPassword.setText(currentUser.getPassword());
            remember.setSelected(true);
        } 
    }
    
}
