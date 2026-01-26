public class Expense {
    private int id;
    private String name;
    private String category;
    private double amount;

    public Expense(int id, String name, String category, double amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public Expense(String name, String category, double amount) {
        this(-1, name, category, amount);
    }

    public int getId() {
        return id;
    }

    // keep your existing getters/setters if any
}


    public double getAmount() {
        return amount;
    }
}


