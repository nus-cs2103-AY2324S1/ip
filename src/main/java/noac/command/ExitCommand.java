package noac.command;


import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;

/**
 * For executing the bye command.
 */
public class ExitCommand extends Command {

    private boolean isExit = true;

    /**
     * Execute the bye command, show the bye message.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {
        ui.showByeMessage();
    }

    /**
     * Tell the main loop to exit.
     *
     * @return true to signal exit.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
