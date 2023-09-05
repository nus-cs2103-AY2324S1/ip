package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;

/**
 * Command to terminate Oscar.
 */
public class ExitCommand extends Command {
    /**
     * Instantiates an exit command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Displays message when terminating Oscar.
     *
     * @param tasks   ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Goodbye for now. Oscar hopes to see you again soon!\n";
    }
}
