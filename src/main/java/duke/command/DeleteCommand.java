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
	 * Updates both storage and local taskList for reference during execution
	 * @param taskList list of tasks to execute.
	 * @param ui displays execution of deleting.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String remaining = taskList.getRemaining();
		Task task = taskList.getTask(pos);
		try {
			storage.deleteFromFile(pos);
			System.out.println(ui.showDelete(task, remaining));
			return ui.showDelete(task, remaining);
		} catch (IndexOutOfBoundsException e) {
			return e.getMessage();
//			System.out.println(e.getMessage());
		}
	}
}
