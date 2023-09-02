package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends Command {
    int taskNum;

    /**
     * Constructs a DeleteCommand object using the superclass constructor and
     * initialises the index of the task to delete.
     *
     * @param taskNum The task number.
     */
    public DeleteCommand(int taskNum) {
        super(CommandType.DELETE);
        this.taskNum = taskNum;
    }

    /**
     * Deletes a task from the task list and displays the delete message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            tasks.deleteTask(taskNum - 1);
            ui.showDeleteMessage(task);
            ui.showTaskListSizeMessage(tasks.getSize(), false);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}
