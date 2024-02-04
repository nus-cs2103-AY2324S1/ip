package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {

    private int taskNum;

    /**
     * Constructs a MarkCommand object using the superclass constructor and
     * initialises the index of the task to mark.
     *
     * @param taskNum The task number.
     */
    public MarkCommand(int taskNum) {
        super(CommandType.MARK);
        this.taskNum = taskNum;
    }

    /**
     * Marks a task as done and displays the mark task message.
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
            task.changeStatus(true);
            return ui.showMarkTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("You have no such task, mortal.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand markCommand = (MarkCommand) obj;
            return markCommand.taskNum == this.taskNum;
        }
        return false;
    }
}
