package chatbot;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private List<Expense> expenses;
    public ExpenseReport() {
        this.expenses = new ArrayList<Expense>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void deleteExpense(int index) {
        expenses.remove(index);
    }

    public List<Expense> getExpenseReport() {
        return expenses;
    }

    public Expense getExpense(int index) {
        return expenses.get(index);
    }

    public int getSize() {
        return expenses.size();
    }
}
