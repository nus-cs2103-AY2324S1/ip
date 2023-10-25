package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to clear all tasks in task list.
 */
public class ClearCommand extends Command {

	/**
	 * Clear taskList and corresponding fileContent.
	 *
	 * @param taskList list of tasks to execute Command on.
	 * @param ui displays execution of Command.
	 * @param storage can write tasks to store to text file depending on Command type.
	 * @return String representing clear action to display to user on GUI.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		storage.clearFile();
		System.out.println(ui.showClear());
		return ui.showClear();
	}
}
