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
     */
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            task.changeStatus(true);
            ui.showMarkTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
        }
    }
}
