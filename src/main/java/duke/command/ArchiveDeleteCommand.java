package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the archive task list
 */
public class ArchiveDeleteCommand extends Command {
	private final int positionToDelete;
	public ArchiveDeleteCommand(int positionToDelete) {
		this.positionToDelete = positionToDelete - 1;
	}

	/**
	 * Executes the task execution command, which deletes a task from archive task list.
	 * and displays a message to the user.
	 *
	 * @param taskList list of tasks to execute Command on.
	 * @param ui displays execution of Command.
	 * @param storage can write tasks to store to text file depending on Command type.
	 * @return String representing deletion.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String remaining = storage.getMainRemaining(false);
		try {
			Task task = storage.deleteFromMainFile(positionToDelete, false);
			storage.updateMainStorage(false);
			System.out.println(ui.showDeleteMain(task, remaining, false));
			return ui.showDeleteMain(task, remaining, false);
		} catch (IndexOutOfBoundsException | DukeException e) {
			return e.getMessage();
		}
	}
}
