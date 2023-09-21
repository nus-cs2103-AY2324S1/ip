package noac.command;

import noac.util.NoacException;
import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;

/**
 * For executing the bye command.
 */
public class ExitCommand extends Command {


    /**
     * Creates the Exit Command and set the boolean isExit to true.
     */
    public ExitCommand() {
        super();
        super.isExit = true;
    }

    /**
     * Execute the bye command, show the bye message.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @return String to be displayed to user.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {
        return ui.showByeMessage();
    }

    /**
     * Tell the main loop to exit.
     *
     * @return true to signal exit.
     */
    @Override
    public boolean isExit() {
        return super.isExit;
    }
}
