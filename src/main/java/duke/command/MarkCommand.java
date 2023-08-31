package duke.command;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** 
 * Represents a mark command. Marks a task from the task list based on the input number. 
 */
public class MarkCommand extends Command {
	private String s;

  /**
   * Returns a MarkCommand
   *
   * @param out Printer to print output
   * @param taskList Tasklist to update
   * @param savefile File to write task list to
   * @param s Index of task to mark
   * @return a MarkCommand with the wrapped info
   */
  public MarkCommand(Printer out, TaskList taskList, FileIO savefile, String s) {
    super(out, taskList, savefile);
    this.s = s;
  }

  /**
   * The mark action
   *
   * @throws ArgumentMustBeNumException when s is not a number
   */
  @Override
  public void action() {
    Task task;
    try {
      task = taskList.getTask(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      throw new ArgumentMustBeNumException(MARK);
    }

		task.mark();

		out.print("Nice! I've marked this task as done:", task.toString());
		save();
	}
}
