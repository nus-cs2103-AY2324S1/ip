package duke.command;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.list.FunnyList;
import duke.storage.Storage;

public abstract class Command {

	protected boolean isExit = false;

	public abstract void execute(FunnyList tasks, Ui ui, Storage storage) throws DukeException;

	public boolean isExit() {
		return isExit;
	}

}
