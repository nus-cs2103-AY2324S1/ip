package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * Represents a command to terminate the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command, which terminates the application.
     *
     * @param tasks The task list that contains the list of tasks.
     * @param ui The UI of the application.
     * @param storage The storage that stores task list data.
     * @return A goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.showGoodbye();
    }
}