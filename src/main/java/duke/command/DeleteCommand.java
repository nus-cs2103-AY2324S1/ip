package duke.command;

import duke.Ui;
import duke.exceptions.TaskIndexOutOfBoundsException;
import duke.Storage;
import duke.task.*;

/**
 * Deletes a task from the taskList
 */
public class DeleteCommand extends Command {
    protected int taskIndex;
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
                throw new TaskIndexOutOfBoundsException("Invalid task index");
            }
            taskList.deleteTask(taskIndex);
            return ui.format_response("duke.task.Task successfully deleted");
        } catch (TaskIndexOutOfBoundsException e) {
            return ui.format_response("Invalid task index. Please provide a valid index.");
        }
    }
}
