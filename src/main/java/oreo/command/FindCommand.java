package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.util.Scanner;

public class FindCommand extends Command {
    private Scanner tokeniser;

    /**
     * Constructor for FindCommand
     *
     * @param tokeniser rest of the user input
     */
    public FindCommand(Scanner tokeniser) {
        this.tokeniser = tokeniser;
    }

    /**
     * Acts on tasks with command.
     *
     * @param tasks task list.
     * @return Response upon acting on task with command.
     * @throws IllegalCommandException invalid command formats.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            return actOn(tasks);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
