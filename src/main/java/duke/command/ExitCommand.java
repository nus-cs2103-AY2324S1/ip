package duke.command;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Indicates that this command should cause the application to exit.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {

        return true;
    }

    /**
     * Executes the exit command. Optionally save tasks or do any clean up here if needed.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface handler.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return ui.showGoodbye();
    }
}
