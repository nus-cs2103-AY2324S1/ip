package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to list out all tasks in the Duke application.
 * Extends the base Command class.
 */
public class ListCommand extends Command {

	public static final String COMMAND_WORD = "list";

	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		ui.showItems(taskList);
	}
}
