package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import gui.MainWindow;

/**
 * Represents a command that can be executed, performing specific tasks when doing so.
 */
public abstract class Command {
    /**
     * Checks whether this command is an exit command.
     *
     * @return True if command is exit command.
     */
    public abstract boolean isExit();

    /**
     * Executes this command.
     *
     * @param taskManager The task manager that modifies the task list.
     * @param diskManager The disk manager that handles updating data in local disk.
     * @param ui The ui that handles writing output.
     * @throws DukeException When something went wrong during execution.
     */
    public abstract String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException;
}
