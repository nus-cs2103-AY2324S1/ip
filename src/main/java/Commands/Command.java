package Commands;

import Exceptions.DukeException;
import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public abstract class Command {

  public Command() {
  }

  public void load(TaskList tasklist) throws DukeException {
  }
  
  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
  }

  public boolean isExit() {
    return false;
  }

}
