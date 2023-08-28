package commands;

import errors.DotException;
import tasks.TaskList;

/**
 * Command to mark a task, given its position on the list.
 */
public class MarkCommand extends Command {
    private final int position;
    private final TaskList dotTaskList;

    /**
     * Constructor for MarkCommand.
     * @param position This is the position of the task as listed by ListCommand
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations
     */
    public MarkCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Marks the task as done and saves all tasks to storage.
     * @throws DotException On detected error
     */
    @Override
    public void execute() throws DotException {
        dotTaskList.toggleTaskStatus(position - 1, true);
        dotTaskList.saveTaskListToStorage();
    }
}
