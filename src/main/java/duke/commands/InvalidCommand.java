package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `InvalidCommand` class represents a command that is not recognized or valid.
 * It is responsible for providing a response message indicating that the user input
 * does not correspond to a valid command.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the "Invalid" command. It provides a response message indicating that the
     * user input is not recognized as a valid command.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface for displaying error messages to the user.
     * @return A response message indicating that the user input is not a valid command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.illegalArgumentExceptionReply();
    }
}
