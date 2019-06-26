package gui.javafx.CuttingStockSolutionViewer;

import csp.CuttingStockPattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CuttingStockSolutionViewerTable extends TableView<CuttingStockPattern> {

    public CuttingStockSolutionViewerTable() {


        TableColumn<CuttingStockPattern, String> column1 = new TableColumn<>("Cardinality");
        TableColumn<CuttingStockPattern, String> column2 = new TableColumn<>("Pattern");

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


        this.getColumns().add(column1);
        this.getColumns().add(column2);
    }
}

