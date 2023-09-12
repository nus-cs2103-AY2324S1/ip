package horo.commands.expenses;

import java.util.regex.MatchResult;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.expenses.Expense;
import horo.data.expenses.ExpenseList;

/**
 * AddExpenseCommand
 */
public class AddExpenseCommand extends Command {

  public static final String DISPLAY_FORMAT = "expense <amount> <description>";
  private static final String NAME = "expense";
  private static final String REGEX = "^expense ([0-9]+) ([\\w ]+)";

  private String description;
  private String amount;

  public AddExpenseCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    MatchResult m = validateAndParse(input);
    amount = m.group(1);
    description = m.group(2);
  }

  @Override
  public void execute(Ui ui, Storage storage) throws HoroException {
    ExpenseList e = storage.getExpenseList();
    e.add(new Expense(Integer.parseInt(this.amount), this.description));
    storage.updateExpenseData(e);

    ui.horoOutput("New Expense added");
  }
}
