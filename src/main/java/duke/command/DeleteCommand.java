package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.exceptions.TaskIndexOutOfBoundsException;
import duke.Storage;
import duke.task.*;

public class DeleteCommand extends Command {
    protected int taskIndex;
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
                throw new TaskIndexOutOfBoundsException("Invalid task index");
            }
            taskList.deleteTask(taskIndex);
            System.out.println(ui.format_response("duke.task.Task successfully deleted"));
        } catch (TaskIndexOutOfBoundsException e) {
            System.out.println(ui.format_response("Invalid task index. Please provide a valid index."));
        }
    }
}
