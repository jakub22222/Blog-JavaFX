package pl.polsl.controllers;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.polsl.model.BlogModel;
import pl.polsl.model.Document;
import pl.polsl.model.User;
import pl.polsl.table.App;
/**
 * FXML Controller class
 * @author Jakub Hoś
 * @version 1.3
 */
public class TableController {
    /**
     * Model object
     */
    private final BlogModel model;
    /**
     * Current user object
     */
    User currentUser;
    /**
     * FXML field
     */
    @FXML
    private TableView table;
    /**
     * FXML field
     */
    @FXML
    private Label errorMessage;
    /**
     * FXML field
     */
    @FXML
    private Label welcome;
    /**
     * FXML field
     */
    @FXML
    private TableColumn documentContent;
    /**
     * FXML field
     */
    @FXML
    private TableColumn author;
    /**
     * FXML field
     */
    @FXML
    private TableColumn dateTime;
    /**
     * Observe list with documents shown in table
     */
    private final ObservableList<Document> documents;   
    
   /**
    * Constructor
    * @param bModel Model object
    * @param u Current user
    */
    public TableController(BlogModel bModel, User u) {
        
        
        this.model = bModel;
        this.currentUser = u;
        
        documents = FXCollections.observableArrayList(model.getDocuments()); 
        
        documents.addListener(new ListChangeListener<Document>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Document> change) {
                while (change.next()) 
                {
                    for (var additem : change.getAddedSubList()) 
                    {
                        model.getDocuments().add(additem);                     
                    } 
                }
            }
        });

    } 
    /**
     * Initialize class method
     */
    @FXML
    public void initialize() {
        
        table.setItems(documents);
        documentContent.setCellValueFactory(new PropertyValueFactory<Document, String>("content"));
        author.setCellValueFactory(new PropertyValueFactory<Document, String>("author"));
        dateTime.setCellValueFactory(new PropertyValueFactory<Document, String>("timeAgo"));
        
        table.setEditable(true);
        welcome.setText("Nice to see you "+currentUser.getLogin()+" !");
        errorMessage.setVisible(false);
    }
    /**
     * Add new document to documents array
     * @param content New documents content
     */
    @FXML
    private void add(String content) {
        documents.add(new Document(content,currentUser.getId()));
    }
    /**
     * Moves to login view
     */
    @FXML
    private void logout() {
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
    /**
     * Moves to my post view
     */
    @FXML
    private void seeMyPost() {
        try
            {
                App.setRoot("/pl/polsl/views/myPosts.fxml");
            }
            catch(IOException e)
            {
                errorMessage.setVisible(true);
                errorMessage.setText(e.getMessage());
            }
    }
    /**
     * Show dialog window
     * Calls method which adds new document with text typed by user
     */
    @FXML
    private void addPost() {
        
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New post");
        dialog.setHeaderText("You can add new post here");
        dialog.setContentText("Please enter your post text:");
        dialog.getEditor().setPrefWidth(600);
        
        Optional<String> newPostContent=dialog.showAndWait();
        newPostContent.ifPresent(name -> add(name));
        
    }
    
    
}

