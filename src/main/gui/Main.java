package gui;

import gui.javafx.ResultView.ResultView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override

    public void start(Stage arg0) throws Exception {

        try {
            new ResultView().showUserInterface();
        } catch (Exception e) {

            e.printStackTrace();
            System.exit(1);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
