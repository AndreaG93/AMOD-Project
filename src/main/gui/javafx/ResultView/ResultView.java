package gui.javafx.ResultView;

import CuttingStock.CuttingStockInstance;
import CuttingStock.CuttingStockPattern;
import CuttingStock.CuttingStockProblem;
import CuttingStock.CuttingStockSolution;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultView extends UserInterfaceJavaFX {

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

    private CuttingStockSolution cuttingStockSolution;
    private CuttingStockSolutionViewTable table;

    public ResultView(CuttingStockSolution cuttingStockSolution) throws Exception {
        super();

        this.cuttingStockSolution = cuttingStockSolution;
        this.table = new CuttingStockSolutionViewTable();

        fx_borderPane.setCenter(this.table);

        populateSummaryFields();
        populateObjectiveLineChart(cuttingStockSolution.getObjectiveFunctionValues());
        populateGraphicSolutionStackedBarChart(cuttingStockSolution.getSolutionPatterns());

        this.table.setItems(FXCollections.observableArrayList(this.cuttingStockSolution.getPatterns()));


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

    private void populateSummaryFields(){
        this.fx_columnsAdded.setText(String.valueOf(this.cuttingStockSolution.getTotalNumberOfColumnsAdded()));
        this.fx_solutionValue.setText(String.valueOf(this.cuttingStockSolution.getMinimumObjectiveFunctionValues()));
        this.fx_elapsedTime.setText(this.cuttingStockSolution.getTimeElapsed()+ " ms ");
    }

    private void populateObjectiveLineChart(ArrayList<Double> objectiveFunctionValues) {

        XYChart.Series series = new XYChart.Series();

        int iteration = 0;
        for (Double value : objectiveFunctionValues) {
            series.getData().add(new XYChart.Data(String.valueOf(iteration), value));
            iteration++;
        }

        this.fx_ObjectiveLineChart.getData().add(series);
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

        for (Map.Entry<Double, XYChart.Series<String, Number>> pair : hashMapSeries.entrySet())
            fx_problemGraphicSolution.getData().add(pair.getValue());
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
