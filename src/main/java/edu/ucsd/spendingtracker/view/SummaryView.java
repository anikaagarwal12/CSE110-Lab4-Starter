package edu.ucsd.spendingtracker.view;

import javafx.scene.Node;


import edu.ucsd.spendingtracker.view.charts.IChartProvider;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Node; 
import javafx.util.StringConverter;

public class SummaryView extends VBox {
    private final Label totalLabel = new Label();
    private final Button backButton = new Button("Back to Expenses");
    private final ComboBox<IChartProvider> chartSelector = new ComboBox<>();
     
    private final StackPane chartContainer = new StackPane();

    public SummaryView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #FFFFFF;");

        Label title = new Label("Spending Analysis");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        totalLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: #2E7D32;");

        chartSelector.setPromptText("Select Visualization Type");
        chartSelector.setPrefWidth(200);

        chartContainer.setPrefSize(400, 300);
        chartContainer.setStyle("-fx-border-color: #EEEEEE; -fx-border-width: 1; -fx-border-radius: 5;");
        backButton.setStyle("-fx-background-color: #757575; -fx-text-fill: white;");

        this.getChildren().addAll(title, totalLabel, new Label("View Mode:"), chartSelector, chartContainer, backButton);
        chartSelector.setConverter(new StringConverter<IChartProvider>() {
            @Override
            public String toString(IChartProvider chartProvider) {
                return chartProvider != null ? chartProvider.getDisplayName() : "";
            }

            @Override
            public IChartProvider fromString(String string) {
                return null; // Not needed
            }
        });
    }

    public void setTotal(double total) {
        totalLabel.setText("$" + String.format("%.2f", total));
    }
    /// Hello
    public Button getBackButton() {
        return backButton;
    }

    public void setChartDisplay(Node chartNode) { 
        chartContainer.getChildren().clear();
        if (chartNode != null) {
            chartContainer.getChildren().add(chartNode);
        }
    }

    public ComboBox<IChartProvider> getChartSelector() {
        return chartSelector;
    }
}