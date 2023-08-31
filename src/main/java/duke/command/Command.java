package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    protected boolean willExitNext = false;

    public abstract void execute(TaskList items, Ui ui, Storage storage) throws DukeException;

    public boolean exitsNext() {
        return willExitNext;
    }
}
