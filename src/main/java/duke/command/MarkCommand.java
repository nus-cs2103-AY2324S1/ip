package duke.command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command to Mark or unMark task in the taskList.
 * pos is position of task in taskList starting from 1.
 */
public class MarkCommand extends Command {
	private final boolean isMark;
	private final int positionToMark;
	public MarkCommand(boolean isMark, int positionToMark) {
		this.isMark = isMark;
		this.positionToMark = positionToMark - 1;
	}

	/**
	 * Executes the Mark command which Marks or unMarks a task from taskList.
	 * Ui displays Marking or unMarking a task o user.
	 *
	 * @param taskList list of tasks to mark.
	 * @param ui displays execution of Marking or unMarking.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		try {
			taskList.toMark(isMark, this.positionToMark, ui);
			storage.updateMainStorage(true);
			String markComment = isMark ? ui.showMark(taskList.getTask(this.positionToMark)) :
					ui.showUnMark(taskList.getTask(this.positionToMark));
			return markComment;
		} catch (DukeException e) {
			return e.getMessage();
		}
	}
}
