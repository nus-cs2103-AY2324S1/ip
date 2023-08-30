package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The MarkCommand class represents a command to mark or unmark a task as done in the task list.
 *
 * @author selwyn
 */
public class MarkCommand extends Command {
    /** Indicates whether to mark the task as done or not. */
    private boolean toMark;

    /** The details of the task to be marked. */
    private String taskToMarkDetails;

    /**
     * Constructs a MarkCommand object with the specified details of the task to be marked and the marking status.
     *
     * @param args The arguments specifying the task to be marked.
     * @param toMark True if the task should be marked as done, false if it should be marked as not done.
     */
    public MarkCommand(String args, boolean toMark) {
        this.taskToMarkDetails = args;
        this.toMark = toMark;
    }

    /**
     * Executes the MarkCommand by marking or unmarking a task as done and updating the storage.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     * @throws DukeException If there is an issue marking or unmarking the task or updating storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToChange = taskList.changeTaskDoneStatus(this.taskToMarkDetails, this.toMark);
            ui.printChangeTaskDoneStatus(taskToChange, this.toMark);
            storage.saveTasks(taskList.getTaskList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}