package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasklst, Ui ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
