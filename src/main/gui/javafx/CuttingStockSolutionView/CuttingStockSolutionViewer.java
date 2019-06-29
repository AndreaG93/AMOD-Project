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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolutionViewer extends UserInterfaceJavaFX {

    @FXML
    private LineChart<Number, Number> fx_ObjectiveLineChart;
    @FXML
    private StackedBarChart<String, Number> fx_problemGraphicSolution;

    @FXML
    private Label lbl_OFMinimumIntegerValue;
    @FXML
    private Label lbl_OFMinimumWasteIntegerValue;
    @FXML
    private Label lbl_OFMinimumRealValue;

    @FXML
    private VBox fx_vBoxRoot;
    @FXML
    private Label fx_columnsAdded;
    @FXML
    private Label fx_elapsedTime;
    @FXML
    private BorderPane brdPn_textSolution;
    @FXML
    private Label lbl_timeExpired;
    @FXML
    private Label lbl_waste;
    @FXML
    private HBox hbx_panel1;
    @FXML
    private HBox hbx_panel2;
    @FXML
    private Label lbl_WasteMimimumOFValue;
    @FXML
    private CheckBox chbx_IntegerOF;
    @FXML
    private CheckBox chbx_DualOF;

    @FXML
    private CheckBox chbx_RelaxedOF;

    @FXML
    private CheckBox chbx_waste;

    @FXML
    private Label lbl_wasteMin;

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

        CuttingStockSolutionViewerTable table = new CuttingStockSolutionViewerTable();

        brdPn_textSolution.setCenter(table);

        populateSummaryFields();


        updateLineChart(null);

        populateGraphicSolutionStackedBarChart(cuttingStockSolution.getSolutionPatterns());

        table.setItems(FXCollections.observableArrayList(this.cuttingStockSolution.getPatterns()));
    }

    private void populateSummaryFields() {

        this.lbl_OFMinimumRealValue.setText(String.valueOf(this.cuttingStockSolution.getMinimumRealObjectiveFunctionValue()));
        this.lbl_OFMinimumIntegerValue.setText(String.valueOf(this.cuttingStockSolution.getMinimumIntegerObjectiveFunctionValue()));
        this.lbl_OFMinimumWasteIntegerValue.setText(String.valueOf(this.cuttingStockSolution.getMinimumWasteIntegerObjectiveFunctionValue()));
        this.lbl_WasteMimimumOFValue.setText(String.valueOf(this.cuttingStockSolution.getWasteValueWhenOFIntegerValueIsMinimum()));

        this.fx_columnsAdded.setText(String.valueOf(this.cuttingStockSolution.getTotalNumberOfColumnsAdded()));

        this.fx_elapsedTime.setText(this.cuttingStockSolution.getTimeElapsed() + " ms ");
        this.lbl_timeExpired.setText(String.valueOf(this.cuttingStockSolution.isCurrentSolutionApproximated()));
        this.lbl_waste.setText(String.valueOf(this.cuttingStockSolution.getWasteLastValues()));
        this.lbl_wasteMin.setText(String.valueOf(this.cuttingStockSolution.getMinimumWaste()));
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
            populateLineChart(cuttingStockSolution.getRelaxedObjectiveFunctionValues(), this.fx_ObjectiveLineChart, "Relaxed O.F. Value");

        if (this.chbx_IntegerOF.isSelected())
            populateLineChart(cuttingStockSolution.getObjectiveFunctionValues(), this.fx_ObjectiveLineChart, "Integer O.F. Value");

        if (this.chbx_DualOF.isSelected())
            populateLineChart(cuttingStockSolution.getDualObjectiveFunctionValues(), this.fx_ObjectiveLineChart, "Dual O.F. Value");

        if (this.chbx_waste.isSelected())
            populateLineChart(cuttingStockSolution.getWasteValues(), this.fx_ObjectiveLineChart, "Waste Trend");
    }
}