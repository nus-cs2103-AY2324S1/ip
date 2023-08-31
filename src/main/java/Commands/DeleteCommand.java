package Commands;

import Exceptions.DukeException;
import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public class DeleteCommand extends Command {

  private int index;

  public DeleteCommand(int index) {
    this.index = index;
  }

  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
    ui.print(tasklist.delete(index));
  }
}
