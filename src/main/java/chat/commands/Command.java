package chat.commands;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * @author juzzztinsoong
 */
public abstract class Command {

    public Command() {
    }

    /**
     * Loads the tasks into the tasklist. This is done when printing to CLI is not
     * necessary when executing a command.
     * @param tasklist the tasklist to load.
     * @throws ChatException if loading fails.
     */
    public void load(TaskList tasklist) throws ChatException {
    }

    /**
     * Executes the command.
     * @param tasklist the tasklist that is being executed upon.
     * @param storage  the storage class to use for the task.
     * @throws ChatException if execution fails.
     */
    public String execute(TaskList tasklist, Storage storage) throws ChatException {
        return "";
    }

    /**
     * Returns true only for ByeCommand, else parent implementation applies to all
     * child Command classes.
     * @return true only for ByeCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
