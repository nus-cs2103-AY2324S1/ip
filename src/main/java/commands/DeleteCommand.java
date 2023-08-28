package commands;

import errors.DotException;
import tasks.TaskList;

public class DeleteCommand extends Command {

    private final int position;
    private final TaskList dotTaskList;

    public DeleteCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        dotTaskList.deleteTask(position - 1);
        dotTaskList.saveTaskListToStorage();
    }

    ;

}
