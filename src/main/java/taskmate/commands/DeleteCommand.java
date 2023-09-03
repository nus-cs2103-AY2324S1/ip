package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Task;

public class DeleteCommand extends Command {
    String commandType;
    boolean isExit;
    int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.commandType = "Delete";
        this.isExit = false;
        this.deleteIndex = deleteIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToMark = tasks.getTask(this.deleteIndex);
            tasks.removeTask(this.deleteIndex);

            // print message when deleting
            ui.printSuccessfulDeleteResponse(taskToMark, tasks.getNumTotalTasks());
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
        }

    }
}
