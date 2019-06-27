import file.Waescher;
import gui.javafx.LoadingView.LoadingScreen;
import gui.javafx.LoadingView.task.CuttingStockProblemResolver;
import javafx.application.Application;
import javafx.stage.Stage;

public class EntryPoint extends Application {

    @Override
    public void start(Stage arg0) throws Exception {

        LoadingScreen loadingScreen = new LoadingScreen();
        Waescher cspInstanceRetriever = new Waescher();

        Thread resolver = new CuttingStockProblemResolver(cspInstanceRetriever.getData(), loadingScreen);
        resolver.start();

        loadingScreen.showUserInterface();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
