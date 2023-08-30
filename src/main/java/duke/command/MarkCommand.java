package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

	public static final String COMMAND_WORD = "mark";

	private int index;

	public MarkCommand(int index) {
		this.index = index;
	}

	@Override
	public void execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
		Task task = taskList.completeTask(this.index);
		storage.write(taskList);
		ui.showMarkMessage(task);
	}
}
