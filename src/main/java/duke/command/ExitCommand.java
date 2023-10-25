package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit task command, exiting the Duke application.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        displayByeMessage(ui);
    }

    private static void displayByeMessage(Ui ui) {
        ui.sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether this command is an exit command.
     * ExitCommand is an exit command, so this method returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
