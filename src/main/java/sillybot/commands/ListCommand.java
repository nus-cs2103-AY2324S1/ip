package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents a ListCommand object that handles the list command.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the ListCommand object.
     *
     * @return The String representation of the ListCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printTasks();
    }
}
