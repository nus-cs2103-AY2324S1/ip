package Commands;

import Exceptions.DukeException;
import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public class ByeCommand extends Command {

  public ByeCommand() {};

  @Override
  public boolean isExit() {
    return true;
  }

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
    ui.print("Aw goodbye.. ಠ_ಠ");
  }
}
