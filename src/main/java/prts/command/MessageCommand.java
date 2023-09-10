package prts.command;

import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * The general command for simply displaying a given message to the user.
 */
public class MessageCommand extends Command {

    private String message;

    /**
     * Constructs a MessageCommand object, given the string to be displayed.
     * @param message The message to be displayed.
     */
    public MessageCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the displaying of the message.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @return The string to be displayed to the user upon successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return message;
    }

}
