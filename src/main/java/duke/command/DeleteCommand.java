package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to delete a task in the Duke application.
 * Extends the base Command class.
 */
public class DeleteCommand extends Command {

	public static final String COMMAND_WORD = "delete";

	private int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		Task task = taskList.deleteTask(this.index);
		storage.write(taskList);
		ui.showDeleteMessage(task, taskList);

	}

}
