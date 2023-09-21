package jeo.command;

import jeo.exception.JeoException;
import jeo.storage.Storage;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command Bye that ends interaction with the user.
 *
 * @author Joseph Oliver Lim
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {
        super(false);
    }

    /**
     * Executes the ByeCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws JeoException If failed to write data into file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeoException {
        storage.writeToFile(tasks);
        return ui.bye();
    }
}
