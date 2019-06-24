package gui.javafx.ResultView;

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockPattern;
import CuttingStock.CuttingStockProblem;
import CuttingStock.CuttingStockSolution;
import gui.javafx.UserInterfaceJavaFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultView extends UserInterfaceJavaFX {

    @FXML
    private LineChart<Number, Number> fx_ObjectiveLineChart;

    @FXML
    private Label fx_solutionValue;

    @FXML
    private Label fx_totalColumnAdded;

    @FXML
    private Label fx_elapsedTime;

    @FXML
    private VBox test;

    @FXML
    private StackedBarChart<String, Number> fx_problemGraphicSolution;

    private CuttingStockSolution solution;

    public ResultView() throws Exception {
        super();

        //this.fx_problemGraphicSolution.getStylesheets().add(getClass().getResource("./f.css").toExternalForm());

        //this.fx_problemGraphicSolution.setStyle("-fx-border-color: rgba(0,16,35,0.5) rgba(0,68,55,0.6) transparent rgba(0,68,55,0.7);");
        //this.fx_problemGraphicSolution.setStyle("-fx-category-gap: 100;");


        CuttingStockInstance instance = new CuttingStockInstance(11.0);

        instance.addItems(48, 2.0);
        instance.addItems(35, 4.5);
        instance.addItems(24, 5.0);
        instance.addItems(10, 5.5);
        instance.addItems(8, 7.5);

        CuttingStockProblem cuttingStockProblem = new CuttingStockProblem(instance);
        cuttingStockProblem.solve();

        this.solution = cuttingStockProblem.getCuttingStockSolution();

        this.fx_totalColumnAdded.setText(String.valueOf(cuttingStockProblem.getTotalNumberOfColumnsAdded()));

        updateObjectiveFunctionValueGraphic(cuttingStockProblem.getObjectiveFunctionValues());

        updateSolutions(cuttingStockProblem.getCuttingStockSolution().getSolutions());

        updateUserInterface();
    }

    @Override
    public void showUserInterface() {

        this.stage = new Stage();
        this.scene = new Scene(this.root);

        this.stage.setTitle("Home");
        this.stage.setScene(this.scene);
        this.stage.setResizable(true);

        this.stage.show();
    }

    private void updateObjectiveFunctionValueGraphic(ArrayList<Double> objectiveFunctionValues) {

        XYChart.Series series = new XYChart.Series();

        int iteration = 0;
        for (Double value : objectiveFunctionValues) {
            series.getData().add(new XYChart.Data(String.valueOf(iteration), value));
            iteration++;
        }

        this.fx_ObjectiveLineChart.getData().add(series);
    }


    private void updateSolutions(Map<Integer, CuttingStockPattern> input) {

        Map<Double, XYChart.Series<String, Number>> hashMapSeries = new HashMap<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : input.entrySet()) {

            for (Double value : pair.getValue().getCuttingLengths()) {

                XYChart.Series<String, Number> currentPatternSeries = hashMapSeries.get(value);
                if (currentPatternSeries == null) {
                    currentPatternSeries = new XYChart.Series<String, Number>();
                    currentPatternSeries.setName(String.valueOf(value));
                    hashMapSeries.put(value, currentPatternSeries);
                }

                String x = String.format("Pattern #%d - x%d", pair.getKey(), pair.getValue().getCardinality());

                final XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(x, value);
                data.nodeProperty().addListener(new ChangeListener<Node>() {
                    @Override
                    public void changed(ObservableValue<? extends Node> observableValue, Node node, Node t1) {

                        data.getNode().setStyle("-fx-border-color: black; -fx-border-width: 3 3 3 3;");

                    }
                });

                currentPatternSeries.getData().add(data);


            }
        }


        for (Map.Entry<Double, XYChart.Series<String, Number>> pair : hashMapSeries.entrySet()) {

            //pair.getValue().getChart().setStyle("-fx-border-color: black;");

            fx_problemGraphicSolution.getData().add(pair.getValue());


        }
    }







    @Override
    public void updateUserInterface() {


    }

    @Override
    public void closeUserInterface() {
        stage.close();
    }
}
