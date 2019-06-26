package gui.javafx;

import gui.UserInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.text.MessageFormat;


/**
 * This class represent an user graphical interface based on JavaFX system.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class UserInterfaceJavaFX extends UserInterface {

    protected Stage stage; // JavaFX "Stage" class; it is a container for any JavaFX program.
    protected Scene scene; // JavaFX "Scene" class; it is a container for all content in a scene.
    protected Parent root;

    /**
     * Constructs a newly allocated {@code View} object.
     */
    public UserInterfaceJavaFX() throws Exception {

        // Get location of FXML file...
        String resourceName = MessageFormat.format("{0}.fxml", this.getClass().getSimpleName());

        // Get a JavaFX 'FXMLLoader' object; it is used to loading FXML files at runtime...
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));

        // Set view controller...
        loader.setController(this);

        // Loads an object hierarchy from a FXML document...
        this.root = loader.load();
    }
}