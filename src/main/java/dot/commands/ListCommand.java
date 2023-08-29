package commands;

import errors.DotException;
import tasks.TaskList;

public class ListCommand extends Command {
    private final TaskList dotTaskList;
    public ListCommand(TaskList dotTaskList) {
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        this.dotTaskList.list();
    };
}
