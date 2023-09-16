package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.TaskList;

public class InvalidCommand extends Command{

    @Override
    public String execute(TaskList tasks) throws IllegalCommandException {
        return new IllegalCommandException("do that").getMessage();
    }
}
