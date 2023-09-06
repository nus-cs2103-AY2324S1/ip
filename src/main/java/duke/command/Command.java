package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Abstract command class for commands to be executed after parsing
 */
public abstract class Command {
    public abstract void execute(TaskList tasklst, Ui ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
