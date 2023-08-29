package dot.commands;

import dot.errors.DotException;
import dot.tasks.TaskList;

public class MarkCommand extends Command {

    private final int position;

    private final TaskList dotTaskList;

    public MarkCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        dotTaskList.setTaskComplete(position - 1, true);
        dotTaskList.saveTaskListToStorage();
    }

}
