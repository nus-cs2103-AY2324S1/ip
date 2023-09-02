package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as incomplete in the Duke application.
 * Extends the base Command class.
 */
public class UnmarkCommand extends Command {

	public static final String COMMAND_WORD = "unmark";

	private int index;

	public UnmarkCommand(int index) {
		this.index = index;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		Task task = taskList.undoTask(this.index);
		storage.write(taskList);
		ui.showUnmarkMessage(task);
	}
}