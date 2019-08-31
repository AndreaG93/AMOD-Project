package gui.javafx.CuttingStockSolutionView;

import csp.CuttingStockPattern;
import csp.CuttingStockSolution;
import gui.javafx.UserInterfaceJavaFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolutionViewer extends UserInterfaceJavaFX {

    @FXML
    private HBox hbx_panel1;
    @FXML
    private HBox hbx_panel2;
    @FXML
    private BorderPane brdPn_1;
    @FXML
    private BorderPane brdPn_1_2;
    @FXML
    private BorderPane brdPn_2;
    @FXML
    private BorderPane brdPn_2_2;

    // Charts
    @FXML
    private LineChart<Number, Number> fx_ObjectiveLineChart;
    @FXML
    private StackedBarChart<String, Number> fx_problemGraphicSolution;

    // Checkboxes

    @FXML
    private CheckBox chbx_IntegerOF;
    @FXML
    private CheckBox chbx_RelaxedOF;
    @FXML
    private CheckBox chbx_waste;

    // Textual solution

    @FXML
    private Label lbl_numberOfCuts;
    @FXML
    private Label lbl_waste;

    // Textual solution (minimum waste)

    @FXML
    private Label lbl_numberOfCuts_minimumWaste;
    @FXML
    private Label lbl_waste_minimumWaste;

    // Charts page

    @FXML
    private Label lbl_OF_IntegerValue;
    @FXML
    private Label lbl_OF_RelaxedValue;
    @FXML
    private Label lbl_columnsAdded;
    @FXML
    private Label lbl_elapsedTime;
    @FXML
    private Label lbl_isTimeOut;





    private CuttingStockSolution cuttingStockSolution;

    public CuttingStockSolutionViewer() throws Exception {
        super();
    }

    public void setCuttingStockSolution(CuttingStockSolution cuttingStockSolution) {
        this.cuttingStockSolution = cuttingStockSolution;
    }

    @Override
    public void updateUserInterface() {

        this.hbx_panel1.setStyle("-fx-background-color: linear-gradient(to bottom, #F0F0F0, #ffffff);");
        this.hbx_panel2.setStyle("-fx-background-color: linear-gradient(to bottom, #F0F0F0, #ffffff);");

        populateSummaryFields();
        updateLineChart(null);
        populateGraphicSolutionStackedBarChart(cuttingStockSolution.getSolutionPatternsAsMap());

        displaySolutionTables(this.brdPn_1, cuttingStockSolution.getCspSolutionPatterns());
        displaySolutionTables(this.brdPn_2, cuttingStockSolution.getCspSolutionPatternsMinimumWaste());

        CuttingStockSolutionViewerOutputTable table1Produced = new CuttingStockSolutionViewerOutputTable();
        table1Produced.addData(cuttingStockSolution.getSolutionPatternsAsMap(), cuttingStockSolution.getCspInstance().getItems());
        this.brdPn_1_2.setCenter(table1Produced);

        CuttingStockSolutionViewerOutputTable table2Produced = new CuttingStockSolutionViewerOutputTable();
        table2Produced.addData(cuttingStockSolution.getSolutionPatternsMinimumWasteAsMap(), cuttingStockSolution.getCspInstance().getItems());
        this.brdPn_2_2.setCenter(table2Produced);
    }

    private void displaySolutionTables(BorderPane borderPane, Collection<CuttingStockPattern> solution) {
        CuttingStockSolutionViewerTable table = new CuttingStockSolutionViewerTable(cuttingStockSolution.getCspInstance().getMaxItemLength());

        table.setItems(FXCollections.observableArrayList(solution));
        borderPane.setCenter(table);
    }


    private void populateSummaryFields() {

        this.lbl_OF_RelaxedValue.setText(String.valueOf(this.cuttingStockSolution.getObjectiveFunctionReal()));
        this.lbl_OF_IntegerValue.setText(String.valueOf(this.cuttingStockSolution.getObjectiveFunctionInteger()));

        this.lbl_columnsAdded.setText(String.valueOf(this.cuttingStockSolution.getTotalNumberOfColumnsAdded()));
        this.lbl_elapsedTime.setText(this.cuttingStockSolution.getTimeElapsed() + " ms ");
        this.lbl_isTimeOut.setText(String.valueOf(this.cuttingStockSolution.isTimeOut()));

        this.lbl_numberOfCuts.setText(String.valueOf(this.cuttingStockSolution.getObjectiveFunctionInteger()));
        this.lbl_waste.setText(String.valueOf(this.cuttingStockSolution.getWaste()));

        this.lbl_numberOfCuts_minimumWaste.setText(String.valueOf(this.cuttingStockSolution.getObjectiveFunctionInteger_MinimumWaste()));
        this.lbl_waste_minimumWaste.setText(String.valueOf(this.cuttingStockSolution.getWaste_Minimum()));
    }

    private void populateLineChart(ArrayList<Double> objectiveFunctionValues, LineChart<Number, Number> lineChart, String nameSeries) {

        XYChart.Series series = new XYChart.Series();
        series.setName(nameSeries);

        double width = 700;
        int iteration = 0;
        for (Double value : objectiveFunctionValues) {
            series.getData().add(new XYChart.Data(String.valueOf(iteration), value));
            iteration++;
            width += 55;
        }

        lineChart.getData().add(series);
        lineChart.setPrefWidth(width);
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

    @FXML
    void updateLineChart(ActionEvent event) {

        this.fx_ObjectiveLineChart.getData().clear();

        if (this.chbx_RelaxedOF.isSelected())
            populateLineChart(cuttingStockSolution.getObjectiveFunctionRealValues(), this.fx_ObjectiveLineChart, "Relaxed O.F. Value");

        if (this.chbx_IntegerOF.isSelected())
            populateLineChart(cuttingStockSolution.getObjectiveFunctionIntegerValues(), this.fx_ObjectiveLineChart, "Integer O.F. Value");

        if (this.chbx_waste.isSelected())
            populateLineChart(cuttingStockSolution.getWasteValues(), this.fx_ObjectiveLineChart, "Waste Trend");
    }
}