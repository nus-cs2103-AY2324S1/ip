/**
 * Command to mark task as done.
 */
public class MarkTaskCommand extends DudeCommand {
  private static final String MARKED_AS_DONE_PREFIX = "Nice! I've marked this task as done:\n\t";

  /**
   * Task to mark.
   */
  private final int taskIndex;

  /**
   * Constructor for mark task command.
   *
   * @param taskIndex Index of task to mark.
   */
  public MarkTaskCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  /**
   * Marks task as done and saves list to disk.
   */
  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DudeException {
    Task task = taskList.getTask(taskIndex);
    task.markAsDone();
    storage.save(taskList.toArrayList());
    ui.printMessage(
      String.format(MARKED_AS_DONE_PREFIX + task));
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
