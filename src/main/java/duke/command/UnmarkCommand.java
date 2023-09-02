package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that unmarks a task.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Constructs a UnmarkCommand object using the superclass constructor and
     * initialises the task number.
     *
     * @param taskNum The task number.
     */
    public UnmarkCommand(int taskNum) {
        super(CommandType.UNMARK);
    }

    /**
     * Unmarks a task in the task list and displays the unmark task message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            task.changeStatus(false);
            ui.unmarkTaskMessage(task);
            ui.taskListSizeMessage(tasks.getSize(), true);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}
