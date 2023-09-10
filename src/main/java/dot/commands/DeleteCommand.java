package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.Task;
import dot.tasks.TaskList;

/**
 * Command to delete a task based on position in list.
 */
public class DeleteCommand extends Command implements Undoable {

    private final int position;

    private final TaskList dotTaskList;

    /**
     * For the undo method.
     */
    private Task deletedTask;
    private int zeroBasedDeletePosition;

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
        deletedTask = dotTaskList.deleteTask(position - 1, handleDotOutput);
        // We can assume that position is within range here [0, tasks.size()]
        zeroBasedDeletePosition = position - 1;
        dotTaskList.saveTaskListToStorage();
    }

    /**
     * Undoes the deletion of the latest Task.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void undo(Consumer<String> handleDotOutput) throws DotException {
        dotTaskList.addTaskToSpecificPosition(deletedTask, handleDotOutput, zeroBasedDeletePosition);
        dotTaskList.saveTaskListToStorage();
    }

    /**
     * {@inheritDoc}
     *
     * @return true to indicate that this is an Undoable Command.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}
