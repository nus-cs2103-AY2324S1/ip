package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.exceptions.DukeException;
import duke.utils.Ui;

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
