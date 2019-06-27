package gui.javafx.CuttingStockSolutionView;

import csp.CuttingStockPattern;
import csp.CuttingStockSolution;
import gui.javafx.UserInterfaceJavaFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolutionViewer extends UserInterfaceJavaFX {

    @FXML
    private LineChart<Number, Number> fx_ObjectiveLineChart;

    @FXML
    private StackedBarChart<String, Number> fx_problemGraphicSolution;

    @FXML
    private VBox fx_vBoxRoot;

    @FXML
    private Label fx_solutionValue;

    @FXML
    private Label fx_columnsAdded;

    @FXML
    private Label fx_elapsedTime;

    @FXML
    private BorderPane fx_borderPane;

    @FXML
    private ScrollPane scrollbar;

    @FXML
    private BorderPane doubletest;


    private CuttingStockSolution cuttingStockSolution;
    private CuttingStockSolutionViewerTable table;

    public CuttingStockSolutionViewer() throws Exception {
        super();
    }

    public void setCuttingStockSolution(CuttingStockSolution cuttingStockSolution) {
        this.cuttingStockSolution = cuttingStockSolution;
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

    @Override
    public void updateUserInterface() {

        this.table = new CuttingStockSolutionViewerTable();

        fx_borderPane.setCenter(this.table);

        populateSummaryFields();
        populateObjectiveLineChart(cuttingStockSolution.getObjectiveFunctionValues());
        populateGraphicSolutionStackedBarChart(cuttingStockSolution.getSolutionPatterns());

        this.table.setItems(FXCollections.observableArrayList(this.cuttingStockSolution.getPatterns()));
    }

    private void populateSummaryFields(){
        this.fx_columnsAdded.setText(String.valueOf(this.cuttingStockSolution.getTotalNumberOfColumnsAdded()));
        this.fx_solutionValue.setText(String.valueOf(this.cuttingStockSolution.getMinimumObjectiveFunctionValues()));
        this.fx_elapsedTime.setText(this.cuttingStockSolution.getTimeElapsed()+ " ms ");
    }

    private void populateObjectiveLineChart(ArrayList<Double> objectiveFunctionValues) {

        XYChart.Series series = new XYChart.Series();

        double width = 700;
        int iteration = 0;
        for (Double value : objectiveFunctionValues) {
            series.getData().add(new XYChart.Data(String.valueOf(iteration), value));
            iteration++;
            width += 55;
        }

        this.fx_ObjectiveLineChart.getData().add(series);
        this.fx_ObjectiveLineChart.setPrefWidth(width);
    }


    private void populateGraphicSolutionStackedBarChart(Map<Integer, CuttingStockPattern> input) {

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

                XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(x, value);
                data.nodeProperty().addListener(new ChangeListener<Node>() {
                    @Override
                    public void changed(ObservableValue<? extends Node> observableValue, Node node, Node t1) {

                        t1.setStyle("-fx-border-color: black; -fx-border-width: 4 0 0 0; -fx-bar-fill: rgb(210,210,210); -fx-opacity: 0.4");
                        displayLabelForData(t1, String.valueOf(value));

                    }
                });

                currentPatternSeries.getData().add(data);
            }
        }


        double width = 700;
        double height = 700;
        for (Map.Entry<Double, XYChart.Series<String, Number>> pair : hashMapSeries.entrySet()) {
            fx_problemGraphicSolution.getData().add(pair.getValue());
            width += 55;
            height += 60;
        }
        fx_problemGraphicSolution.setPrefWidth(width);
        fx_problemGraphicSolution.setPrefHeight(height);
    }

    private void displayLabelForData(Node node, String string) {

        final Text dataText = new Text(string);

        dataText.setStyle("-fx-font-weight: bold; -fx-font-size: 14");
        node.parentProperty().addListener(new ChangeListener<Parent>() {

            @Override
            public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });

        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                dataText.setLayoutX(
                        Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2)
                );
                dataText.setLayoutY(
                        Math.round(bounds.getCenterY())
                );
            }
        });
    }




}
