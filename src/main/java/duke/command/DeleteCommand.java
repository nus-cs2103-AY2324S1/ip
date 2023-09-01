package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param taskIndexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    /**
     * Executes the DeleteCommand to delete the specified task from the task list,
     * update the user interface, and save the tasks to storage.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(taskIndexToDelete);
        tasks.deleteTask(taskIndexToDelete);
        ui.showDeleted(deletedTask, tasks.listSize());
        storage.saveTasks(tasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since DeleteCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
