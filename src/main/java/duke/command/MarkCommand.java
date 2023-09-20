package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand class represents a command for marking tasks as done or undone in the task list.
 *
 * @author selwyn
 */
public class MarkCommand extends Command {
    private boolean isMarked;

    private String taskToMarkDetails;

    /**
     * Constructs a MarkCommand with the specified task details and whether to mark as done or undone.
     *
     * @param args   The details of the task to be marked.
     * @param toMark Whether to mark the task as done (true) or undone (false).
     */
    public MarkCommand(String args, boolean isMarked) {
        this.taskToMarkDetails = args;
        this.isMarked = isMarked;
    }

    /**
     * Executes the MarkCommand by changing the done status of a task and saving it to storage.
     *
     * @param taskList The task list to which the task belongs.
     * @param storage  The storage object used for saving tasks.
     * @return A message indicating the successful change in task done status.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task taskToChange;
        try {
            taskToChange = taskList.changeTaskDoneStatus(this.taskToMarkDetails, this.isMarked);
            storage.saveTasks(taskList.getTaskList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        assert taskToChange != null : "Task to change should not be null";

        return Ui.printChangeTaskDoneStatus(taskToChange, this.isMarked);
    }
}
