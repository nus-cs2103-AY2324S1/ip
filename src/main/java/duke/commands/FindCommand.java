package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
	private final String keyword;

	public FindCommand(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * The keyword to search for
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		TaskList filteredTasks = tasks.filter(keyword);
		ui.showFilteredList(filteredTasks.length());
		filteredTasks.list();
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
