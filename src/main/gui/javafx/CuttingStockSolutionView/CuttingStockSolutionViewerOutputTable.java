package gui.javafx.CuttingStockSolutionView;

import csp.CuttingStockItem;
import csp.CuttingStockPattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuttingStockSolutionViewerOutputTable extends TableView<CuttingStockSolutionViewerOutputTableRow> {

    public CuttingStockSolutionViewerOutputTable() {

        TableColumn<CuttingStockSolutionViewerOutputTableRow, String> column1 = new TableColumn<>("Cut length");
        TableColumn<CuttingStockSolutionViewerOutputTableRow, String> column2 = new TableColumn<>("Amount produced");
        TableColumn<CuttingStockSolutionViewerOutputTableRow, String> column3 = new TableColumn<>("Amount required");
        TableColumn<CuttingStockSolutionViewerOutputTableRow, String> column4 = new TableColumn<>("Status");


        column1.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String> param) {
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
                                return param.getValue().getCutLength();
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String> param) {
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
                                return String.valueOf(param.getValue().getProducedAmountAsString());
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column3.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String> param) {
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
                                return String.valueOf(param.getValue().getRequiredAmount());
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });

        column4.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(final TableColumn.CellDataFeatures<CuttingStockSolutionViewerOutputTableRow, String> param) {
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

                                if (param.getValue().getRequiredAmount() < param.getValue().getProducedAmount()){
                                    return "Surplus";
                                } else
                                    return "OK";
                            }

                            @Override
                            public void removeListener(ChangeListener<? super String> listener) {
                            }
                        };
                    }
                });


        column4.setCellFactory(new Callback<TableColumn<CuttingStockSolutionViewerOutputTableRow, String>, TableCell<CuttingStockSolutionViewerOutputTableRow, String>>() {
            @Override
            public TableCell<CuttingStockSolutionViewerOutputTableRow, String> call(TableColumn<CuttingStockSolutionViewerOutputTableRow, String> param) {
                return new TableCell<CuttingStockSolutionViewerOutputTableRow, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setTextFill(Color.BLUEVIOLET);
                            // Get fancy and change color based on data
                            if(item.contains("OK"))
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

    void addData(Map<Integer, CuttingStockPattern> data, ArrayList<CuttingStockItem> items){
        ArrayList<CuttingStockSolutionViewerOutputTableRow> dataForTable = new ArrayList<>();
        HashMap<Double, CuttingStockSolutionViewerOutputTableRow> output = new HashMap<>();

        for (Map.Entry<Integer, CuttingStockPattern> pair : data.entrySet()) {

            for (double value : pair.getValue().getCuttingLengths())
            {
                CuttingStockSolutionViewerOutputTableRow current = output.get(value);

                if (current == null) {
                    current = new CuttingStockSolutionViewerOutputTableRow(value);
                    output.put(value, current);
                }

                for (int i = 0; i < pair.getValue().getCardinality(); i++)
                    current.increaseProducedAmount();
            }
        }

        for (CuttingStockItem item : items) {
            output.get(item.getLength()).setRequiredAmount((int) item.getAmount());
        }

        for (Map.Entry<Double, CuttingStockSolutionViewerOutputTableRow> pair : output.entrySet()) {
            dataForTable.add(pair.getValue());
        }

        this.setItems(FXCollections.observableArrayList(dataForTable));
    }
}


class CuttingStockSolutionViewerOutputTableRow {

    private final String cutLength;
    private int producedAmount;
    private int requiredAmount;

    public CuttingStockSolutionViewerOutputTableRow(double cutLength) {
        this.cutLength = String.valueOf(cutLength);
    }

    public String getCutLength() {
        return cutLength;
    }

    public void increaseProducedAmount(){
        this.producedAmount++;
    }

    public String getProducedAmountAsString() {
        return String.valueOf(producedAmount);
    }

    public int getProducedAmount() {
        return producedAmount;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}

