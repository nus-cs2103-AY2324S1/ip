package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that lists all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object using the superclass constructor.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.listTasks(tasks, false);
    }
}
