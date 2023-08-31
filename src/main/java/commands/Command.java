package commands;

import storage.DataFile;
import tasks.TaskList;

/**
 * Represents a command that can be read by the chatbot.
 */
abstract public class Command {

    /**
     * Abstract method to implemented.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    abstract public void execute(TaskList tasks , DataFile dF);

    /**
     * Returns a boolean depending the command.
     * Default is false.
     * @return False, as that is the default.
     */
    public boolean isExit() {
        return false;
    }
}
