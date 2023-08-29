package dot.commands;

import dot.errors.DotException;
import dot.tasks.TaskList;

public class ListCommand extends Command {

    private final TaskList dotTaskList;

    public ListCommand(TaskList dotTaskList) {
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        this.dotTaskList.list();
    }

}
