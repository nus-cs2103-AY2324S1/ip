package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
/**
 * Command that causes the chatbot to terminate.
 */
public class ExitCommand extends Command{
    /**
     * Constructor of a new ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes the exit command.
     * @param tasklst list of tasks
     * @param ui ui component of the program
     * @param storage storage componenet of the program
     */
    @Override
    public void execute(TaskList tasklst, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Checks if the current task is an exit task.
     * @return true since task is an exit task
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
