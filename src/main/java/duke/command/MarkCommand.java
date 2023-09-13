package duke.command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command to Mark or Unmark task in the taskList.
 * pos is position of task in taskList starting from 1
 */
public class MarkCommand extends Command {
	private final boolean isMark;
	private final int positionToMark;
	public MarkCommand(boolean isMark, int positionToMark) {
		this.isMark = isMark;
		this.positionToMark = positionToMark - 1;
	}

	/**
	 * Executes the Mark command which Marks or Unmarks a task from taskList.
	 * Ui displays Marking or Unmarks to user.
	 * @param taskList list of tasks to mark.
	 * @param ui displays execution of Marking or Unmarking.
	 * @param storage can write tasks to store on the text file.
	 */
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		try {
			taskList.toMark(isMark, positionToMark, ui);
			storage.updateMainStorage(true);
//			System.out.println(ui.showMark(taskList.getTask(positionToMark)));
			return ui.showMark(taskList.getTask(positionToMark));
		} catch (DukeException e) {
			return e.getMessage();
		}
	}
}
