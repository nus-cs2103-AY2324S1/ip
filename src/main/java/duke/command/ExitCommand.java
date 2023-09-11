package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to terminate the Duke application.
 * Extends the base Command class.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Represents a command to add an event task in the Duke application.
     * Extends the base Command class.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
        return ui.showGoodbyeMessage();
    }
}
