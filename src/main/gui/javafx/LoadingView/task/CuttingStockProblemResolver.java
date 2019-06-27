package gui.javafx.LoadingView.task;

import csp.CuttingStockInstance;
import csp.CuttingStockProblem;
import gui.javafx.CuttingStockSolutionView.CuttingStockSolutionViewer;
import gui.javafx.LoadingView.LoadingScreen;
import javafx.application.Platform;

public class CuttingStockProblemResolver extends Thread {

    private CuttingStockSolutionViewer cuttingStockSolutionViewer;
    private CuttingStockProblem cuttingStockProblem;
    private LoadingScreen loadingScreen;

    public CuttingStockProblemResolver(CuttingStockInstance instance, LoadingScreen loadingScreen) {
        try {
            this.cuttingStockSolutionViewer = new CuttingStockSolutionViewer();
            this.cuttingStockProblem = new CuttingStockProblem(instance);
            this.loadingScreen = loadingScreen;
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
                        cuttingStockSolutionViewer.showUserInterface();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
