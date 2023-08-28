package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
