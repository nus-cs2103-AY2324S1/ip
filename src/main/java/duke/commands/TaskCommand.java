package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public abstract class TaskCommand extends Command {

  protected String description;
  protected boolean doneness;

  public TaskCommand(String description, boolean doneness) {
    this.description = description;
    this.doneness = doneness;
  }

  @Override
  public void load(TaskList tasklist) {
  }

  @Override
  public void execute(TaskList tasklist, Ui ui, Storage storage) {
  }
}
