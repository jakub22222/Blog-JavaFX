/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blogModelTest;


import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.model.BlogModel;
import pl.polsl.model.Document;
import pl.polsl.model.LoginException;

/**
 * Tests of addDocument method
 * @author Jakub Hoś
 * @version 1.2
 */

public class AddDocumentTest {
    BlogModel blogTest = new BlogModel();
    
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
     * Checks size of ArrayList before and after adding document
     * @param content Content of added document
     */
    @ParameterizedTest
    @ValueSource(strings = {"testTEST", "tEstTEST", "JavaJAVA", "document","ddd"})
    void checkIfDocumentWasAdded(String content) {
        int oldNumberOfDocuments = blogTest.getDocuments().size();
        blogTest.addDocument(content,0);
        assertEquals(oldNumberOfDocuments, oldNumberOfDocuments++, "Document was not added properly");
    }
    
    /**
     * Check if content and authors id were set properly
     * @param content Content of added document
     * @param author Authors login
     * @param id Authors id
     */
    @ParameterizedTest
    @CsvSource({"test,test_user1,0", "tEst,test_user2,1", "Java,test_user3,2"})
    void checkContentAndAuthor(String content, String author, String id) {
        int authorId = parseInt(id);
        blogTest.addDocument(content,authorId);
        Document last = blogTest.getDocuments().get(blogTest.getDocuments().size()-1);
        assertEquals(content,last.getContent() , "Content was not set properly");
        assertEquals(authorId, last.getAuthor(), "Wrong author id");
        assertEquals(author, blogTest.getUsers().get(last.getAuthor()).getLogin() , "Wrong author nick");
    }
    
}
