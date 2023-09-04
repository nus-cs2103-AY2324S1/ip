package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
/**
 * Command to list the task in the list of tasks, taskList.
 */
public class ListCommand extends Command {
	/**
	 * Executes the list command which lists all tasks from taskList.
	 * Ui displays listing to user.
	 * Storage does not store anything in this case.
	 * @param taskList list of tasks to execute.
	 * @param u displays listing of task in taskList.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public void execute(TaskList taskList, Ui u, Storage storage) {
		taskList.list();
	}
}
