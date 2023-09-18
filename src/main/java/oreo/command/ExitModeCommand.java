package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;

public class ExitModeCommand extends Command {

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) throws IllegalCommandException {
        return "I guess not! What else can I help you with?";
    }
}
