package carbonbot.command;
import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

/**
 * The command lists all the tasks in the task list.
 */
public class ListCommand extends Command {

	/**
	 * Constructs a ListCommand object.
	 */
	public ListCommand() {

	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.showMessage("Here are the tasks in your list:");
		tasks.printList();
	}
}
