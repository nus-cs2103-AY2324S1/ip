package joe.commands;

import java.time.LocalDateTime;
import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.tasks.DeadlineTask;

/** Represents a command to add a deadline task to the task list. */
public class DeadlineCommand extends Command {
  private final String taskDetails;
  private final LocalDateTime by;

  /**
   * Constructs a DeadlineCommand with task details and a deadline.
   *
   * @param taskDetails The description of the deadline task.
   * @param by The deadline date and time.
   */
  public DeadlineCommand(String taskDetails, LocalDateTime by) {
    this.taskDetails = taskDetails;
    this.by = by;
  }

  /**
   * Executes the command to add a deadline task to the task list.
   *
   * @param tasks The TaskList on which the command should be executed.
   * @param ui The user interface to interact with the user.
   * @param storage The storage for saving and loading tasks.
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    DeadlineTask newTask = new DeadlineTask(taskDetails, by);
    tasks.add(newTask);
    ui.print(
        String.format(
            "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.",
            newTask, tasks.size()));

    storage.saveToFile(tasks);
  }
}
