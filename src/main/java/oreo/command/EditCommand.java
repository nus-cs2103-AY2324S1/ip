package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Formatter;

import java.util.Scanner;

public class EditCommand extends Command {
    private String command;

    private Scanner tokeniser;

    private Task taskToEdit;

    public EditCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    public EditCommand() {

    }

    public Task getEditTask() {
        return taskToEdit;
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return "Seems like you can't do that edit!\n"
                + "You are currently modifying this task: \n"
                + Ui.indentLineBy(oldTask.toString(), 2)
                + "Type \"C\" to cancel";
    }

    @Override
    public String execute(TaskList tasks) throws IllegalCommandException {
        this.taskToEdit = tasks.getEditTask(tokeniser);
        return "You are currently modifying this task: \n"
                + Ui.indentLineBy(taskToEdit.toString(), 2)
                + "Type \"C\" to cancel";
    }
}
