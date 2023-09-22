package atlas.commands;

import atlas.components.Storage;
import atlas.components.TaskList;

/**
 * Abstract Command class that can be executed to perform a command
 */
public abstract class Command {

    /**
     * Types of commands
     */
    public enum Type {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, FIND, HELP
    }

    /**
     * Executes command for GUI
     * @param taskList TaskList for containing tasks
     * @param storage Storage to handle file saving and loading
     * @return String output containing results of execution
     */
    public abstract String execute(TaskList taskList, Storage storage);
}
