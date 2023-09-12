package horo.commands.expenses;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.expenses.ExpenseList;

public class DeleteExpenseCommand extends Command {
  private static final String NAME = "delete expense";
  private static final String REGEX = "^delete expense ([0-9]+)";
  private static final String DISPLAY_FORMAT = "delete expense <number>";

  private Integer index;

  public DeleteExpenseCommand(String input) throws HoroException {
    super(NAME, REGEX, DISPLAY_FORMAT);
    index = Integer.parseInt(validateAndParse(input).group(1)) - 1;
  }

  public void execute(Ui ui, Storage storage) throws HoroException {
    ExpenseList expenseList = storage.getExpenseList();
    expenseList.remove(index);
    storage.updateExpenseData(expenseList);

    ui.horoOutput("Removed expense");
  }
}
