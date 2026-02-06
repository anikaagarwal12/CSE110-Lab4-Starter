package edu.ucsd.spendingtracker.presenter;

import java.util.List;

import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.view.SummaryView;
import edu.ucsd.spendingtracker.view.charts.IChartProvider;

public class SummaryPresenter extends AbstractPresenter<SummaryView> {
    private Runnable onBack;
    private final List<IChartProvider> chartProviders;


    public SummaryPresenter(Model model, SummaryView view, List<IChartProvider> chartProviders) {
    super(model, view);
    this.chartProviders = chartProviders;

    this.view.getChartSelector().getItems().addAll(this.chartProviders);

    if (!this.chartProviders.isEmpty()) {
        this.view.getChartSelector().setValue(this.chartProviders.get(0));
    }

    this.view.getBackButton().setOnAction(e -> {
        if (onBack != null) onBack.run();
    });

    this.view.getChartSelector().setOnAction(e -> updateView());
}


    public void setOnBack(Runnable action) {
        this.onBack = action;
    }

    
    @Override
public void updateView() {
    List<edu.ucsd.spendingtracker.model.Expense> expenses = model.getExpenses();
    java.util.Map<edu.ucsd.spendingtracker.model.Category, Double> totals =
        new java.util.TreeMap<>();
    
    for (edu.ucsd.spendingtracker.model.Category cat : edu.ucsd.spendingtracker.model.Category.values()) {
    totals.put(cat, 0.0);
}


    for (edu.ucsd.spendingtracker.model.Expense expense : expenses) {
        edu.ucsd.spendingtracker.model.Category category = expense.getCategory();
        double amount = expense.getAmount();

        
        totals.put(category, totals.get(category) + amount);
        
    }

    view.setTotal(model.getTotalSpending());

    IChartProvider selectedProvider = view.getChartSelector().getValue();
    if (selectedProvider != null) {
        javafx.scene.Node chartNode = selectedProvider.createChart(totals);
        view.setChartDisplay(chartNode);
    }
}


    @Override
    public String getViewTitle() {
        return "Summary";
    }
}
