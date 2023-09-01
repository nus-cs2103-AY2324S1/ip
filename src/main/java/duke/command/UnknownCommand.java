package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an unknown command.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(false);
    }

    /**
     * Prints the error message indicating an unknown command has been entered.
     *
     * @param taskList The given TaskList associated with the chatbot.
     * @param ui The given Ui used to print the error message.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.addCommandNotFound();
    }
}
