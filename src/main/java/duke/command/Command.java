package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that represents possible user commands
 * that can be executed and checks if command calls for program to end.
 */
public abstract class Command {
    /**
     * Executes the tasks required based on user command received.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if command will end program.
     * @return True if it is ByeCommand, else returns false.
     */
    public abstract boolean isExit();
}
