/**
 * Command to delete task from Dude.
 */
public class DeleteTaskCommand extends DudeCommand {
  private static final String DELETED_TASK_MSG = "Got it! I've removed this task:\n\t%s\n"
    + "You now have a total of %d task(s).";

  /**
   * Task to delete.
   */
  private final int taskIndex;

  /**
   * Constructor for delete task command.
   *
   * @param taskIndex Index of task to delete.
   */
  public DeleteTaskCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  /**
   * Deletes task from task list and saves list to disk.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
    Task removedTask = taskList.remove(taskIndex);
    storage.save(taskList.toArrayList());
    ui.printMessage(
      String.format(DELETED_TASK_MSG, removedTask, taskList.getNumTasks())
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
