package taskmate.commands;

import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;

public class MarkCommand extends Command {
    String commandType;
    boolean isExit;
    int markIndex;

    public MarkCommand(int markIndex) {
        this.commandType = "Mark";
        this.isExit = false;
        this.markIndex = markIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            Task taskToMark = tasks.getTask(this.markIndex);
            tasks.markAsDone(taskToMark);

            // print message when marking
            ui.printSuccessfulMarkResponse(taskToMark);
        } catch (TaskNotFoundException e) {
            ui.printTaskNotFoundExceptionResponse();
        }

    }
}
