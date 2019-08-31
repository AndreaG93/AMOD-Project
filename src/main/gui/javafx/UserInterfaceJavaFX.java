package gui.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.text.MessageFormat;

/**
 * This class represent an user graphical interface based on JavaFX system.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class UserInterfaceJavaFX {

    protected Stage stage; // JavaFX "Stage" class; it is a container for any JavaFX program.
    private Scene scene; // JavaFX "Scene" class; it is a container for all content in a scene.
    private Parent root;

    private String screenTitle;
    private boolean isScreenResizable;
    private StageStyle stageStyle;

    /**
     * Constructs a newly allocated {@code View} object.
     */
    public UserInterfaceJavaFX() throws Exception {

        // Get location of FXML file...
        String resourceName = MessageFormat.format("{0}.fxml", this.getClass().getSimpleName());

        // Get a JavaFX 'FXMLLoader' object; it is used to LoadingScreen FXML files at runtime...
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));

        // Set view controller...
        loader.setController(this);

        // Loads an object hierarchy from a FXML document...
        this.root = loader.load();

        // Default...
        this.screenTitle = "";
        this.isScreenResizable = false;
        this.stageStyle = StageStyle.DECORATED;
    }

    public void showAsNewStage() {

        this.stage = new Stage();
        this.scene = new Scene(this.root);

        this.stage.setTitle(screenTitle);
        this.stage.setScene(this.scene);
        this.stage.setResizable(this.isScreenResizable);
        this.stage.initStyle(this.stageStyle);

        this.stage.show();
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public void setScreenResizable(boolean screenResizable) {
        isScreenResizable = screenResizable;
    }

    public void setStageStyle(StageStyle stageStyle) {
        this.stageStyle = stageStyle;
    }

    public abstract void updateUserInterface();
}