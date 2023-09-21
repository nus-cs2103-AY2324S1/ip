package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Formatter;

import java.util.Scanner;

public class DeleteCommand extends Command {
    private String command;
    private Scanner tokeniser;

    public DeleteCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return tasks.deleteTask(command, tokeniser);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
