package gui;

import gui.javafx.ResultView.ResultView;
import gui.javafx.register_account.Result;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override

    public void start(Stage arg0) throws Exception {

        try {
            //new Result().showUserInterface();
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
