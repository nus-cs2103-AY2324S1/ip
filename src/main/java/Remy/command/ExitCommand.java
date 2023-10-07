package remy.command;

import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * Represents an Exit Command that ends the Chatbot session upon executing.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints the exit message.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.getExitMessage();
    }
}
