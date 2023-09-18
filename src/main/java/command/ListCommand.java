package command;

import duke.Storage;
import task.TaskList;

/**
 * Prints out the current todo list
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    /**
     * Prints the current TaskList
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks.getSize() >= 0 : "Size of task list should be more than or equal to 0";
        return tasks.getSize() == 0
                ? "You have no tasks\n"
                : "Here are the tasks in your list:\n" + tasks;
    }
}
