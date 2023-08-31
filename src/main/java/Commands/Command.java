package Commands;

import Exceptions.DukeException;
import Tasks.TaskList;
import Utilities.Storage;
import Utilities.Ui;

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
