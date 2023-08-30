package duke.command;

import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

public class WrongCommand extends Command {

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) {
		ui.showInvalidInput();
	}
}
