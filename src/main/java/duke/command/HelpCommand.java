package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to display help message to user.
 */
public class HelpCommand extends Command {
	/**
	 * Give user help guide.
	 *
	 * @param taskList not used in this case.
	 * @param ui displays execution of Command.
	 * @param storage not used in this case.
	 * @return String representing help message.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		System.out.println(ui.showHelp());
		return ui.showHelp();
	}
}
