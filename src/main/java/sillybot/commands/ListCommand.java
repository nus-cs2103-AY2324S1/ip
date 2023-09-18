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
    }

    /**
     * Executes the ListCommand object.
     *
     * @return The response to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printTasks();
    }
}
