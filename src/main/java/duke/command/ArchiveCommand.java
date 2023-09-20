package duke.command;

import duke.exception.DukeException;
import duke.exception.PositionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ArchiveCommand extends Command{
	private boolean isArchive;
	private int position;
	public ArchiveCommand(boolean isArchive, int position) {
		this.isArchive = isArchive;
		this.position =  position - 1;
	}

	/**
	 * Remove task from main file, update main file storage.
	 * add file to archive, update archive file storage.
	 * vice versa.
	 *
	 * @param taskList list of tasks to execute Command on.
	 * @param ui displays execution of Command.
	 * @param storage can write tasks to store to text file depending on Command type.
	 * @return
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		if (isArchive) {
			try {
				Task task = taskList.toArchiveFile(this.position);
				storage.updateMainStorage(true);
				storage.addToFileMain(task, taskList, false);
				System.out.println(ui.showArchive(task));
				return ui.showArchive(task);
			} catch (PositionException e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		} else {
			try {
				Task task = storage.deleteFromMainFile(this.position, false);
				taskList.add(task);
				storage.updateMainStorage(true);
				System.out.println(ui.showUnArchive(task));
				return ui.showUnArchive(task);
			} catch (DukeException e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		}
	}
}
