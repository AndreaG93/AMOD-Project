package gui.javafx.ResultView;

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockPattern;
import CuttingStock.CuttingStockProblem;
import CuttingStock.CuttingStockSolution;
import gui.javafx.UserInterfaceJavaFX;
import gurobi.GRB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
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

    private CuttingStockSolution solution;

    public ResultView() throws Exception {
        super();

        CuttingStockInstance instance = new CuttingStockInstance(5);

        instance.addItems(1, 5);
        instance.addItems(3, 3);
        instance.addItems(3, 2);

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

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";


    private void updateObjectiveFunctionValueGraphic(ArrayList<Double> objectiveFunctionValues){

        XYChart.Series series = new XYChart.Series();

        int iteration = 0;
        for (Double value : objectiveFunctionValues) {
            series.getData().add(new XYChart.Data(String.valueOf(iteration), value));
            iteration++;
        }

        this.fx_ObjectiveLineChart.getData().add(series);
    }


    private void updateSolutions(Map<Integer, CuttingStockPattern> input){

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final StackedBarChart<String, Number> sbc =
                new StackedBarChart<String, Number>(xAxis, yAxis);

        this.test.getChildren().add(sbc);
/*
        for (Map.Entry<Integer, CuttingStockPattern> pair : input.entrySet()) {

            for (Double value : pair.getValue().getCuttingLengths()){

                XYChart.Series<String, Number> currentPatternSeries = new XYChart.Series<String, Number>();
                currentPatternSeries.setName(String.valueOf(value));

                String x = String.format("Pattern #%d - x%d", pair.getKey(), pair.getValue().getCardinality());
                currentPatternSeries.getData().add(new XYChart.Data<String, Number>(x, value));

                sbc.getData().add(currentPatternSeries);
            }
        }
        */

        Map<Double, XYChart.Series<String, Number>> hashMapSeries = new HashMap<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : input.entrySet()) {

            for (Double value : pair.getValue().getCuttingLengths()){

                XYChart.Series<String, Number> currentPatternSeries = hashMapSeries.get(value);
                if (currentPatternSeries == null){
                    currentPatternSeries = new XYChart.Series<String, Number>();
                    currentPatternSeries.setName(String.valueOf(value));
                    hashMapSeries.put(value, currentPatternSeries);
                }

                String x = String.format("Pattern #%d - x%d", pair.getKey(), pair.getValue().getCardinality());
                currentPatternSeries.getData().add(new XYChart.Data<String, Number>(x, value));
            }
        }

        for (Map.Entry<Double, XYChart.Series<String, Number>> pair : hashMapSeries.entrySet()) {
            sbc.getData().add(pair.getValue());
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
