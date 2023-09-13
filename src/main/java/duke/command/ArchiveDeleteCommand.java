package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ArchiveDeleteCommand extends Command {
	private final int positionToDelete;
	public ArchiveDeleteCommand(int positionToDelete) {
		this.positionToDelete = positionToDelete - 1;
	}

	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String remaining = storage.getMainRemaining(false);
		try {
			Task task = storage.deleteFromMainFile(positionToDelete, false);
			storage.updateMainStorage(false);
			System.out.println(ui.showDelete(task, remaining));
			return ui.showDelete(task, remaining);
		} catch (IndexOutOfBoundsException e) {
			return e.getMessage();
		}
	}
}
