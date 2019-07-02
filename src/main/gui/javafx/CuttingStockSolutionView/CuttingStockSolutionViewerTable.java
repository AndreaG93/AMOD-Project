package gui.javafx.CuttingStockSolutionView;

import csp.CuttingStockPattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.awt.geom.Arc2D;

public class CuttingStockSolutionViewerTable extends TableView<CuttingStockPattern> {

    public CuttingStockSolutionViewerTable(double maxLength) {

        TableColumn<CuttingStockPattern, String> column1 = new TableColumn<>("Cardinality");
        TableColumn<CuttingStockPattern, String> column2 = new TableColumn<>("Pattern");
        TableColumn<CuttingStockPattern, String> column3 = new TableColumn<>("Pattern Length");
        TableColumn<CuttingStockPattern, String> column4 = new TableColumn<>("Waste");

        column1.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockPattern, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockPattern, String> param) {
                        return new ObservableValue<String>() {

                            @Override
                            public void addListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void removeListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void addListener(ChangeListener<? super String> listener) {
                            }

                            @Override
                            public String getValue() {
                                return String.valueOf(param.getValue().getCardinality());
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockPattern, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockPattern, String> param) {
                        return new ObservableValue<String>() {

                            @Override
                            public void addListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void removeListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void addListener(ChangeListener<? super String> listener) {
                            }

                            @Override
                            public String getValue() {
                                return param.getValue().getCuttingLengths().toString();
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column3.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockPattern, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockPattern, String> param) {
                        return new ObservableValue<String>() {

                            @Override
                            public void addListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void removeListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void addListener(ChangeListener<? super String> listener) {
                            }

                            @Override
                            public String getValue() {

                                double output = 0;

                                for (double value : param.getValue().getCuttingLengths())
                                    output += value;

                                return String.valueOf(output);
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column4.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockPattern, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockPattern, String> param) {
                        return new ObservableValue<String>() {

                            @Override
                            public void addListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void removeListener(InvalidationListener arg0) {
                            }

                            @Override
                            public void addListener(ChangeListener<? super String> listener) {
                            }

                            @Override
                            public String getValue() {

                                double output = 0;

                                for (double value : param.getValue().getCuttingLengths())
                                    output += value;

                                return String.valueOf(maxLength - output);
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column4.setCellFactory(new Callback<TableColumn<CuttingStockPattern, String>, TableCell<CuttingStockPattern, String>>() {
            @Override
            public TableCell<CuttingStockPattern, String> call(TableColumn<CuttingStockPattern, String> param) {
                return new TableCell<CuttingStockPattern, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setTextFill(Color.BLUEVIOLET);
                            // Get fancy and change color based on data
                            if(Double.valueOf(item) == 0.0)
                                this.setTextFill(Color.GREEN);
                            setText(item);

                        }
                    }

                };
            }
        });



        this.getColumns().add(column1);
        this.getColumns().add(column2);
        this.getColumns().add(column3);
        this.getColumns().add(column4);
    }
}

