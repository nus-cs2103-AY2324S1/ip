package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

	public static final String COMMAND_WORD = "list";
	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		ui.showItems(taskList);
	}
}
