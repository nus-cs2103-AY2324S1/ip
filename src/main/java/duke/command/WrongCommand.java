package duke.command;

import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that cannot be understood.
 * Extends the base Command class.
 */
public class WrongCommand extends Command {


	/**
	 * @inheritDoc
	 */
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) {
		ui.showInvalidInput();
	}
}
