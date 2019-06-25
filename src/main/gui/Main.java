package gui;

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockProblem;
import gui.javafx.ResultView.ResultView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage arg0) throws Exception {

        CuttingStockInstance instance = new CuttingStockInstance(11.0);

        instance.addItems(48, 2.0);
        instance.addItems(35, 4.5);
        instance.addItems(24, 5.0);
        instance.addItems(10, 5.5);
        instance.addItems(8, 7.5);

        CuttingStockProblem cuttingStockProblem = new CuttingStockProblem(instance);
        cuttingStockProblem.solve();

        try {
            new ResultView(cuttingStockProblem.getCuttingStockSolution()).showUserInterface();
        } catch (Exception e) {

            e.printStackTrace();
            System.exit(1);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
