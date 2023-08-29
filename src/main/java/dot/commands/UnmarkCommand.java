package dot.commands;

import dot.errors.DotException;
import dot.tasks.TaskList;

public class UnmarkCommand extends Command {
    private final int position;
    private final TaskList dotTaskList;
    public UnmarkCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        dotTaskList.toggleTaskStatus(position - 1, false);
        dotTaskList.saveTaskListToStorage();
    }

}
