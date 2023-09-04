package duke.command;
import java.io.IOException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Commmand to delete a task from the tasklist
 */
public class DeleteCommand extends Command {
	private final int pos;
	public DeleteCommand(int pos) {
		this.pos = pos;
	}
	/**
	 * Executes the delete command which deletes a task from taskList.
	 * Ui displays deleting to user.
	 * Deletion changes the contents in storage.
	 * @param taskList list of tasks to execute.
	 * @param u displays execution of deleting.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public void execute(TaskList taskList, Ui u, Storage storage) {
		try {
			taskList.delete(this.pos);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
}
