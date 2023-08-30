package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.essential.Ui;

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
     * @param tasks ArrayList of tasks.
     * @param ui User interaction handler.
     * @param storage File loading and saving handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Goodbye for now. Oscar hopes to see you again soon!\n");
    }
}
