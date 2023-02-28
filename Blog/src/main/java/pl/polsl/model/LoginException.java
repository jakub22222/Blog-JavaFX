/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Class represents exceptions connected with logging and registration
 * @author Jakub Hoś
 * @version 1.3
 */
public class LoginException extends Exception {
    
    /**
     * Constructor, print out expcetion message
     * @param exceptionMessage Exception message
     */
    public LoginException(String exceptionMessage) {
        super(exceptionMessage);
    }

    
}
