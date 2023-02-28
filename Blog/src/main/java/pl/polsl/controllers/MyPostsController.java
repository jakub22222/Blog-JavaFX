package pl.polsl.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.polsl.model.BlogModel;
import pl.polsl.model.Document;
import pl.polsl.model.User;
import pl.polsl.table.App;
/**
 * FXML Controller class
 * @author Jakub Hoś
 * @version 1.3
 */
public class MyPostsController {
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
    private TableColumn documentContent;
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
    public MyPostsController(BlogModel bModel, User u) {
        
        this.model = bModel;
        this.currentUser = u;
        
        List<Document> filtered = model.getDocuments()
                .stream()
                .filter(p -> p.getAuthor() == currentUser.getId())
                .collect(Collectors.toList());
        
        documents = FXCollections.observableArrayList(filtered); 
        
        documents.addListener(new ListChangeListener<Document>(){
            
            @Override
            public void onChanged(ListChangeListener.Change<? extends Document> change) {
                while (change.next()) 
                {
                    for (var remitem : change.getRemoved()) 
                    {
                        model.getDocuments().remove(remitem);
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
        dateTime.setCellValueFactory(new PropertyValueFactory<Document, String>("timeAgo"));
        
        table.setEditable(true);
        
        documentContent.setCellFactory(TextFieldTableCell.forTableColumn());	

        documentContent.setOnEditCommit(new EventHandler<CellEditEvent<Document, String>>() {
            @Override
            public void handle(CellEditEvent<Document, String> t) {
                ((Document) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContent(t.getNewValue());
            }
        });
        errorMessage.setVisible(false);
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
     * Moves to table view
     */
    @FXML
    private void goBack() {
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
    /**
     * Removes post - if user confirms it
     */
    @FXML
    private void remove() {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index != -1 )
        {
            ButtonType del = new ButtonType("Delete my post");
            ButtonType can = new ButtonType("Cancel");
            Alert alert;
            alert = new Alert(AlertType.NONE, "Are you sure ?",del,can);
            alert.showAndWait().ifPresent(response -> {
            if (response == del) 
            {
                documents.remove(index);
            } 
            });
        }
    }
}

