package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to list out all the tasks
 */
public class ListCommand extends Command {

    /**
     * Represents a constructor of the ListCommand object
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the ListCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        ui.printLine();
        output += tasks.printList();
        return output;
    }
}
