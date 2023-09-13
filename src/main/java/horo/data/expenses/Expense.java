package horo.data.expenses;

import horo.data.Data;

public class Expense extends Data {
  private Integer amount;
  private String description;

  public Expense(Integer amount, String description) {
    this.amount = amount;
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("$%d %s", this.amount, this.description);
  }

  @Override
  public String getDataString() {
    return String.format("E,%d,%s", this.amount, this.description);
  }
}
