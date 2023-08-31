package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
	/**
	 * The index of the task to be marked
	 */
	private final int index;
	/**
	 * The type of mark command
	 */
	private final boolean isDone;

	/**
	 * Constructor
	 *
	 * @param index the index of the task to be marked
	 * @param type  the type of mark command
	 */
	public MarkCommand(int index, String type) {
		this.index = index;
		this.isDone = type.equals("mark");
	}

	/**
	 * Executes the command
	 *
	 * @param tasks   the task list
	 * @param ui      the ui
	 * @param storage the storage
	 * @throws DukeException if there is an error
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		// Check if index is invalid or the task is already marked
		if (tasks.length() < index || tasks.getTask(index - 1).isCompleted() == isDone) {
			throw new DukeException("The task you are trying to mark either doesnt exist, or is already marked");
		}

		if (isDone) {
			tasks.mark(index - 1);
			ui.showMark(tasks.getTask(index - 1));
		} else {
			tasks.unmark(index - 1);
			ui.showUnmark(index, tasks.getTask(index - 1));
		}

		storage.writeData(tasks.getAllTasks());
	}

	/**
	 * Returns true if the command is an exit command
	 *
	 * @return true if the command is an exit command
	 */
	@Override
	public boolean isExit() {
		return false;
	}
}
