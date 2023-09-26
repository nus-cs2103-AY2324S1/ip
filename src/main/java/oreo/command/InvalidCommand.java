package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;

public class InvalidCommand extends Command{

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
    public String execute(TaskList tasks) throws IllegalCommandException {
        return new IllegalCommandException("do that, type \"help\" for help").getMessage();
    }
}
