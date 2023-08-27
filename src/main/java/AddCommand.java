import java.io.IOException;

public class AddCommand extends Command {
	private final Task task;

	public AddCommand(Task task) {
		this.task = task;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		// Add the task to the TaskList
		tasks.add(this.task);

		ui.showMessage("Got it. I've added this task:");
		ui.showMessage(this.task.toString());
		ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

		// Save the TaskList to Storage
		try {
			storage.write(tasks.serialize());
		} catch (IOException ex) {
			throw new DukeException("I/O Error: Failed to write to storage. "
					+ ex.getMessage());
		}
	}
}
