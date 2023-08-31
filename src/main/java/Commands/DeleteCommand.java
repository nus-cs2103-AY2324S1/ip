package Commands;

import Exceptions.DukeException;
import Tasks.TaskList;
import Utilities.Storage;
import Utilities.Ui;

public class DeleteCommand extends Command {

  private int index;

  public DeleteCommand(int index) {
    this.index = index;
  }
  
  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
    ui.print(tasklist.delete(index));
  }
}
