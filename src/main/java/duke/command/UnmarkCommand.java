package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private int taskIndexToUnmark;

    /**
     * Constructs an UnmarkCommand with the index of the task to be marked as undone.
     *
     * @param taskIndexToUnmark The index of the task to be marked as undone.
     */
    public UnmarkCommand(int taskIndexToUnmark) {
        this.taskIndexToUnmark = taskIndexToUnmark;
    }

    /**
     * Executes the UnmarkCommand to mark the specified task as undone, update the user interface,
     * and save the tasks to storage.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsUndone(taskIndexToUnmark);
        ui.showUnmarked(tasks.getTask(taskIndexToUnmark));
        storage.saveTasks(tasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since UnmarkCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}