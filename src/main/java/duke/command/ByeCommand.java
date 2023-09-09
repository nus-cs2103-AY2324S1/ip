package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command to exit the system and say bye.
 */
public class ByeCommand extends Command {
	/**
	 * Executes the bye command which exits system.
	 * Ui displays exiting to user.
	 * @param taskList list of tasks to execute.
	 * @param ui displays execution of adding.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		System.out.println(ui.showGoodBye());
		return ui.showGoodBye();
	}
}
