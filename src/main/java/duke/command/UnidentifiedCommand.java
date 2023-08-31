package duke.command;

import duke.exception.UnidentifiedCommandException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;

public class UnidentifiedCommand extends Command {
	public UnidentifiedCommand(Printer out, TaskList taskList, FileIO savefile) {
		super(out, taskList, savefile);
	}

	@Override
	public void action() {
		throw new UnidentifiedCommandException();
	}
}
