package duke.command;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** 
 * Represents a delete command. Deletes a task from the task list based on the number. 
 */
public class DeleteCommand extends Command {
	private String s;

  /**
   * Returns a DeleteCommand
   *
   * @param out Printer to print output
   * @param tasklist Tasklist to update
   * @param savefile File to write tasklist to
   * @param name Index of task to delete
   * @return A DeleteCommand with the wrapped info.
   */
  public DeleteCommand(Printer out, TaskList taskList, FileIO savefile, String name) {
    super(out, taskList, savefile);
    this.s = name;
  }

  /**
   * The delete action
   *
   * @throws ArgumentMustBeNumException when name is not a number
   */
  @Override
  public void action() {
    Task task;
    try {
      int i = Integer.parseInt(s); // 1-indexed
      task = taskList.getTask(i);
      taskList.deleteTask(i);
    } catch (NumberFormatException e) {
      throw new ArgumentMustBeNumException(DELETE);
    }

		out.print("Noted. I've removed this task:", task.toString(), taskList.getNumberOfTasks());
		save();
	}
}
