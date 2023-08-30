package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.DukeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
