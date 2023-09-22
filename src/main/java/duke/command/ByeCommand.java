package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to exit the Duke application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to exit the application and returns a farewell message.
     *
     * @param taskList The task list containing tasks (not used in this command).
     * @param storage  The storage component for saving tasks (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @return A farewell message to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.showByeMessage();
    }

    /**
     * Returns true only if the command is a bye command.
     *
     * @return True as this is a bye command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

