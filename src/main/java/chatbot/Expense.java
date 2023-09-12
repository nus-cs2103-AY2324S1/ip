package chatbot;

/**
 * Records the user's expenses
 */
public class Expense {
    private String description;
    private String cost;
    public Expense(String description, String cost) {
        this.description = description;
        this.cost = cost;
    }

    public void updateCosts(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format(" %s : %s", description, cost);
    }
}
