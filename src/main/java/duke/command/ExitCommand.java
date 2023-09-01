package duke.command;

import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
	public static final String COMMAND_WORD = "bye";

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) {
		this.isExit = true;
		ui.showGoodbyeMessage();
	}
}
