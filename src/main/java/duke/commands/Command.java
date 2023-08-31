package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * Abstract Command class that can be executed to perform a command
 */
public abstract class Command {

    /**
     * Types of commands
     */
    public enum Types {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DATE
    }

    /**
     * Whether the command is an Exit Command
     * @return True if command is an Exit Command, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes command
     * @param taskList TaskList for containing lists
     * @param ui Ui to handle interaction with users
     * @param storage Storage to handle file saving and loading
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
