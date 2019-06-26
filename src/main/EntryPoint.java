import csp.CuttingStockInstance;
import csp.CuttingStockProblem;
import file.Waescher;
import gui.javafx.CuttingStockSolutionViewer.CuttingStockSolutionViewer;
import javafx.application.Application;
import javafx.stage.Stage;

public class EntryPoint extends Application {

    @Override
    public void start(Stage arg0) throws Exception {

        CuttingStockInstance instance =  new Waescher().getData();

        CuttingStockProblem cuttingStockProblem = new CuttingStockProblem(instance);
        cuttingStockProblem.solve();

        try {
            new CuttingStockSolutionViewer(cuttingStockProblem.getCuttingStockSolution()).showUserInterface();
        } catch (Exception e) {

            e.printStackTrace();
            System.exit(1);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
