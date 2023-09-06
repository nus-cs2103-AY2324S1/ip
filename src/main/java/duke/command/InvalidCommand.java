package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to show user possible commands.
 */
public class InvalidCommand extends Command {
    /**
     * List all possible commands for user when invalid command is inputted.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.prompt();
    }
    /**
     * Checks if command will end program.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
