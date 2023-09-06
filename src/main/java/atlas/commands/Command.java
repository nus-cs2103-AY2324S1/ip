package atlas.commands;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;

/**
 * Abstract Command class that can be executed to perform a command
 */
public abstract class Command {

    /**
     * Types of commands: TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, FIND
     */
    public enum Type {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE, FIND
    }

    /**
     * Returns whether a command is an Exit command
     * @return True if command is an Exit Command, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes command for CLI
     * @param taskList TaskList for containing tasks
     * @param ui Ui to handle interaction with users
     * @param storage Storage to handle file saving and loading
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Executes command for GUI
     * @param taskList TaskList for containing tasks
     * @param storage Storage to handle file saving and loading
     */
    public abstract String execute(TaskList taskList, Storage storage);
}
