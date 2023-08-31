package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
	/**
	 * Executes the command
	 *
	 * @param tasks   the task list
	 * @param ui      the ui
	 * @param storage the storage
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.showList(tasks.length());
		tasks.list();
		ui.printLine();
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
}
