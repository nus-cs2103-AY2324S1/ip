package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.TaskList;

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
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        dotTaskList.deleteTask(position - 1, handleDotOutput);
        dotTaskList.saveTaskListToStorage();
    }

}
