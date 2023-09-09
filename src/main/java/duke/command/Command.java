package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Abstract class that represent a Command.
 * A Command is an executable that can execute to perform different actions.
 */
public abstract class Command {
	// only inheritors can use
	/**
	 /**
	 * Type of Command execution depends on the implementation.
	 * @param taskList list of tasks to execute Command on.
	 * @param u displays execution of Command.
	 * @param storage can write tasks to store to text file depending on Command type.
	 */
	public abstract String execute(TaskList taskList, Ui u, Storage storage);

}
