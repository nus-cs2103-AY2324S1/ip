package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

	public static final String COMMAND_WORD = "unmark";

	private int index;

	public UnmarkCommand(int index) {
		this.index = index;
	}

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		Task task = taskList.undoTask(this.index);
		storage.write(taskList);
		ui.showUnmarkMessage(task);
	}
}