package carbonbot.command;
import java.io.IOException;
import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

public class MarkCommand extends Command {
	private int taskIdx;
	private boolean isMark;

	public MarkCommand(int taskIdx, boolean isMark) {
		this.taskIdx = taskIdx;
		this.isMark = isMark;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		Task task = tasks.get(taskIdx);
		if (isMark) {
			ui.showMessage("Nice! I've marked this task as done:");
			task.markAsDone();
		} else {
			ui.showMessage("OK, I've marked this task as not done yet:");
			task.markAsUndone();
		}
		ui.showMessage(task.toString());

		// Save the TaskList to Storage
		try {
			storage.write(tasks.serialize());
		} catch (IOException ex) {
			throw new DukeException("I/O Error: Failed to write to storage. "
					+ ex.getMessage());
		}
	}
}
