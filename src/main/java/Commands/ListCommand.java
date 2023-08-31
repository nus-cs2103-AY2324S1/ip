package Commands;

import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public class ListCommand extends Command {

  public ListCommand() {
  };

  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    ui.print(tasklist.toString());
  }
}
