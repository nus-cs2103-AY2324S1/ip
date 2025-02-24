package tasket.command;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.StorageInterface;
import tasket.ui.Ui;

/**
 * The class for commands.
 */
public abstract class Command {
    protected String commandDescription;

    /**
     * Constructs a command.
     *
     * @param description The arguments of the command, or the full command, or nothing.
     */
    public Command(String description) {
        this.commandDescription = description;
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return Respective response of each command.
     * @throws TasketException If error occured while running the command.
     */
    public abstract String execute(TaskList taskList, Ui ui, StorageInterface storage) throws TasketException;

    /**
     * To identify if it's a exit command.
     * All commands will return false except the exit command.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
