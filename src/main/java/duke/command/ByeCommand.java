package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the Duke application.
 * This command displays a goodbye message and terminates the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";
        return ui.displayGoodbyeText();
    }
}

