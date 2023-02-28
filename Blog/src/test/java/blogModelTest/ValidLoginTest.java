/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blogModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.model.BlogModel;
import pl.polsl.model.LoginException;
import pl.polsl.model.User;


/**
 * Tests of validLogin method
 * @author Jakub Hoś
 * @version 1.2
 */
public class ValidLoginTest {
    

    private BlogModel blogTest; 
   
    @BeforeEach
    public void setup(){
        blogTest = new BlogModel();
        try
        {
           blogTest.addUser("test_user1", "test_pass1");
           blogTest.addUser("test_user2", "test_pass2");
           blogTest.addUser("test_user3", "test_pass3");
        }
        catch(LoginException e)
        {
            System.out.println(e);
        }
    }
    /**
     * Checks if value returned by method is equal to users id(added before) - login and password are correct
     */
   @Test
   void testRightPassword()
   {
       for (User u : blogTest.getUsers()) 
        {
        int id = blogTest.validLogin(u.getLogin(), u.getPassword());
        assertEquals(u.getId(), id, "Login error");
        } 
   }
   /**
    * Checks if method return -1 in case when user does not exists(incorrect login)
    * @param login 'Login' of not registered user
    */
   @ParameterizedTest
   @ValueSource(strings = {"keineUser1", "noSuchUser","anotherNotRegistered","imNotOnTheBlog"})
   void testUserDoNotHaveAccount(String login)
   {
       int id;
       id = blogTest.validLogin(login, "no such password");
       assertEquals(-1, id, "This user should not exist");
   }
   /**
    * Checks if method return -1 in case when existing user type wrong password 
    */
   @Test
   void testWrongPassword()
   {
       for (User u : blogTest.getUsers()) 
        {
        int id = blogTest.validLogin(u.getLogin(), u.getPassword()+"wrong");
        assertEquals(-1, id, "Login error");
        } 
   }
}
