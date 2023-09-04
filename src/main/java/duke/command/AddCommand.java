package duke.command;
import java.io.IOException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a Command to which adds a tasks when executed.
 */
public class AddCommand extends Command {
	private final Task task;
	public AddCommand(Task task) {
		this.task = task;
	}

	/**
	 * Executes the add command which add the task into taskList.
	 * Ui displays adding to user.
	 * Output of task is stored in Storage which writes to a text file.
	 * @param taskList list of tasks to execute.
	 * @param u displays execution of adding.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public void execute(TaskList taskList, Ui u, Storage storage) {
		taskList.addTask(task);
		try {
			storage.writeFile(task);
		} catch (IOException e) {
			System.out.println("Fail to write to file\n" + e.getMessage());
		}
	}

}

