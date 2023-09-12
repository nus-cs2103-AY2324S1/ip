package horo.commands.expenses;

import java.util.ArrayList;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.expenses.Expense;

public class ListExpenseCommand extends Command {

  public static final String DISPLAY_FORMAT = "list expense";
  private static final String NAME = "list expense";
  private static final String REGEX = "^list expense";

  public ListExpenseCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    validateAndParse(input);
  }

  public void execute(Ui ui, Storage storage) throws HoroException {

    StringBuilder sb = new StringBuilder("-----Expenses-----\n");

    ArrayList<Expense> expenses = storage.getExpenseList().getExpenses();

    for (int i = 0; i < expenses.size(); i++) {
      sb.append((i + 1) + ". " + expenses.get(i) + "\n");
    }

    ui.horoOutput(sb.toString());
  }

}
