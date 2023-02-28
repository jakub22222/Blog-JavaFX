/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.io.Serializable;

/**
 * Class represents blogs user
 * @author Jakub Hoś
 * @version 1.3
 */
public class User implements Serializable {
    /**
     * Login of user(must be unique)
     */
    String login;
    /**
     * Users password
     */
    String password;
    /**
     * Id which indetifies user(must be unique)
     */
    int id;
    
    /**
     * Returns users id
     * @return Users id 
     */
    public int getId() {
        return id;
    }
    /**
     * Returns users login
     * @return Users login
     */
    public String getLogin() {
        return login;
    }
    /**
     * Returns users password
     * @return Users password 
     */
    public String getPassword() {
        return password;
    }
    /**
     * Constructor
     * @param login New users login
     * @param password New users password
     * @param id New users id
     */
    public User(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }
}
