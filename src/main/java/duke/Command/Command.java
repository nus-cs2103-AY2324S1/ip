package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException;

}
