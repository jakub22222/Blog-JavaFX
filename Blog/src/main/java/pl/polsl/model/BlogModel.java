/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Manages the data, logic and rules of the application. 
 * Stores the objects of User and Docuemnt classes
 * @author Jakub Hoś
 * @version 1.3
 */
public class BlogModel {
    /**
     * ArrayList of usres of blog
     */
    private final ArrayList<User> users;
    /**
     * ArrayList of docuemnts on blog
     */
    private final ArrayList<Document> documents;
    /**
     * Non-parameter constructor, creates sample documents and users objects
     */
    public BlogModel() {
        this.documents = new ArrayList<>();
        this.users = new ArrayList<>();
        
        users.add(new User("root","root",0));
        
        documents.add(new Document("Meine post",0));
        documents.add(new Document("Post n+1",0,LocalDateTime.of(2022, 12, 11, 10, 34)));
        documents.add(new Document("Post n",0,LocalDateTime.of(2022, 1, 14, 10, 34)));
        documents.add(new Document("My first post",0,LocalDateTime.of(2022, 2, 4, 10, 34)));
        documents.add(new Document("My first post",0,LocalDateTime.of(2022, 1, 14, 10, 34)));
        documents.add(new Document("My first post",0,LocalDateTime.of(2021, 12, 14, 10, 34)));
        documents.add(new Document("My first post",0,LocalDateTime.of(2020, 12, 14, 10, 34)));
        documents.add(new Document("My first post",0,LocalDateTime.of(2019, 12, 14, 10, 34)));
        
    }
    /**
     * Searches for user in ArrayList and returns his id
     * @param login String 
     * @param password
     * @return Users id or -1 if user was not found
     */
    public int validLogin(String login, String password)
    {
        for (User u : users) 
        {
            if(u.getLogin().equals(login))
            {
                if(u.getPassword().equals(password))
                {
                    return u.getId();
                }
            }
        }
        return -1;
    }
    /***
     * Returns documents
     * @return ArrayList of documents
     */
    public ArrayList<Document> getDocuments() {
        return documents;
    }
    /**
     * Adds user to ArrayList of users.
     * @param login New user login
     * @param password New user password
     * @throws LoginException Throws exception when login is already used
     */
    public void addUser(String login, String password) throws LoginException
    {
        if(login.isEmpty())
             throw new LoginException("Login is empty");
        else if(password.isEmpty())
            throw new LoginException("Password is empty");
        else if(isLoginUsed(login))
            throw new LoginException("Login is already used");
        else
        users.add(new User(login,password,users.size()));
    }
    /**
     * Adds new document to ArratList of documents
     * @param content New document content
     * @param authorId Authors id
     */
    
    public void addDocument(String content, int authorId)
    {
         documents.add(new Document(content,authorId));
    }
    /**
     * Checks if given login is already taken
     * @param login Users login to check
     * @return True if login is used, false if it is not used
     */
    private boolean isLoginUsed(String login)
    {
        for (User u : users) 
        {
            if(u.getLogin().equals(login))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    
    
}
