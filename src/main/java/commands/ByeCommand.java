package commands;

import storage.DataFile;
import tasks.TaskList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public String execute(TaskList tasks, DataFile dF) {
        return this.toString();
    }

    /**
     * Returns true as this is an exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the string representation of bye command.
     * @return String representation of bye command.
     */
    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
