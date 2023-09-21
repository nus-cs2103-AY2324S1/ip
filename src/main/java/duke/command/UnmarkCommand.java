package duke.command;

import java.io.IOException;

import duke.*;

/**
 * Represents a command to unmark a specific task as undone.
 * This command, when executed, will unmark a task from the task list as undone
 * and persistently store the updated list to a data file.
 */
public class UnmarkCommand extends Command {

    /** The number or index of the task to be unmarked. */
    private int taskNumber;

    /**
     * Initializes a new instance of the UnmarkCommand class.
     *
     * @param taskNumber The index or number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Unmark command by changing the status of a task to undone.
     * This command will update both the in-memory task list and the stored data file.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object used to communicate with the user.
     * @param storage The storage object responsible for reading from and writing tasks to the data file.
     * @return A string message indicating the task has been unmarked as done.
     * @throws DukeException If the provided task number is invalid.
     * @throws IOException If there's an error when saving the tasks to the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.unmarkAsDone();
            storage.saveTasks(tasks);
            return ui.showTaskUnmarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number.");
        }
    }
}