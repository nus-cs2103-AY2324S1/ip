package duke.command;

import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.RichieException;

/**
 * The parent class for all the commands in the Richie application
 */
public abstract class Command {
    private boolean isExit;
    abstract public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException;

}
