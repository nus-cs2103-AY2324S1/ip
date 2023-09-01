package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 *  Class that encapsulates Command.
 *
 *  @author Zheng Chenglong
 *
 */
public abstract class Command {

    /**
     *  Command that executes the corresponding task.
     *
     *  @param tasks Contains all current tasks.
     *  @param ui Ui for interacting with user.
     *  @param storage Storage that the data file is stored in.
     *  @throws NobitaException Allow subclasses to override the function with the throw property.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException;

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    public abstract boolean isExit();
}
