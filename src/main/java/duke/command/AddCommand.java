package duke.command;
import java.io.IOException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represent a Command to which adds a tasks when executed.
 */
public class AddCommand extends Command {
	private final Task task;
	public AddCommand(Task task) {
		this.task = task;
	}

	/**
	 * Executes the task execution command, which adds a task to the task list
	 * and displays a message to the user.
	 *
	 * @param taskList The task list to which the task should be added.
	 * @param ui       The user interface for displaying messages to the user.
	 * @param storage  The storage for saving tasks to a file.
	 * @return A message to be displayed to the user.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		storage.addToFileMain(task, taskList, true);
		System.out.println(ui.showAddTask(task, taskList.getSize()));
		return ui.showAddTask(task, taskList.getSize());
	}

}

