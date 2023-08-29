package commands;

import errors.DotException;
import tasks.TaskList;

/**
 * Command to delete a task based on position in list.
 */
public class DeleteCommand extends Command {

    private final int position;
    private final TaskList dotTaskList;

    /**
     * Constructor for DeleteCommand.
     *
     * @param position    This is the position of the task as listed by ListCommand.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public DeleteCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Deletes the task and saves all Tasks to storage.
     *
     * @throws DotException On detected error.
     */
    @Override
    public void execute() throws DotException {
        dotTaskList.deleteTask(position - 1);
        dotTaskList.saveTaskListToStorage();
    }

}
