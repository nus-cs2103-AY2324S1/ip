package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for the Command class
 *
 * @author marioalvaro
 */
public abstract class Command {
    /**
     * Method to execute the command
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @throws Exception Throw Exception if needed
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
}
