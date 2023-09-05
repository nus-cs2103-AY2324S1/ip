package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ExitCommand class represents a command for exiting the Duke chatbot application.
 *
 * @author selwyn
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand by changing the exit status and returning an exit message.
     *
     * @param taskList The task list (not used in this command).
     * @param storage  The storage object (not used in this command).
     * @return A message indicating the application should exit.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        super.changeExitStatus();
        return Ui.printExit();
    }
}
