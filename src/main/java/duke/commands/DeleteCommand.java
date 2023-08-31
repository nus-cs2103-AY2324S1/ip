package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.exceptions.DukeException;

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
