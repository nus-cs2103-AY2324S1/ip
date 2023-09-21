package duke.command;

import java.io.IOException;

import duke.*;

/**
 * Represents a command that marks a task as done in the task list.
 * When executed, it will set the specified task's status to done
 * and update the storage accordingly.
 */
public class MarkCommand extends Command {

    /** The index of the task in the task list to be marked as done. */
    private int taskNumber;

    /**
     * Initializes a new instance of the MarkCommand class.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * This command will update both the in-memory task list and the
     * stored data file.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to read/write tasks from/to the data file.
     * @throws DukeException If the specified task number is invalid.
     * @throws IOException   If there's an error saving the tasks to the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            Task task = tasks.get(taskNumber - 1); // arrays are 0-based, so subtract 1
            task.markAsDone();
            storage.saveTasks(tasks); // Save the updated tasks to file
            return ui.showTaskMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number.");
        }
    }
}