package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.exceptions.DukeException;
import duke.utils.Ui;

public abstract class Command {

  public Command() {
  }

  /**
  * Loads the tasks into the tasklist. This is done when printing to CLI is not necessary when executing a command.
  * 
  * @param tasklist the tasklist to load.
  * @throws DukeException if loading fails.
  */
  public void load(TaskList tasklist) throws DukeException {
  }
  
  /**
  * Executes the command.
  * 
  * @param tasklist the tasklist that is being executed upon.
  * @param ui the ui class to use for the task.
  * @param storage the storage class to use for the task.
  * @throws DukeException if execution fails.
  */
  public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
  }

  /**
  * Returns true only for ByeCommand, else parent implementation applies to all child Command classes.
  * 
  * @return true only for ByeCommand, false otherwise.
  */
  public boolean isExit() {
    return false;
  }

}
