package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.help();
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
