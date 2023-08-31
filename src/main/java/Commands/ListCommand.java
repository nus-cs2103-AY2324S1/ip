package Commands;

import Tasks.TaskList;
import Utilities.Storage;
import Utilities.Ui;

public class ListCommand extends Command {

  public ListCommand() {
  };

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    ui.print(tasklist.toString());
  }
}
