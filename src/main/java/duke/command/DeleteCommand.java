package duke.command;

import java.io.IOException;

import duke.*;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a new DeleteCommand object with the given task number.
     *
     * @param taskNumber The index (1-based) of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command, which attempts to remove a task from the given task list
     * based on the provided task number. It also updates the storage after deletion.
     *
     * @param tasks The task list that contains the list of tasks.
     * @param ui The UI of the application.
     * @param storage The storage that stores task list data.
     * @return A message indicating the result of the execution.
     * @throws DukeException If the task number is invalid or out of bounds.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DukeException("Invalid task number!");
        }

        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber - 1);

        try {
            storage.saveTasks(tasks); // Save the updated tasks to file
        } catch (IOException e) {
            return ui.showSavingError(e.getMessage());
        }

        return ui.showDeletedTask(deletedTask, tasks.size());
    }
}