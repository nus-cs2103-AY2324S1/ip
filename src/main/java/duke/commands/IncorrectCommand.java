package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
	/**
	 * The message to be displayed
	 */
	private final String message;

	/**
	 * Constructor
	 *
	 * @param message the message to be displayed
	 */
	public IncorrectCommand(String message) {
		this.message = message;
	}

	/**
	 * Executes the command
	 *
	 * @param tasks   the task list
	 * @param ui      the ui
	 * @param storage the storage
	 * @throws DukeException if there is an error
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		throw new DukeException(this.message);
	}

	/**
	 * Returns true if the command is an exit command
	 *
	 * @return true if the command is an exit command
	 */
	@Override
	public boolean isExit() {
		return false;
	}

	/**
	 * Returns the message
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}
}
