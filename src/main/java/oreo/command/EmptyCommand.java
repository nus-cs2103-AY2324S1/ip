package oreo.command;

import oreo.task.Task;
import oreo.task.TaskList;

public class EmptyCommand extends Command {

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
        return "uhhh what???";
    }
}
