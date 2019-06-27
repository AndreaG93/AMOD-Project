package gui.javafx.LoadingView.task;

import csp.CuttingStockInstance;
import csp.CuttingStockProblem;
import gui.javafx.CuttingStockSolutionView.CuttingStockSolutionViewer;
import gui.javafx.LoadingView.LoadingScreen;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CuttingStockProblemResolver extends Thread {

    private CuttingStockSolutionViewer cuttingStockSolutionViewer;
    private CuttingStockProblem cuttingStockProblem;
    private LoadingScreen loadingScreen;

    public CuttingStockProblemResolver(CuttingStockInstance instance, LoadingScreen loadingScreen) {
        try {
            this.cuttingStockSolutionViewer = new CuttingStockSolutionViewer();
            this.cuttingStockProblem = new CuttingStockProblem(instance);
            this.loadingScreen = loadingScreen;

            this.cuttingStockSolutionViewer.setScreenResizable(true);
            this.cuttingStockSolutionViewer.setScreenTitle("Solution viewer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            cuttingStockProblem.solve();
            cuttingStockSolutionViewer.setCuttingStockSolution(cuttingStockProblem.getCuttingStockSolution());

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    loadingScreen.close();
                    try {

                        cuttingStockSolutionViewer.updateUserInterface();
                        cuttingStockSolutionViewer.showAsNewStage();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    loadingScreen.close();
                    try {

                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();

                        System.exit(1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
