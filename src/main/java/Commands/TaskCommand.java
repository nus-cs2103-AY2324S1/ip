package Commands;

import TaskPackages.TaskList;
import Utility.Storage;
import Utility.Ui;

public abstract class TaskCommand extends Command {

  protected String description;
  protected boolean doneness;

  public TaskCommand(String description, boolean doneness) {
    this.description = description;
    this.doneness = doneness;
  }

  public void load(TaskList tasklist) {
  }

  public void execute(TaskList tasklist, Ui ui, Storage storage) {
  }
}
