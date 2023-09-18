package command;

import duke.DukeException;
import duke.Storage;
import task.Task;
import task.TaskList;

/**
 * Marks a task on the todo list
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n";

    /** The index of the task to be marked as done */
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task with the specified index from the TaskList, and saves the current TaskList
     * in the specified Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     * @throws DukeException if an invalid index is given
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (this.index >= 0 && this.index < tasks.getSize()) {
            Task task = tasks.getTask(this.index);
            tasks.markTask(task);
            storage.writeToFile(tasks.getList());
            return MESSAGE_SUCCESS + task;
        } else {
            // user input is an integer bigger than size of task list
            String message = tasks.isEmpty()
                    ? "You have no tasks! Please add some tasks first"
                    : "No such task! Please enter a task ID between 1 and " + tasks.getSize();
            throw new DukeException(message);
        }
    }
}
