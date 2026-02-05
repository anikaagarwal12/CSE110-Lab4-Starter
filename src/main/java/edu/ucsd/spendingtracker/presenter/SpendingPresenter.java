package edu.ucsd.spendingtracker.presenter;

import edu.ucsd.spendingtracker.model.Expense;
import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.view.SpendingView;

public class SpendingPresenter extends AbstractPresenter<SpendingView> {
    private Runnable onShowSummary;

    public SpendingPresenter(Model model, SpendingView view) {
        super(model, view);

        // Summary navigation
        this.view.getSummaryButton().setOnAction(e -> {
            if (onShowSummary != null)
                onShowSummary.run();
        });

        // Delete handler
        this.view.setOnDelete(id -> {
            model.deleteExpense(id);
            updateView();
        });

        // Add expense modal
        this.view.getOpenModalButton().setOnAction(e -> {
            this.view.showAddExpenseModal(newExpense -> {
                model.addExpense(newExpense);
                updateView();
            });
        });

        updateView();
    }

    public void setOnShowSummary(Runnable action) {
        this.onShowSummary = action;
    }

    @Override
    public String getViewTitle() {
        return "Expenses";
    }

    // THIS IS THE EXACT METHOD FROM THE LAB
    @Override
    public void updateView() {
        view.clearList();
        for (Expense e : model.getExpenses()) {
            view.addExpenseRow(
                    e.getId(),
                    e.getName(),
                    e.getCategory().name(),
                    e.getCategory().color,
                    e.getAmount()
            );
        }
    }
}
