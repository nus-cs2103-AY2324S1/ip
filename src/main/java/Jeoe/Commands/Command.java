package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the abstract class Command.
 * It is meant for executing an action & determining if the command would end the run loop.
 *
 * @author Joe Chua
 * @version Week-3
 */
public abstract class Command {

    /** A boolean that determines whether this command would end the run loop in Jeoe */
    private boolean isExit;

    /**
     * Constructor for a Command object.
     * @param isExit Exit status inputted by other commands to see if they end the run loop or not.
     */
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the actions for the command.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public abstract void execute(TaskManager taskManager, Ui ui, StorageManager storageManager);

    /**
     * Returns the exit status of the command.
     *
     * @return Exit status of the command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
