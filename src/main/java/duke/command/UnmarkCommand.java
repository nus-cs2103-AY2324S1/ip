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
        this.taskNum = taskNum;
    }

    /**
     * Unmarks a task in the task list and displays the unmark task message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     * @return The response to the user input.
     */
    public String execute(TaskList tasks, Ui ui) {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "User interface should not be null";
        
        try {
            Task task = tasks.getTask(taskNum - 1);
            task.changeStatus(false);
            return ui.showUnmarkTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("You have no such task, mortal.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand unmarkCommand = (UnmarkCommand) obj;
            return unmarkCommand.taskNum == this.taskNum;
        }
        return false;
    }
}
