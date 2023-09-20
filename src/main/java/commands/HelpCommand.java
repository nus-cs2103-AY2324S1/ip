package commands;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/**
 * The command that lists all the currently stored tasks by wallE.
 */
public class HelpCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to list out all the tasks that are currently stored in the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "commands:";
    }
}
