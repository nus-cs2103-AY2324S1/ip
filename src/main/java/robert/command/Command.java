package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;

/**
 * The base abstract class for commands.
 *
 * @author Lee Zhan Peng
 */
public abstract class Command {

    /**
     * Abstract method that executes the intended task for its subclass.
     *
     * @param tasks the list of tasks to be added onto.
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException to allow subclasses to override the function with the throw property.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws RobertException;

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
