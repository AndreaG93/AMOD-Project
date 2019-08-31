import file.WaescherFile;
import gui.javafx.LoadingView.LoadingScreen;
import gui.javafx.LoadingView.task.CuttingStockProblemResolver;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EntryPoint extends Application {

    @Override
    public void start(Stage arg0) throws Exception {

        LoadingScreen loadingScreen = new LoadingScreen();
        loadingScreen.setStageStyle(StageStyle.UNDECORATED);

        Thread resolver = new CuttingStockProblemResolver(WaescherFile.parsingCSPInstance(), loadingScreen);
        resolver.start();

        loadingScreen.updateUserInterface();
        loadingScreen.showAsNewStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// java --module-path .\src\libraries\javafx-sdk-12.0.2\lib --add-modules javafx.controls,javafx.fxml -jar .\AMOD-Project.jar
