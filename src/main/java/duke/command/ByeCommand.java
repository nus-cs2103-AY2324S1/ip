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
	 * @param u displays execution of adding.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public void execute(TaskList taskList, Ui u, Storage storage) {
		String echo = "____________________________________________________________\n" +
				"Bye. Hope to see you again soon!\n" +
				"____________________________________________________________";
		System.out.println(echo);
		// write to file before leaving the system
		System.exit(0);
	}
}
