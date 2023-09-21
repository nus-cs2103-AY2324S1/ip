package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Represents a command to list all tasks.
 * This command retrieves and displays a list of all tasks in the task list.
 */
public class List extends Command {

    /**
     * Constructs a List command with the given input string.
     *
     * @param str The input string (not used in this command).
     */
    public List(String str) {
        super(str);
    }

    /**
     * Executes the List command.
     * Retrieves and displays a list of all tasks from the task list using the user interface.
     *
     * @param lst The task list containing the tasks.
     * @param io The user interface handling input and output.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList lst, UI io, Storage storage) {
        return io.list(lst);
    }
}
