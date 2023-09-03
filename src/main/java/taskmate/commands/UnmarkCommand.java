package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;

public class UnmarkCommand extends Command {
    String commandType;
    boolean isExit;
    int unmarkIndex;

    public UnmarkCommand(int unmarkIndex) {
        this.commandType = "Unmark";
        this.isExit = false;
        this.unmarkIndex = unmarkIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToUnmark = tasks.getTask(this.unmarkIndex);
            tasks.markAsNotDone(taskToUnmark);

            // print message when unmarking
            ui.printSuccessfulUnmarkResponse(taskToUnmark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
        }
    }
}
