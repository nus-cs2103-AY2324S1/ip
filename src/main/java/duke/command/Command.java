package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
