package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an abstract command that can be executed.
 * This serves as the base class for all specific command types.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command should cause the program to exit.
     *
     * @return False by default, can be overridden by specific commands that cause exit.
     */
    public boolean isExit() {
        return false;
    }
}
