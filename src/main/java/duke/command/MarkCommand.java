package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndexToMark;

    /**
     * Constructs a MarkCommand with the index of the task to be marked as done.
     *
     * @param taskIndexToMark The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndexToMark) {
        this.taskIndexToMark = taskIndexToMark;
    }

    /**
     * Executes the MarkCommand to mark the specified task as done, update the user interface,
     * and save the tasks to storage.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskIndexToMark);
        ui.showMarked(tasks.getTask(taskIndexToMark));
        storage.saveTasks(tasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since MarkCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}