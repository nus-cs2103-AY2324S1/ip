package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;



/**
 * Abstract command class which will be inherited by the different command classes.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */
    public abstract String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException;

}
