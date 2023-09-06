package dude.command;

import dude.Storage;
import dude.Ui;
import dude.exception.DudeException;
import dude.task.Task;
import dude.task.TaskList;

/**
 * Command to add task to Dude.
 */
public class AddTaskCommand extends DudeCommand {
  private static final String ADDED_TASK_MSG = "Got it! I've added this task:\n\t%s\n"
    + "You now have a total of %d task(s).";

  /**
   * Task to add.
   */
  private final Task task;

  /**
   * Constructor for add task command.
   *
   * @param task Task to add.
   */
  public AddTaskCommand(Task task) {
    this.task = task;
  }

  /**
   * Adds task to task list and saves list to disk.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
    taskList.add(task);
    storage.save(taskList.toArrayList());
    ui.printMessage(
      String.format(ADDED_TASK_MSG, task, taskList.getNumTasks())
    );
  }

  /**
   * {@inheritDoc}
   *
   * @return False.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}
