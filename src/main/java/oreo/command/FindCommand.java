package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.util.Scanner;

public class FindCommand extends Command {
    private Scanner tokeniser;

    public FindCommand(Scanner tokeniser) {
        this.tokeniser = tokeniser;
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return tasks.findTasksWith(tokeniser);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
