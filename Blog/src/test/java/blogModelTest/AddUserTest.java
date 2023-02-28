/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blogModelTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.model.BlogModel;
import pl.polsl.model.LoginException;
import pl.polsl.model.User;

/**
 * Tests of addUser method
 * @author Jakub Hoś
 * @version 1.2
 */
public class AddUserTest {
    BlogModel blogTest;
    @BeforeEach
    public void setup(){
        blogTest = new BlogModel();
    }
   
    /**
     * Checks number of users after and before adding user, new users login, password and id
     * @param login New users login
     * @param password New users password
     */
    @ParameterizedTest
    @CsvSource({"test,test_user1", "tEst,test_user2", "Java,test_user3", "NewUser,HisPassword","AnotherUser,qwerty"})
    void testAddManyUsersProperly(String login, String password) {
        int oldNumberOfUsers = blogTest.getUsers().size();
        try
        {
            blogTest.addUser(login, password);
        }
        catch(LoginException e)
        {
            System.out.println(e);
        }
        int newNumberOfUsers = blogTest.getUsers().size();
        User newUser = blogTest.getUsers().get(oldNumberOfUsers);
        assertEquals(oldNumberOfUsers,newNumberOfUsers-1,"Wrong number of users");
        assertEquals(login, newUser.getLogin(), "User has wrong login");
        assertEquals(password, newUser.getPassword(), "User has wrong password");
        assertEquals(oldNumberOfUsers, newUser.getId(), "User has wrong id");
    }
   
    /**
     * Checks if method throws exception in statment when given login is already used, checks exception message
     */
    @Test
    public void testUserLoginIsUsed(){
     
        try
        {
            blogTest.addUser("userIsAlreadyAdded", "123");
        }
        catch(LoginException e)
        {
            System.out.println(e);
        }
        
        LoginException exception = assertThrows(
                LoginException.class,
                () -> blogTest.addUser("userIsAlreadyAdded", "123"));
        assertEquals("Login is already used", exception.getMessage());
    
    }
    /**
     * Checks if method throws exception in statment when given login is empty, checks exception message
     */
    @Test
    public void testLoginIsEmpty(){
        LoginException exception = assertThrows(
                LoginException.class,
                () -> blogTest.addUser("", "123"));
        assertEquals("Login is empty", exception.getMessage());
    
    }
    /**
     * Checks if method throws exception in statment when given password is empty, checks exception message
     */
    @Test
    public void testPasswordIsEmpty(){
        LoginException exception = assertThrows(
                LoginException.class,
                () -> blogTest.addUser("letMeOut", ""));
        assertEquals("Password is empty", exception.getMessage());
    
    }
}
