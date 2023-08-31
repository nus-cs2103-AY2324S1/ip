package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command
 */

public class Command {
    /**
     * Whether the command is an exit command
     */
    private boolean isExit;

    /**
     * Constructor for Command
     * @param isExit Whether the command is an exit command
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command
     * @param tasks Task list to be added to
     * @param ui Ui to show the user the task has been added
     * @param storage Storage to save the task list
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    /**
     * Returns whether the command is an exit command
     * @return Whether the command is an exit command
     */
    public boolean isExit() {
        return this.isExit;
    }
}
