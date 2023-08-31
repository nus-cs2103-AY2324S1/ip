package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
/**
 * Class of the command that terminates the bot.
 */
public class ExitCommand extends Command{
    /**
     * isExit contains whether to terminate the bot.
     */
    private boolean isExit;

    /**
     * Constructor for ExitCommand
     */
    public ExitCommand() {
        this.isExit = true;
    };

    /**
     * Executes the thank method before the bot terminates.
     *
     * @param tasks List of tasks in taskList.
     * @param ui Instance of the user interface.
     * @param storage Instance of the storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.thank();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
