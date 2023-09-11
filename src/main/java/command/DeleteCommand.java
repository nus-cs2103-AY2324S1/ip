package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Deletes a task from the todo list
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:\n";

    /** The index of the task to be deleted */
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task with the specified index from the TaskList, and saves the current TaskList
     * in the specified Storage file.
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
            int originalSize = tasks.getSize();
            tasks.deleteTask(task);
            assert tasks.getSize() == originalSize - 1 : "Task was not properly added to the list";
            storage.writeToFile(tasks.getList());
            String response = MESSAGE_SUCCESS + task.toString()
                    + "\nNow you have " + tasks.getSize() + " tasks in the list";
            return response;
        } else {
            // user input is an integer bigger than size of task list
            String message = tasks.isEmpty()
                    ? "You have no tasks! Please add some tasks first"
                    : "No such task! Please enter a task ID between 1 and " + tasks.getSize();
            throw new DukeException(message);
        }
    }
}
