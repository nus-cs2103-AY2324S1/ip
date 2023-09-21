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

    public String actOn(TaskList tasks) throws IllegalCommandException{
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a keyword");
        }
        String keyword = tokeniser.next();
        TaskList toPrint = new TaskList();
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            Task ref = tasks.get(i);
            if (ref.contains(keyword)) {
                toPrint.add(ref);
            }
        }
        return toPrint.listResults(keyword);
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return actOn(tasks);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
