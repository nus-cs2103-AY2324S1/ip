package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Ui;
import java.util.Scanner;

public class DeleteCommand extends Command {
    private Scanner tokeniser;

    public DeleteCommand(Scanner tokeniser) {
        this.tokeniser = tokeniser;
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return tasks.deleteTask(tokeniser);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
