package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command: displays all tasks in the list of tasks.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return All tasks in the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() == 0) {
            return "There are no tasks in your list!";
        }

        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            tasksString.append(String.format("  %d. %s\n", i + 1, tasks.getTask(i).toString()));
        }
        return String.format("Here are the tasks in your list:\n%s", tasksString);
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
