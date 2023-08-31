package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;

/**
 * This interface is used to give a building block for all class that implements the Command interface.
 */
public interface Command {

    /**
     * Executes the command that is used to make changes to the program from the parser.
     *
     * @param tasks Object that holds the list locally and has all the methods associated with it.
     * @param storage Storage used to store the list in the hard disk.
     * @param ui Ui that is used to print messages.
     */
    public void execute(TaskList tasks, Storage storage, Ui ui);
}
