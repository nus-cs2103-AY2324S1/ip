package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Unmarks a task on the todo list
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:\n";

    /**
     * The index of the task to be marked as undone
     */
    protected int index;

    /**
     * Creates an unmark command with the given index
     *
     * @param index index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task with the specified index from the TaskList, and saves the current TaskList
     * in the specified Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     * @throws DukeException if an invalid index is given
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index >= 0 && this.index < tasks.getSize()) {
            Task task = tasks.getTask(this.index);
            tasks.unmarkTask(task);
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
