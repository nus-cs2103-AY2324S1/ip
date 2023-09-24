package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.DukeList;

/**
 * The ExitCommand class represents a command for exiting the Duke chatbot application.
 *
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand by changing the exit status and returning an exit message.
     *
     * @param dukelist The task list (not used in this command).
     * @param storage  The storage object (not used in this command).
     * @return A message indicating the application should exit.
     */
    @Override
    public String execute(DukeList dukelist, Storage storage) {
        super.setExitStatus();
        return Ui.bye();
    }
}