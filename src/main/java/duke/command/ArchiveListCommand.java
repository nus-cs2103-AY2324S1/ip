package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command representing list or Archive task.
 */
public class ArchiveListCommand extends Command {
	/**
	 * Executes the task execution command, which list all tasks from archive task list
	 * and displays a message to the user.
	 *
	 * @param taskList list of tasks to execute Command on.
	 * @param ui displays execution of Command.
	 * @param storage can write tasks to store to text file depending on Command type.
	 * @return String representing archive list
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
			TaskList archiveList = storage.getArchiveList();
			System.out.println(archiveList.mainListAsString(ui, false));
			return archiveList.mainListAsString(ui, false);
	}
}
