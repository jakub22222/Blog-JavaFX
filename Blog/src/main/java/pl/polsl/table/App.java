package pl.polsl.table;

import pl.polsl.controllers.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import pl.polsl.controllers.LoginController;
import pl.polsl.controllers.RegisterController;
import pl.polsl.controllers.MyPostsController;
import pl.polsl.model.BlogModel;
import pl.polsl.model.User;
/**
 * Class manages the scene and switches beetwen views
 * @author Jakub Hoś
 * @version 1.3
 */
public class App extends Application {

    /**
     * Scene object
     */
    private static Scene scene;
    /**
     * Model object
     */
    private static BlogModel model;
    /**
     * Current user object
     */
    private static User currentUser;
    /**
     * Method incializes scene
     * @param stage Stage
     * @throws IOException Thrown when file is not accessible
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/pl/polsl/views/login.fxml"), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Method sets scenes root 
     * @param fxml Url to FX view
     * @throws IOException Thrown when file is not accessible
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * Loads proper FXML view and creates its controller object
     * @param fxml Url to FX view
     * @return FXML loader
     * @throws IOException Thrown when file is not accessible
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        switch (fxml) {
            case "/pl/polsl/views/table.fxml":
                fxmlLoader.setControllerFactory( p -> { return new TableController(model, currentUser);} );
                break;
            case "/pl/polsl/views/register.fxml":
                fxmlLoader.setControllerFactory( p -> { return new RegisterController(model);} );
                break;
            case "/pl/polsl/views/myPosts.fxml":
                fxmlLoader.setControllerFactory( p -> { return new MyPostsController(model, currentUser);} );
                break;
            default:
                fxmlLoader.setControllerFactory( p -> { return new LoginController(model);} );
                break;   
        }
        return fxmlLoader.load();
    }
    /**
     * Sets reference to currentUser 
     * @param currentUser Current user object 
     */
    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
    /**
     * Main class method
     * @param args Arguments 
     */
    public static void main(String[] args) {
        model = new BlogModel();
        launch();
    }

}