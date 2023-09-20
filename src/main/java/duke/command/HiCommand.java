package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The HiCommand class represents a command to greet the user when they start the Duke application.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class HiCommand extends Command {

    /**
     * Executes the "hi" command by displaying a welcome message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying the welcome message.
     * @param storage The storage for saving the updated task list to a file (not used in this command).
     * @return A string containing the welcome message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHiMessage();
    }
}
