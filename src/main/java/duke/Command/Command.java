package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;



public abstract class Command {
    public abstract boolean isExit();
    public abstract String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException;

}
