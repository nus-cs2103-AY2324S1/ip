/**
 * Command to mark task as not done.
 */
public class UnmarkTaskCommand extends DudeCommand {
  private static final String MARKED_AS_NOT_DONE_PREFIX = "Got it. I've marked this task as not done:\n\t";

  /**
   * Task to unmark.
   */
  private final int taskIndex;

  /**
   * Constructor for unmark task command.
   *
   * @param taskIndex Index of task to mark.
   */
  public UnmarkTaskCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  /**
   * Marks task as not done and saves list to disk.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
    Task task = taskList.getTask(taskIndex);
    task.markAsNotDone();
    storage.save(taskList.toArrayList());
    ui.printMessage(
      String.format(MARKED_AS_NOT_DONE_PREFIX + task));
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
