package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The parent class for all the commands in the Richie application
 */
public abstract class Command {
    private boolean isExit;
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException;

}
