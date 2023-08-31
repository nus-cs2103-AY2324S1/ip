package duke.command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

/**
 * Represents a command in the Duke application.
 * This abstract class serves as the base for different types of commands that can be executed.
 */
public abstract class Command {

	protected boolean isExit = false;

	/**
	 * Executes the command using the provided FunnyList, Ui, and Storage objects.
	 *
	 * @param tasks The list of tasks to be operated on.
	 * @param ui The user interface for interaction.
	 * @param storage The storage manager for data management.
	 * @throws DukeException If there is an issue executing the command.
	 */
	public abstract void execute(FunnyList tasks, Ui ui, Storage storage) throws DukeException;

	/**
	 * Checks if the command triggers an exit from the application.
	 *
	 * @return True if the command triggers an exit, otherwise false.
	 */
	public boolean isExit() {
		return isExit;
	}

}
