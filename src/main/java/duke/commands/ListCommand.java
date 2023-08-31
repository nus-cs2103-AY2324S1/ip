package duke.commands;

import duke.exceptions.EmptyTaskListException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to list tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command, displaying the tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler.
     * @throws EmptyTaskListException If the task list is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskListException {
        if (tasks.size() == 0) {
            throw new EmptyTaskListException();
        }
        ui.printTasks(tasks);
    }
}
