package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the exit command entered by the user to end the conversation.
 * When the user provides an exit command, an instance of this class is created
 * to end the conversation and display a goodbye message.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the ExitCommand instance.
     * Initializes the instance to specify that it represents an exit command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the exit command to end the conversation.
     * This method returns a goodbye message to signal the end of the conversation.
     *
     * @param taskList The task list with the current available tasks (unused in this command).
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class (unused in this command).
     * @return A String containing a goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
