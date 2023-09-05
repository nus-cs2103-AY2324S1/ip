package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.TaskList;

/**
 * Command to mark a task, given its position on the list.
 */
public class MarkCommand extends Command {

    private final int position;

    private final TaskList dotTaskList;

    /**
     * Constructor for MarkCommand.
     *
     * @param position    This is the position of the task as listed by ListCommand.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public MarkCommand(int position, TaskList dotTaskList) {
        this.position = position;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Marks the task as done and saves all tasks to storage.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        dotTaskList.setTaskComplete(position - 1, true, handleDotOutput);
        dotTaskList.saveTaskListToStorage();
    }

}
