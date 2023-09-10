package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.TaskList;

/**
 * Command to mark a task, given its position on the list.
 */
public class MarkCommand extends Command implements Undoable {

    // Also utilised by undo
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

    /**
     * Undoes the marking of given task.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void undo(Consumer<String> handleDotOutput) throws DotException {
        Command unmarkCommand = new UnmarkCommand(position, dotTaskList);
        unmarkCommand.execute(handleDotOutput);
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
