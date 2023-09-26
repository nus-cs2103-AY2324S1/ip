package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.util.Scanner;

public class MarkUnmarkCommand extends Command {
    private String command;
    private Scanner tokeniser;

    public MarkUnmarkCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    /**
     * Acts on tasks with command.
     *
     * @param tasks task list.
     * @return Response upon acting on task with command.
     * @throws IllegalCommandException invalid command formats.
     */
    public String actOn(TaskList tasks) throws IllegalCommandException {
        // nothing specified after command
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        // specified content is not an integer
        String content = tokeniser.next();
        if (!isInteger(content)) {
            if (content.equals("all")) {
                return command.equals("mark") ? tasks.markAll() : tasks.unmarkAll();
            }
            throw new IllegalCommandException("do that... try a number instead");
        }
        // if number of task does not exist
        int id = Integer.parseInt(content);
        if (id > tasks.getNumberOfTask() || id <= 0) {
            throw new IllegalCommandException("do that... this task does not exist :(");
        }
        // checks which mark command it is
        switch (command) {
        case "mark":
            String message = tasks.markDone(id - 1);
            return tasks.isAllComplete() ? message + tasks.list()
                    : message;
        case "unmark":
            return tasks.markNotDone(id - 1);
        default:
            throw new IllegalCommandException("help mark or unmark that");
        }
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
