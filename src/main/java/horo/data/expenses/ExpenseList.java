package horo.data.expenses;

import java.util.ArrayList;

import horo.data.HoroList;

public class ExpenseList extends HoroList<Expense> {

  public ExpenseList() {
    super();
  }

  public ExpenseList(ArrayList<Expense> expenseList) {
    super(expenseList);
  }

  public void add(Expense expense) {
    list.add(expense);
    System.out.println("Added expense: ");
    System.out.println(expense);
  }

  public void remove(int i) {
    if (list.isEmpty()) {
      System.out.println("No expenses");
      return;
    }

    try {
      System.out.println("Removed expense: ");
      System.out.println(list.remove(i));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Please enter a valid number from 1 - " + list.size());
    }
  }

  public ArrayList<Expense> getExpenses() {
    return list;
  }
}
